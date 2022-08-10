package no.ks.svarut.klient.forsendelse.historikk.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.forsendelse.historikk.model.v1.Hendelse
import no.ks.fiks.svarut.forsendelse.historikk.model.v1.Hendelser
import no.ks.fiks.svarut.forsendelse.historikk.model.v1.Signeringshendelse
import no.ks.fiks.svarut.forsendelse.historikk.model.v1.Signeringshendelser
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "/tjenester/api/v2/forsendelser"

class HistorikkKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentHendelser(forsendelseId: UUID) = "$BASE_PATH/$forsendelseId/hendelser"
    private fun pathHentSigneringshendelser(forsendelseId: UUID) = "$BASE_PATH/$forsendelseId/signeringshendelser"

    fun hentHendelser(forsendelseId: UUID): List<Hendelse> =
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

    fun hentSigneringshendelser(forsendelseId: UUID): List<Signeringshendelse> =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentSigneringshendelser(forsendelseId))
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
