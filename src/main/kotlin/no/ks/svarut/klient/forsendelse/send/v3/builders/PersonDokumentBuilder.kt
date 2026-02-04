package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.PersonForsendelseDokumenterInner

class PersonDokumentBuilder {

    private var filnavn: String? = null
    private var mimeType: String? = null
    private var skalSigneres: Boolean? = false
    private var giroarkSide: Int? = null
    private var skalEkskluderesFraUtskrift: Boolean? = false

    fun filnavn(filnavn: String) = apply { this.filnavn = filnavn }
    fun mimeType(mimeType: String) = apply { this.mimeType = mimeType }
    fun skalSigneres(skalSigneres: Boolean?) = apply { this.skalSigneres = skalSigneres }
    fun giroarkSide(giroarkSide: Int?) = apply { if (giroarkSide != null && giroarkSide < 1) throw IllegalArgumentException("Giroarkside må vere et heltall større enn 0") else this.giroarkSide = giroarkSide }
    fun skalEkskluderesFraUtskrift(skalEkskluderesFraUtskrift: Boolean?) = apply { this.skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift }

    fun build() = PersonForsendelseDokumenterInner(
        filnavn = filnavn ?: throw IllegalArgumentException("Dokument må ha filnavn"),
        mimeType = mimeType ?: throw IllegalArgumentException("Mimetype på dokumentet må være satt"),
        skalSigneres = skalSigneres,
        giroarkSide = giroarkSide,
        skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift,
    )
}
