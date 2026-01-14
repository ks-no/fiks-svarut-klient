package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.PrintForsendelseDokumenterInner

class PrintDokumentBuilder {

    private var filnavn: String? = null
    private var mimeType: String? = null
    private var giroarkSide: Int? = null

    fun filnavn(filnavn: String) = apply { this.filnavn = filnavn }
    fun mimeType(mimeType: String) = apply { this.mimeType = mimeType }
    fun giroarkSide(giroarkSide: Int) = apply { if (giroarkSide < 1) throw IllegalArgumentException("Giroarkside må vere et heltall større enn 0") else this.giroarkSide = giroarkSide }
    fun build() = PrintForsendelseDokumenterInner(
        filnavn = filnavn ?: throw IllegalArgumentException("Dokument må ha filnavn"),
        mimeType = mimeType ?: throw IllegalArgumentException("Mimetype på dokumentet må være satt"),
        giroarkSide = giroarkSide,
    )
}
