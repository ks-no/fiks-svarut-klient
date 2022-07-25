package no.ks.svarut.klient.forsendelse.send.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import no.ks.svarut.model.forsendelse.send.v1.Forsendelse
import no.ks.svarut.model.forsendelse.send.v1.SendForsendelseResponse
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.client.util.InputStreamContentProvider
import org.eclipse.jetty.client.util.MultiPartContentProvider
import org.eclipse.jetty.client.util.StringContentProvider
import org.eclipse.jetty.http.HttpMethod
import java.io.InputStream
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.function.Function

private const val BASE_PATH = "tjenester/forsendelse/api/v1/send"

class SendKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathSend(kontoId: UUID) = "$BASE_PATH/$kontoId/forsendelser/"

    fun send(kontoId: UUID, forsendelse: Forsendelse, dokumenter: Map<String, InputStream>): UUID {
        val multipart = MultiPartContentProvider().also { multipart ->
            val forsenselseMapped = objectMapper.writeValueAsString(forsendelse)
            multipart.addFieldPart("forsendelse", StringContentProvider(forsenselseMapped), null)
            forsendelse.dokumenter.forEach {
                multipart.addFilePart("filer", it.filnavn, InputStreamContentProvider(dokumenter[it.filnavn]), null)
            }
            multipart.close()
        }

        return newRequest()
            .method(HttpMethod.POST)
            .path(pathSend(kontoId))
            .content(multipart)
            .idleTimeout(16, TimeUnit.MINUTES)
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                } else {
                    objectMapper.readValue<SendForsendelseResponse>(response.contentAsString)
                        .id
                }
            }
    }
}