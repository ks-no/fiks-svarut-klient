package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.Utskriftskonfigurasjon

class UtskriftskonfigurasjonBuilder {

    private var tosidig: Boolean? = false

    fun tosidig(tosidig: Boolean?) = apply { this.tosidig = tosidig }

    fun build() = Utskriftskonfigurasjon(
        tosidig = tosidig
    )

}
