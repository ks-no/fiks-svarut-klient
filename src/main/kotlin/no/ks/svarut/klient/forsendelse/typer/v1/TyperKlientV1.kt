package no.ks.svarut.klient.forsendelse.typer.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import no.ks.svarut.model.forsendelse.typer.v1.Forsendelsetyper
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.function.Function

private const val BASE_PATH = "tjenester/forsendelse/api/v1/typer"

class TyperKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentForsendelseTyper() = BASE_PATH

    fun hentForsendelseTyper() =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentForsendelseTyper())
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                } else {
                    objectMapper.readValue<Forsendelsetyper>(response.contentAsString)
                        .forsendelsetyper
                }
            }
}