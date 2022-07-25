package no.ks.svarut.klient.forsendelse.lest.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import no.ks.svarut.model.forsendelse.lest.v1.LestAv
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.client.util.StringContentProvider
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "tjenester/forsendelse/api/v1/lest"

class LestKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathSettForsendelseLestAvEksterntSystem(forsendelseId: UUID) = "$BASE_PATH/$forsendelseId/lest"

    fun settForsendelseLestAvEksterntSystem(forsendelseId: UUID, lestAv: LestAv) {
        return newRequest()
            .method(HttpMethod.POST)
            .path(pathSettForsendelseLestAvEksterntSystem(forsendelseId))
            .content(StringContentProvider(objectMapper.writeValueAsString(lestAv)), "application/json")
            .send()
            .let { response ->
                if (response.status != 204) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                }
            }
    }
}