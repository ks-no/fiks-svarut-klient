package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.PersonForsendelseDokumenterInner

class PersonDokumentBuilder {

    private var filnavn: String? = null
    private var mimeType: String? = null
    private var skalSigneres: Boolean? = false
    private var giroarkSider: Set<Int>? = null
    private var skalEkskluderesFraUtskrift: Boolean? = false

    fun filnavn(filnavn: String) = apply { this.filnavn = filnavn }
    fun mimeType(mimeType: String) = apply { this.mimeType = mimeType }
    fun skalSigneres(skalSigneres: Boolean?) = apply { this.skalSigneres = skalSigneres }
    fun giroarkSider(giroarkSider: Set<Int>?) = apply { this.giroarkSider = giroarkSider }
    fun skalEkskluderesFraUtskrift(skalEkskluderesFraUtskrift: Boolean?) = apply { this.skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift }

    fun build() = PersonForsendelseDokumenterInner(
        filnavn = filnavn ?: throw IllegalArgumentException("Dokument må ha filnavn"),
        mimeType = mimeType ?: throw IllegalArgumentException("Mimetype på dokumentet må være satt"),
        skalSigneres = skalSigneres,
        giroarkSider = giroarkSider,
        skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift,
    )
}
