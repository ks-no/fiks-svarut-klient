package no.ks.svarut.klient.forsendelse.status.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import no.ks.svarut.model.forsendelse.status.v1.ForsendelseIds
import no.ks.svarut.model.forsendelse.status.v1.Forsendelsestatus
import no.ks.svarut.model.forsendelse.status.v1.Forsendelsestatuser
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.client.util.StringContentProvider
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "tjenester/forsendelse/api/v1/status"

class StatusKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentStatuser(kontoId: UUID) = "$BASE_PATH/$kontoId/sok"

    fun hentStatus(kontoId: UUID, forsendelseId: UUID): Forsendelsestatus? =
        hentStatuser(kontoId, listOf(forsendelseId)).singleOrNull()

    fun hentStatuser(kontoId: UUID, forsendelseIds: List<UUID>): List<Forsendelsestatus> =
        newRequest()
            .method(HttpMethod.POST)
            .path(pathHentStatuser(kontoId))
            .content(StringContentProvider(objectMapper.writeValueAsString(ForsendelseIds(forsendelseIds))), "application/json")
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                } else {
                    objectMapper.readValue<Forsendelsestatuser>(response.contentAsString)
                        .statuser
                }
            }
}