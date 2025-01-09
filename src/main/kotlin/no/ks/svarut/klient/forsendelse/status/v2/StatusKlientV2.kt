package no.ks.svarut.klient.forsendelse.status.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.forsendelse.status.model.v2.ForsendelseStatus
import no.ks.fiks.svarut.forsendelse.status.model.v2.ForsendelseStatuser
import no.ks.fiks.svarut.forsendelse.status.model.v2.StatusSok
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.HttpConfiguration
import org.eclipse.jetty.client.Request
import org.eclipse.jetty.client.StringRequestContent
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "/api/v2"

class StatusKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>,
    httpConfig: HttpConfiguration = HttpConfiguration(),
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor, httpConfig) {

    private fun pathHentStatuser(kontoId: UUID) = "$BASE_PATH/kontoer/$kontoId/forsendelser/status-sok"

    fun hentStatus(kontoId: UUID, forsendelseId: UUID): ForsendelseStatus? =
        hentStatuser(kontoId, listOf(forsendelseId)).singleOrNull()

    fun hentStatuser(kontoId: UUID, forsendelseIds: List<UUID>): List<ForsendelseStatus> =
        newRequest()
            .method(HttpMethod.POST)
            .path(pathHentStatuser(kontoId))
            .body(StringRequestContent("application/json", objectMapper.writeValueAsString(StatusSok(forsendelseIds))))
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw objectMapper.bodyToException(response.contentAsString)
                } else {
                    objectMapper.readValue<ForsendelseStatuser>(response.contentAsString)
                        .statuser
                }
            }
}