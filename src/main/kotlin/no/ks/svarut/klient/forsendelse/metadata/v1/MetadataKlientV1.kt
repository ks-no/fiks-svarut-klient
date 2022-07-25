package no.ks.svarut.klient.forsendelse.metadata.v1

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import no.ks.svarut.model.forsendelse.metadata.v1.MetadataList
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "tjenester/forsendelse/api/v1/metadata"

class MetadataKlientV1(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentMetadata(forsendelseId: UUID) = "$BASE_PATH/$forsendelseId/metadata"

    fun hentMetadata(forsendelseId: UUID) =
        newRequest()
            .method(HttpMethod.GET)
            .path(pathHentMetadata(forsendelseId))
            .send()
            .let { response ->
                if (response.status != 200) {
                    throw SvarUtKlientException(objectMapper.readValue(response.contentAsString))
                } else {
                    objectMapper.readValue<MetadataList>(response.contentAsString)
                        .metadata
                }
            }
}