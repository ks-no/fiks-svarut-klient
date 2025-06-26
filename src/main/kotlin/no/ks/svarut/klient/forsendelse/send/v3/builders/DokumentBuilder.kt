package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.Dokument

class DokumentBuilder {

    private var filnavn: String? = null
    private var mimeType: String? = null
    private var skalSigneres: Boolean? = false
    private var dokumentType: String? = null
    private var giroarkSider: Set<Int>? = null
    private var skalEkskluderesFraUtskrift: Boolean? = false
    private var ekstraMetadata: List<no.ks.fiks.svarut.forsendelse.send.model.v2.Metadata>? = null
    private var inneholderPersonsensitivInformasjon: Boolean? = false

    fun filnavn(filnavn: String) = apply { this.filnavn = filnavn }
    fun mimeType(mimeType: String) = apply { this.mimeType = mimeType }
    fun skalSigneres(skalSigneres: Boolean?) = apply { this.skalSigneres = skalSigneres }
    fun dokumentType(dokumentType: String?) = apply { this.dokumentType = dokumentType }
    fun giroarkSider(giroarkSider: Set<Int>?) = apply { this.giroarkSider = giroarkSider }
    fun skalEkskluderesFraUtskrift(skalEkskluderesFraUtskrift: Boolean?) = apply { this.skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift }
    fun ekstraMetadata(ekstraMetadata: List<no.ks.fiks.svarut.forsendelse.send.model.v2.Metadata>?) = apply { this.ekstraMetadata = ekstraMetadata }
    fun inneholderPersonsensitivInformasjon(inneholderPersonsensitivInformasjon: Boolean?) = apply { this.inneholderPersonsensitivInformasjon = inneholderPersonsensitivInformasjon }

    fun build() = Dokument(
        filnavn = filnavn ?: throw IllegalArgumentException("Dokument må ha filnavn"),
        mimeType = mimeType ?: throw IllegalArgumentException("Mimetype på dokumentet må være satt"),
        skalSigneres = skalSigneres,
        dokumentType = dokumentType,
        giroarkSider = giroarkSider,
        skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift,
        ekstraMetadata = ekstraMetadata?.toSet(),
        inneholderPersonsensitivInformasjon = inneholderPersonsensitivInformasjon
    )
}
