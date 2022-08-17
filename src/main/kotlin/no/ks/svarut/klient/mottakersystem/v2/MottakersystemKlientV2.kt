package no.ks.svarut.klient.mottakersystem.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.mottakersystem.model.v2.Mottakersystem
import no.ks.fiks.svarut.mottakersystem.model.v2.Mottakersystemer
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.function.Function

private const val BASE_PATH = "/tjenester/api/v2/mottakersystem"

private const val PARAM_ORGNR = "organisasjonsnummer"

class MottakersystemKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentMottakersystemForOrgnr() = BASE_PATH

    fun hentMottakersystemForOrgnr(organisasjonsnummer: String): List<Mottakersystem> =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentMottakersystemForOrgnr())
            .param(PARAM_ORGNR, organisasjonsnummer)
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                } else {
                    objectMapper.readValue<Mottakersystemer>(response.contentAsString)
                        .mottakersystemer
                }
            }
}