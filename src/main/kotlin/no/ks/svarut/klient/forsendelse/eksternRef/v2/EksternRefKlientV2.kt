package no.ks.svarut.klient.forsendelse.eksternRef.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.forsendelse.eksternRef.model.v2.EksternRefOppslag
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "/api/v2"

private const val PARAM_EKSTERN_REF = "eksternRef"

class EksternRefKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathFinnForsendelserKnyttetTilEksternRef(kontoId: UUID) = "$BASE_PATH/kontoer/$kontoId/forsendelser/"

    fun finnForsendelserKnyttetTilEksternRef(kontoId: UUID, eksternRef: String): List<String> =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathFinnForsendelserKnyttetTilEksternRef(kontoId))
            .param(PARAM_EKSTERN_REF, eksternRef)
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                } else {
                    objectMapper.readValue<EksternRefOppslag>(response.contentAsString)
                        .forsendelseIds
                }
            }
}
