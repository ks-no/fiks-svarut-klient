package no.ks.svarut.klient.forsendelse.eksternRef.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import no.ks.svarut.model.forsendelse.eksternRef.v1.EksternRefOppslag
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "tjenester/forsendelse/api/v1/oppslag/ekstern-ref"

class EksternRefKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathFinnForsendelserKnyttetTilEksternRef(kontoId: UUID, eksternRef: String) = "$BASE_PATH/$kontoId/$eksternRef"

    fun finnForsendelserKnyttetTilEksternRef(kontoId: UUID, eksternRef: String): List<String> =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathFinnForsendelserKnyttetTilEksternRef(kontoId, eksternRef))
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

