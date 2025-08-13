package no.ks.svarut.klient.forsendelse.send.v3

import com.fasterxml.jackson.module.kotlin.readValue
import com.nimbusds.common.contenttype.ContentType.APPLICATION_PDF
import no.ks.fiks.svarut.forsendelse.send.model.v3.SendForsendelseResponse
import no.ks.fiks.svarut.forsendelse.send.model.v3.NhnForsendelse
import no.ks.fiks.svarut.forsendelse.send.model.v3.OrganisasjonForsendelse
import no.ks.fiks.svarut.forsendelse.send.model.v3.PersonForsendelse
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.HttpConfiguration
import org.eclipse.jetty.client.InputStreamRequestContent
import org.eclipse.jetty.client.MultiPartRequestContent
import org.eclipse.jetty.client.Request
import org.eclipse.jetty.client.StringRequestContent
import org.eclipse.jetty.http.HttpFields
import org.eclipse.jetty.http.HttpHeader
import org.eclipse.jetty.http.HttpMethod
import org.eclipse.jetty.http.MultiPart
import java.io.InputStream
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.function.Function

private const val BASE_PATH = "/api/v3"

private const val FORSENDELSE_PART_NAME = "forsendelse"
private const val FILER_PART_NAME = "filer"

class SendKlientV3(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>,
    httpConfig: HttpConfiguration = HttpConfiguration(),
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor, httpConfig) {

    private fun pathSendPerson(kontoId: UUID) = "$BASE_PATH/kontoer/$kontoId/forsendelser/person"
    private fun pathSendOrganisasjon(kontoId: UUID) = "$BASE_PATH/kontoer/$kontoId/forsendelser/organisasjon"
    private fun pathSendNhn(kontoId: UUID) = "$BASE_PATH/kontoer/$kontoId/forsendelser/nhn"

    fun sendTilPerson(kontoId: UUID, forsendelse: PersonForsendelse, dokumentnavnTilData: Map<String, InputStream>): UUID =
        send(pathSendPerson(kontoId),
            MultiPartRequestContent().apply {
                addForsendelse(forsendelse)
                addDokumenter(forsendelse, dokumentnavnTilData)
            close()
        })

    fun sendTilNhnMottaker(kontoId: UUID, forsendelse: NhnForsendelse, nhnDokument: NhnDokument): UUID =
        if (nhnDokument.contentType != APPLICATION_PDF.type)
            throw IllegalArgumentException("Kan kun sende PDF til NHN mottaker. Fikk ${nhnDokument.contentType}")
        else
            send(pathSendNhn(kontoId), buildMultipartBodyNhn(forsendelse, nhnDokument))

    fun sendTilOrganisasjon(kontoId: UUID, forsendelse: OrganisasjonForsendelse, dokumentnavnTilData: Map<String, InputStream>): UUID =
        send(
            path = pathSendOrganisasjon(kontoId),
            body = MultiPartRequestContent().apply {
                addForsendelse(forsendelse)
                addDokumenter(forsendelse, dokumentnavnTilData)
                close()
            })

    private fun send(path: String, body: MultiPartRequestContent): UUID =
        newRequest()
            .method(HttpMethod.POST)
            .path(path)
            .body(body)
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

    private fun buildMultipartBodyNhn(forsendelse: NhnForsendelse, nhnDokument: NhnDokument) =
        MultiPartRequestContent().apply {
            addForsendelse(forsendelse)
            addDokument(nhnDokument.filnavn, nhnDokument.contentType, nhnDokument.data,1)
            close()
        }

    private fun MultiPartRequestContent.addForsendelse(forsendelse: NhnForsendelse) {
        addPartForsendelse(StringRequestContent(objectMapper.writeValueAsString(forsendelse)))
    }
    private fun MultiPartRequestContent.addForsendelse(forsendelse: PersonForsendelse) {
        addPartForsendelse(StringRequestContent(objectMapper.writeValueAsString(forsendelse)))
    }
    private fun MultiPartRequestContent.addForsendelse(forsendelse: OrganisasjonForsendelse)  =
        addPartForsendelse(StringRequestContent(objectMapper.writeValueAsString(forsendelse)))

    private fun MultiPartRequestContent.addPartForsendelse(content: StringRequestContent) {
        addPart(MultiPart.ContentSourcePart(FORSENDELSE_PART_NAME,null,null, content))
    }

    private fun MultiPartRequestContent.addDokumenter(forsendelse: PersonForsendelse, dokumentnavnTilData: Map<String, InputStream>) {
        forsendelse.dokumenter?.forEachIndexed { index, dokument ->
            addDokument(
                dokumentnavn = dokument.filnavn,
                contentType = dokument.mimeType,
                data = dokumentnavnTilData[dokument.filnavn] ?: throw ManglendeDataException("Fant ikke input stream for dokument ${dokument.filnavn}"),
                filnr = index
            )
        }
    }

    private fun MultiPartRequestContent.addDokumenter(forsendelse: OrganisasjonForsendelse, dokumentnavnTilData: Map<String, InputStream>) {
        forsendelse.dokumenter?.forEachIndexed { index, dokument ->
            addDokument(
                dokumentnavn = dokument.filnavn,
                contentType = dokument.mimeType,
                data = dokumentnavnTilData[dokument.filnavn] ?: throw ManglendeDataException("Fant ikke input stream for dokument ${dokument.filnavn}"),
                filnr = index
            )
        }
    }

    private fun MultiPartRequestContent.addDokument(dokumentnavn: String, contentType: String, data: InputStream, filnr: Int) {
        addPart(
            MultiPart.ContentSourcePart(
                "${FILER_PART_NAME}_$filnr",
                dokumentnavn,
                HttpFields.build().add(HttpHeader.CONTENT_TYPE, contentType),
                InputStreamRequestContent(data),
            )
        )
    }
}