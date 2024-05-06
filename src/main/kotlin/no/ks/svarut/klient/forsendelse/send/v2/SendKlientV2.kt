package no.ks.svarut.klient.forsendelse.send.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.forsendelse.send.model.v2.Forsendelse
import no.ks.fiks.svarut.forsendelse.send.model.v2.SendForsendelseResponse
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import org.eclipse.jetty.client.InputStreamRequestContent
import org.eclipse.jetty.client.MultiPartRequestContent
import org.eclipse.jetty.client.Request
import org.eclipse.jetty.client.StringRequestContent
import org.eclipse.jetty.http.HttpMethod
import org.eclipse.jetty.http.MultiPart
import java.io.InputStream
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.function.Function

private const val BASE_PATH = "/api/v2"

private const val FORSENDELSE_PART_NAME = "forsendelse"
private const val FILER_PART_NAME = "filer"

class SendKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>,
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathSend(kontoId: UUID) = "$BASE_PATH/kontoer/$kontoId/forsendelser/"

    fun send(kontoId: UUID, forsendelse: Forsendelse, dokumentnavnTilData: Map<String, InputStream>): UUID =
        newRequest()
            .method(HttpMethod.POST)
            .path(pathSend(kontoId))
            .body(buildMultipartBody(forsendelse, dokumentnavnTilData))
            .idleTimeout(16, TimeUnit.MINUTES)
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw objectMapper.bodyToException(response.contentAsString)
                } else {
                    objectMapper.readValue<SendForsendelseResponse>(response.contentAsString)
                        .id
                }
            }

    private fun buildMultipartBody(forsendelse: Forsendelse, dokumentnavnTilData: Map<String, InputStream>) =
        MultiPartRequestContent().apply {
            addForsendelse(forsendelse)
            addDokumenter(forsendelse, dokumentnavnTilData)
            close()
        }

    private fun MultiPartRequestContent.addForsendelse(forsendelse: Forsendelse) {
        addPart(
            MultiPart.ContentSourcePart(
                FORSENDELSE_PART_NAME,
                null,
                null,
                StringRequestContent(objectMapper.writeValueAsString(forsendelse)),
            )
        )
    }

    private fun MultiPartRequestContent.addDokumenter(forsendelse: Forsendelse, dokumentnavnTilData: Map<String, InputStream>) {
        forsendelse.dokumenter?.forEach {
            addDokument(
                dokumentnavn = it.filnavn,
                data = dokumentnavnTilData[it.filnavn] ?: throw MissingDataException("Fant ikke input stream for dokument ${it.filnavn}")
            )
        }?: throw MissingDataException("Feltet dokumenter kan ikke være null")
    }

    private fun MultiPartRequestContent.addDokument(dokumentnavn: String, data: InputStream) {
        addPart(
            MultiPart.ContentSourcePart(
                FILER_PART_NAME,
                dokumentnavn,
                null,
                InputStreamRequestContent(data),
            )
        )
    }

}