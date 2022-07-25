package no.ks.svarut.klient

import org.eclipse.jetty.client.api.Request

interface AuthenticationStrategy {
    fun setAuthenticationHeaders(request: Request)
}
