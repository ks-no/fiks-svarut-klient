package no.ks.svarut.klient.forsendelse.slett.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import no.ks.svarut.model.forsendelse.slett.v1.SlettFilerRequest
import no.ks.svarut.model.forsendelse.slett.v1.Slettetype
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.client.util.StringContentProvider
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "tjenester/forsendelse/api/v1/slett"

class SlettKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathSlettFiler() = BASE_PATH

    fun slettOriginalDokumenter(forsendelseId: UUID) = slettOriginalDokumenter(listOf(forsendelseId))

    fun slettOriginalDokumenter(forsendelseIds: List<UUID>) = slettFiler(forsendelseIds, Slettetype.ORIGINALDOKUMENTER)

    fun slettAlleFiler(forsendelseId: UUID) = slettAlleFiler(listOf(forsendelseId))

    fun slettAlleFiler(forsendelseIds: List<UUID>) = slettFiler(forsendelseIds, Slettetype.ALLE)

    private fun slettFiler(forsendelseIds: List<UUID>, slettetype: Slettetype) =
        newRequest()
            .method(HttpMethod.POST)
            .path(pathSlettFiler())
            .content(StringContentProvider(objectMapper.writeValueAsString(SlettFilerRequest(forsendelseIds, slettetype))), "application/json")
            .send()
            .let { response ->
                if (response.status != 202) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                }
            }
}