package no.ks.svarut.klient.forsendelse.send.v2.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.Lenke

class LenkeBuilder {
    private var ledetekst: String? = null
    private var tekst: String? = null
    private var url: String? = null

    fun ledetekst(ledetekst: String?) = apply { this.ledetekst = ledetekst }
    fun tekst(tekst: String) = apply { this.tekst = tekst }
    fun url(url: String) = apply { this.url = url }

    fun build() = Lenke(
        ledetekst = ledetekst,
        tekst = tekst ?: throw IllegalArgumentException("Tekst er ikke satt"),
        url = url ?: throw IllegalArgumentException("Url er ikke satt")
    )
}