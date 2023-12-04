package no.ks.svarut.klient.forsendelse.metadata.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.forsendelse.metadata.model.v2.DokumentMetadata
import no.ks.fiks.svarut.forsendelse.metadata.model.v2.ForsendelseMetadata
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import org.eclipse.jetty.client.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "/api/v2"

class MetadataKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentMetadata(forsendelseId: UUID) = "$BASE_PATH/forsendelser/$forsendelseId/metadata"

    fun hentMetadata(forsendelseId: UUID): List<DokumentMetadata> =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentMetadata(forsendelseId))
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw objectMapper.bodyToException(response.contentAsString)
                } else {
                    objectMapper.readValue<ForsendelseMetadata>(response.contentAsString)
                        .dokumenter
                }
            }
}