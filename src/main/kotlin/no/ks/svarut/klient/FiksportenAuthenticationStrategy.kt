package no.ks.svarut.klient

import no.ks.fiks.fiksporten.klient.FiksportenKlient
import org.eclipse.jetty.client.Request
import no.ks.fiks.fiksporten.klient.AccessTokenRequest
import java.util.*

class FiksportenAuthenticationStrategy(
    private val fiksportenKlient: FiksportenKlient,
    private val clientId: UUID,
) : AuthenticationStrategy {

    override fun setAuthenticationHeaders(request: Request) {
        request.headers { headers ->
            headers.add("Authorization", "Bearer ${createFiksportenAccessToken()}")
        }
    }
    private fun createFiksportenAccessToken() =
        fiksportenKlient.getAccessToken(AccessTokenRequest(clientId, setOf("ks:fiks")))
}
