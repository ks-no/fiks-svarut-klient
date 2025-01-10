package no.ks.svarut.klient.mottakersystem.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.mottakersystem.model.v2.Mottakersystem
import no.ks.fiks.svarut.mottakersystem.model.v2.Mottakersystemer
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.HttpConfiguration
import org.eclipse.jetty.client.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.function.Function

private const val BASE_PATH = "/api/v2/mottakersystem"

private const val PARAM_ORGANISASJONSNUMMER = "organisasjonsnummer"
private const val PARAM_FORSENDELSE_TYPE = "forsendelseType"
private const val PARAM_NIVA = "niva"

class MottakersystemKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>,
    httpConfig: HttpConfiguration = HttpConfiguration(),
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor, httpConfig) {

    private fun pathHentMottakersystemForOrgnr() = BASE_PATH

    fun hentMottakersystem(
        organisasjonsnummer: String? = null,
        forsendelseType: String? = null,
        niva: Int? = null,
    ): List<Mottakersystem> =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentMottakersystemForOrgnr())
            .also { request ->
                organisasjonsnummer?.run { request.param(PARAM_ORGANISASJONSNUMMER, this) }
                forsendelseType?.run { request.param(PARAM_FORSENDELSE_TYPE, this) }
                niva?.run { request.param(PARAM_NIVA, this.toString()) }
            }
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw objectMapper.bodyToException(response.contentAsString)
                } else {
                    objectMapper.readValue<Mottakersystemer>(response.contentAsString)
                        .mottakersystemer
                }
            }
}