package no.ks.svarut.klient

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.eclipse.jetty.client.HttpClient
import org.eclipse.jetty.client.api.Request
import org.eclipse.jetty.util.ssl.SslContextFactory
import java.io.Closeable
import java.util.function.Function


abstract class BaseKlient(
    private val baseUrl: String,
    private val authenticationStrategy: AuthenticationStrategy,
    private val requestInterceptor: Function<Request, Request>
) : Closeable {

    internal val client = HttpClient(SslContextFactory.Client())
    internal val objectMapper = ObjectMapper()
        .registerKotlinModule()
        .registerModule(JavaTimeModule())
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    init {
        try {
            client.start()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    internal fun newRequest() =
        requestInterceptor.apply(
            client.newRequest(baseUrl)
                .onRequestBegin(authenticationStrategy::setAuthenticationHeaders)
        )

    override fun close() {
        try {
            client.stop()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
