package no.ks.svarut.klient

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.eclipse.jetty.client.HttpClient
import org.eclipse.jetty.client.Request
import org.eclipse.jetty.client.transport.HttpClientTransportDynamic
import org.eclipse.jetty.io.ClientConnector
import org.eclipse.jetty.util.ssl.SslContextFactory
import java.io.Closeable
import java.util.function.Function
import java.time.Duration


abstract class BaseKlient(
    private val baseUrl: String,
    private val authenticationStrategy: AuthenticationStrategy,
    private val requestInterceptor: Function<Request, Request>,
    private val idleTimeoutDuration: Duration? = null
) : Closeable {

    internal val client = HttpClient(
        HttpClientTransportDynamic(
        ClientConnector().apply {
            sslContextFactory = SslContextFactory.Client()
            idleTimeoutDuration?.let {
                idleTimeout = it
            }
        }))
    internal val objectMapper = ObjectMapper().registerKotlinModule().registerModule(JavaTimeModule())
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)

    init {
        try {
            client.start()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    internal fun newRequest() = requestInterceptor.apply(
        client.newRequest(baseUrl).onRequestBegin(authenticationStrategy::setAuthenticationHeaders)
    )

    override fun close() {
        try {
            client.stop()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun ObjectMapper.bodyToException(body: String): Exception = try {
        SvarUtKlientException(objectMapper.readValue(body))
    } catch (e: Exception) {
        RuntimeException("Uventet feil. Response body: $body")
    }
}
