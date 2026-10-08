package no.ks.svarut.klient.nhn.v1

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer
import io.kotest.assertions.asClue
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import no.ks.fiks.svarut.nhn.model.v1.AmqpTransportStatus
import no.ks.fiks.svarut.nhn.model.v1.CommunicationParty
import no.ks.fiks.svarut.nhn.model.v1.CommunicationPartyType
import no.ks.fiks.svarut.nhn.model.v1.ErrorMessage
import no.ks.svarut.klient.AuthenticationStrategy
import no.ks.svarut.klient.SvarUtKlientException
import org.eclipse.jetty.client.Request
import java.net.InetSocketAddress
import java.nio.charset.StandardCharsets.UTF_8
import java.util.function.Function

private val objectMapper = jacksonObjectMapper()

class NhnKlientV1Test : StringSpec({

    "adresseregisterOppslag henter communication party for herId" {
        var requestPath: String? = null
        var authorizationHeader: String? = null
        var interceptorHeader: String? = null

        withServerKlient(
            status = 200,
            body = CommunicationParty(
                herId = 12345,
                type = CommunicationPartyType.ORGANIZATION,
                amqpTransportStatus = AmqpTransportStatus.Enabled,
                name = "Oslo Legevakt",
            ),
            onRequest = { exchange ->
                requestPath = exchange.requestURI.path
                authorizationHeader = exchange.requestHeaders.getFirst("Authorization")
                interceptorHeader = exchange.requestHeaders.getFirst("X-Test-Interceptor")
            },
        ) { klient ->
            klient.adresseregisterOppslag(12345).asClue {
                it.herId shouldBe 12345
                it.type shouldBe CommunicationPartyType.ORGANIZATION
                it.amqpTransportStatus shouldBe AmqpTransportStatus.Enabled
                it.name shouldBe "Oslo Legevakt"
            }
        }

        requestPath shouldBe "/api/v1/nhn/oppslag/12345"
        authorizationHeader shouldBe "Bearer test-token"
        interceptorHeader shouldBe "applied"
    }

    "adresseregisterOppslag kaster SvarUtKlientException ved feilrespons" {
        withServerKlient(
            status = 404,
            body = ErrorMessage(
                status = 404,
                message = "Fant ikke communication party for herId 99999",
            ),
            onRequest = {},
        ) { klient ->
            shouldThrow<SvarUtKlientException> {
                klient.adresseregisterOppslag(99999)
            }.errorMessage.asClue {
                it.status shouldBe 404
                it.message shouldBe "Fant ikke communication party for herId 99999"
            }
        }
    }
})

private fun withServerKlient(
    status: Int,
    body: Any,
    onRequest: (HttpExchange) -> Unit,
    test: (NhnKlientV1) -> Unit,
) {
    val server = HttpServer.create(InetSocketAddress(0), 0)
    server.createContext("/") { exchange ->
        onRequest(exchange)
        val responseBody = objectMapper.writeValueAsString(body).toByteArray(UTF_8)
        exchange.responseHeaders.add("Content-Type", "application/json")
        exchange.sendResponseHeaders(status, responseBody.size.toLong())
        exchange.responseBody.use { it.write(responseBody) }
        exchange.close()
    }
    server.start()

    try {
        NhnKlientV1(
            baseUrl = "http://localhost:${server.address.port}",
            authenticationStrategy = object : AuthenticationStrategy {
                override fun setAuthenticationHeaders(request: Request) {
                    request.headers { it.put("Authorization", "Bearer test-token") }
                }
            },
            requestInterceptor = Function { request ->
                request.headers { it.put("X-Test-Interceptor", "applied") }
                request
            },
        ).use(test)
    } finally {
        server.stop(0)
    }
}





