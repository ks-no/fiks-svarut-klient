package no.ks.svarut.klient

import java.time.Duration

data class HttpConfiguration(val idleTimeout: Duration? = null) {
    companion object {
        fun builder() = Builder()
    }

    class Builder {
        private var idleTimeout: Duration? = null

        fun idleTimeout(idleTimeout: Duration?) = apply { this.idleTimeout = idleTimeout }

        fun build() = HttpConfiguration(idleTimeout)
    }
}
