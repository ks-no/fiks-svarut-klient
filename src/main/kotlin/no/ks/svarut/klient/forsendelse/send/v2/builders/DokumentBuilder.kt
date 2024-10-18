package no.ks.svarut.klient.forsendelse.send.v2.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.Dokument

class DokumentBuilder {
    private var dokumentType: String? = null
    private var ekstraMetadata: List<no.ks.fiks.svarut.forsendelse.send.model.v2.Metadata>? = null
    private var filnavn: String? = null
    private var giroarkSider: Set<Int>? = null
    private var inneholderPersonsensitivInformasjon: Boolean? = null
    private var mimeType: String? = null
    private var skalEkskluderesFraUtskrift: Boolean? = null
    private var skalSigneres: Boolean? = null

    fun dokumentType(dokumentType: String?) = apply { this.dokumentType = dokumentType }
    fun ekstraMetadata(ekstraMetadata: List<no.ks.fiks.svarut.forsendelse.send.model.v2.Metadata>?) = apply { this.ekstraMetadata = ekstraMetadata }
    fun filnavn(filnavn: String) = apply { this.filnavn = filnavn }
    fun giroarkSider(giroarkSider: Set<Int>?) = apply { this.giroarkSider = giroarkSider }
    fun inneholderPersonsensitivInformasjon(inneholderPersonsensitivInformasjon: Boolean?) = apply { this.inneholderPersonsensitivInformasjon = inneholderPersonsensitivInformasjon }
    fun mimeType(mimeType: String) = apply { this.mimeType = mimeType }
    fun skalEkskluderesFraUtskrift(skalEkskluderesFraUtskrift: Boolean?) = apply { this.skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift }
    fun skalSigneres(skalSigneres: Boolean?) = apply { this.skalSigneres = skalSigneres }

    fun build() = Dokument(
        dokumentType = dokumentType,
        ekstraMetadata = ekstraMetadata,
        filnavn = filnavn ?: throw IllegalArgumentException("Dokument må ha filnavn"),
        giroarkSider = giroarkSider,
        inneholderPersonsensitivInformasjon = inneholderPersonsensitivInformasjon,
        mimeType = mimeType ?: throw IllegalArgumentException("Mimetype på dokumentet må være satt"),
        skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift,
        skalSigneres = skalSigneres
    )
}