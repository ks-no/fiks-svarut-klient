package no.ks.svarut.klient.forsendelse.metadata.v2

import com.fasterxml.jackson.module.kotlin.readValue
import no.ks.fiks.svarut.forsendelse.metadata.model.v1.Metadata
import no.ks.fiks.svarut.forsendelse.metadata.model.v1.MetadataList
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.BaseKlient
import no.ks.svarut.klient.SvarUtKlientException
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.http.HttpMethod
import java.util.*
import java.util.function.Function

private const val BASE_PATH = "/tjenester/api/v2"

class MetadataKlientV2(
    baseUrl: String,
    authenticationStrategy: AuthenticationStrategy,
    requestInterceptor: Function<Request, Request>
) : BaseKlient(baseUrl, authenticationStrategy, requestInterceptor) {

    private fun pathHentMetadata(forsendelseId: UUID) = "$BASE_PATH/forsendelser/$forsendelseId/metadata"

    fun hentMetadata(forsendelseId: UUID): List<Metadata> =
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