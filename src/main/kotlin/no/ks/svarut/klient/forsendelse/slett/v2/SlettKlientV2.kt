package no.ks.svarut.klient.forsendelse.slett.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "/tjenester/api/v2"

private const val PARAM_SLETTETYPE = "slettetype"
private const val SLETTETYPE_ORIGINALDOKUMENTER = "ORIGINALDOKUMENTER"
private const val SLETTETYPE_ALLE = "ALLE"

class SlettKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathSlettFiler(forsendelseId: UUID) = "$BASE_PATH/forsendelser/$forsendelseId/dokumenter"

    fun slettOriginalDokumenter(forsendelseId: UUID) = slettFiler(forsendelseId, SLETTETYPE_ORIGINALDOKUMENTER)

    fun slettAlleFiler(forsendelseId: UUID) = slettFiler(forsendelseId, SLETTETYPE_ALLE)

    private fun slettFiler(forsendelseId: UUID, slettetype: String) =
        newRequest()
            .method(HttpMethod.DELETE)
            .path(pathSlettFiler(forsendelseId))
            .param(PARAM_SLETTETYPE, slettetype)
            .send()
            .let { response ->
                if (response.status != 202) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                }
            }
}