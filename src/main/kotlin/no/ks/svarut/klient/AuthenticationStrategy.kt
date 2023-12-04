package no.ks.svarut.klient

import org.eclipse.jetty.client.Request


interface AuthenticationStrategy {
    fun setAuthenticationHeaders(request: Request)
}
