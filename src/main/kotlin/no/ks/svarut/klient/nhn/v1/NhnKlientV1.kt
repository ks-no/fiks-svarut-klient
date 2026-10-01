package no.ks.svarut.klient.nhn.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.nhn.model.v1.CommunicationParty
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.HttpConfiguration
import org.eclipse.jetty.client.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.function.Function

private const val BASE_PATH = "/api/v1/nhn"

class NhnKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>,
    httpConfig: HttpConfiguration = HttpConfiguration(),
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor, httpConfig) {

    private fun pathAdresseregisterOppslag(herId: Int) = "$BASE_PATH/oppslag/$herId"

    fun adresseregisterOppslag(herId: Int): CommunicationParty =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathAdresseregisterOppslag(herId))
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw bodyToException(response.contentAsString)
                } else {
                    objectMapper.readValue<CommunicationParty>(response.contentAsString)
                }
            }
}
