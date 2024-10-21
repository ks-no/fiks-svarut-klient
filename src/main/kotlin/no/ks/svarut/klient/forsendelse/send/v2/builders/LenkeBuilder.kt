package no.ks.svarut.klient.forsendelse.send.v2.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.Lenke

class LenkeBuilder {

    private var url: String? = null
    private var tekst: String? = null
    private var ledetekst: String? = null

    fun url(url: String) = apply { this.url = url }
    fun tekst(tekst: String) = apply { this.tekst = tekst }
    fun ledetekst(ledetekst: String?) = apply { this.ledetekst = ledetekst }

    fun build() = Lenke(
        url = url ?: throw IllegalArgumentException("Url er ikke satt"),
        tekst = tekst ?: throw IllegalArgumentException("Tekst er ikke satt"),
        ledetekst = ledetekst
    )

}
