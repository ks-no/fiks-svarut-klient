package no.ks.svarut.klient.forsendelse.lest.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.forsendelse.lest.model.v2.LestAv
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.client.util.StringContentProvider
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "/api/v2"

class LestKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathSettForsendelseLestAvEksterntSystem(forsendelseId: UUID) = "$BASE_PATH/forsendelser/$forsendelseId/lest"

    fun settForsendelseLestAvEksterntSystem(forsendelseId: UUID, lestAv: LestAv) {
        return newRequest()
            .method(HttpMethod.POST)
            .path(pathSettForsendelseLestAvEksterntSystem(forsendelseId))
            .content(StringContentProvider(objectMapper.writeValueAsString(lestAv)), "application/json")
            .send()
            .let { response ->
                if (response.status != 204) {
                    throw objectMapper.bodyToException(response.contentAsString)
                }
            }
    }
}