package no.ks.svarut.klient

import no.ks.fiks.maskinporten.AccessTokenRequest
import no.ks.fiks.maskinporten.Maskinportenklient
import org.eclipse.jetty.client.Request
import java.util.*

class IntegrasjonAuthenticationStrategy(
    private val maskinportenklient: Maskinportenklient,
    private val integrasjonId: UUID,
    private val integrasjonPassord: String,
) : AuthenticationStrategy {

    override fun setAuthenticationHeaders(request: Request) {
        request.headers { headers ->
            headers
                .add("Authorization", "Bearer ${getAccessToken()}")
                .add("IntegrasjonId", integrasjonId.toString())
                .add("IntegrasjonPassord", integrasjonPassord)
        }
    }

    private fun getAccessToken(): String = maskinportenklient.getAccessToken(AccessTokenRequest.builder().scopes(mutableSetOf("ks:fiks")).build())
}
