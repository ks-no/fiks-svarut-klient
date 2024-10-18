package no.ks.svarut.klient.forsendelse.send.v2.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.Utskriftskonfigurasjon

class UtskriftskonfigurasjonBuilder {
    private var tosidig: Boolean? = false
    private var utskriftMedFarger: Boolean? = false

    fun tosidig(tosidig: Boolean?) = apply { this.tosidig = tosidig }
    fun utskriftMedFarger(utskriftMedFarger: Boolean?) = apply { this.utskriftMedFarger = utskriftMedFarger }

    fun build() = Utskriftskonfigurasjon(
        tosidig = tosidig,
        utskriftMedFarger = utskriftMedFarger
    )
}