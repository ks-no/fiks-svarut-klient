package no.ks.svarut.klient.forsendelse.typer.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.forsendelse.typer.model.v2.Forsendelsestyper
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.function.Function

private const val BASE_PATH = "/tjenester/api/v2/forsendelsestyper"

class TyperKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentForsendelseTyper() = BASE_PATH

    fun hentForsendelsestyper(): Set<String> =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentForsendelseTyper())
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                } else {
                    objectMapper.readValue<Forsendelsestyper>(response.contentAsString)
                        .forsendelsestyper
                }
            }
}