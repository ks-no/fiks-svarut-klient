package no.ks.svarut.klient.mottakersystem.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import no.ks.svarut.model.mottakersystem.v1.Mottakersystemer
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.function.Function

private const val BASE_PATH = "tjenester/mottakersystem/api/v1"

class MottakersystemKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentMottakersystemForOrgnr(organisasjonsnummer: String) = "$BASE_PATH/$organisasjonsnummer"

    fun hentMottakersystemForOrgnr(organisasjonsnummer: String) =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentMottakersystemForOrgnr(organisasjonsnummer))
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