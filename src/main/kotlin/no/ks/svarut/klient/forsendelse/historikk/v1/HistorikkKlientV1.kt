package no.ks.svarut.klient.forsendelse.historikk.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import no.ks.svarut.model.forsendelse.historikk.v1.Hendelser
import no.ks.svarut.model.forsendelse.historikk.v1.Signeringshendelser
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "tjenester/forsendelse/api/v1/historikk"

class HistorikkKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentHendelser(forsendelseId: UUID) = "$BASE_PATH/$forsendelseId/hendelser"
    private fun pathHentSigneringshistorikk(forsendelseId: UUID) = "$BASE_PATH/$forsendelseId/signering"

    fun hentHendelser(forsendelseId: UUID) =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentHendelser(forsendelseId))
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                } else {
                    objectMapper.readValue<Hendelser>(response.contentAsString)
                        .hendelser
                }
            }

    fun hentSigneringshistorikk(forsendelseId: UUID) =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentSigneringshistorikk(forsendelseId))
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                } else {
                    objectMapper.readValue<Signeringshendelser>(response.contentAsString)
                        .historikk
                }
            }
}