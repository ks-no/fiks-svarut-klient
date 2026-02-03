package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.OrganisasjonForsendelseDokumenterInner
import no.ks.fiks.svarut.forsendelse.send.model.v3.Metadata

class OrganisasjonDokumentBuilder {

    private var filnavn: String? = null
    private var mimeType: String? = null
    private var dokumentType: String? = null
    private var giroarkSide: Int? = null
    private var skalEkskluderesFraUtskrift: Boolean? = false
    private var ekstraMetadata: List<Metadata>? = null
    private var inneholderPersonsensitivInformasjon: Boolean? = false

    fun filnavn(filnavn: String) = apply { this.filnavn = filnavn }
    fun mimeType(mimeType: String) = apply { this.mimeType = mimeType }
    fun dokumentType(dokumentType: String?) = apply { this.dokumentType = dokumentType }
    fun giroarkSide(giroarkSide: Int?) = apply { if (giroarkSide != null && giroarkSide < 1) throw IllegalArgumentException("Giroarkside må vere et heltall større enn 0") else this.giroarkSide = giroarkSide }
    fun skalEkskluderesFraUtskrift(skalEkskluderesFraUtskrift: Boolean?) = apply { this.skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift }
    fun ekstraMetadata(ekstraMetadata: List<Metadata>?) = apply { this.ekstraMetadata = ekstraMetadata }
    fun inneholderPersonsensitivInformasjon(inneholderPersonsensitivInformasjon: Boolean?) = apply { this.inneholderPersonsensitivInformasjon = inneholderPersonsensitivInformasjon }

    fun build() = OrganisasjonForsendelseDokumenterInner(
        filnavn = filnavn ?: throw IllegalArgumentException("Dokument må ha filnavn"),
        mimeType = mimeType ?: throw IllegalArgumentException("Mimetype på dokumentet må være satt"),
        dokumentType = dokumentType,
        giroarkSide = giroarkSide,
        skalEkskluderesFraUtskrift = skalEkskluderesFraUtskrift,
        ekstraMetadata = ekstraMetadata?.toSet(),
        inneholderPersonsensitivInformasjon = inneholderPersonsensitivInformasjon
    )
}
