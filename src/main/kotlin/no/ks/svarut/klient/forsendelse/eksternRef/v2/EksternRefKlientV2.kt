package no.ks.svarut.klient.forsendelse.eksternRef.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.forsendelse.eksternRef.model.v2.EksternRefOppslagMetadataResponse
import no.ks.fiks.svarut.forsendelse.eksternRef.model.v2.EksternRefOppslagResponse
import no.ks.fiks.svarut.forsendelse.eksternRef.model.v2.Forsendelse
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.HttpConfiguration
import org.eclipse.jetty.client.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "/api/v2"

private const val PARAM_EKSTERN_REF = "eksternRef"

class EksternRefKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>,
    httpConfig: HttpConfiguration = HttpConfiguration(),
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor, httpConfig) {

    private fun pathFinnForsendelserKnyttetTilEksternRef(kontoId: UUID) = "$BASE_PATH/kontoer/$kontoId/forsendelser"
    private fun pathFinnForsendelserKnyttetTilEksternRefMedMetadata(kontoId: UUID) = "${pathFinnForsendelserKnyttetTilEksternRef(kontoId)}/metadata"

    fun finnForsendelserKnyttetTilEksternRef(kontoId: UUID, eksternRef: String): List<String> =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathFinnForsendelserKnyttetTilEksternRef(kontoId))
            .param(PARAM_EKSTERN_REF, eksternRef)
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw objectMapper.bodyToException(response.contentAsString)
                } else {
                    objectMapper.readValue<EksternRefOppslagResponse>(response.contentAsString)
                        .forsendelseIds
                }
            }

    fun finnForsendelserKnyttetTilEksternRefMedMetadata(kontoId: UUID, eksternRef: String): List<Forsendelse> =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathFinnForsendelserKnyttetTilEksternRefMedMetadata(kontoId))
            .param(PARAM_EKSTERN_REF, eksternRef)
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw objectMapper.bodyToException(response.contentAsString)
                } else {
                    objectMapper.readValue<EksternRefOppslagMetadataResponse>(response.contentAsString)
                        .forsendelser
                }
            }
}
