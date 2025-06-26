package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.Utskriftskonfigurasjon

class UtskriftskonfigurasjonBuilder {

    private var utskriftMedFarger: Boolean? = false
    private var tosidig: Boolean? = false

    fun utskriftMedFarger(utskriftMedFarger: Boolean?) = apply { this.utskriftMedFarger = utskriftMedFarger }
    fun tosidig(tosidig: Boolean?) = apply { this.tosidig = tosidig }

    fun build() = Utskriftskonfigurasjon(
        utskriftMedFarger = utskriftMedFarger,
        tosidig = tosidig
    )

}
