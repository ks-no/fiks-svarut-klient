package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.*
import java.util.*

class PrintForsendelseBuilder {
    private var tittel: String? = null
    private val mottaker: Postadresse? = null
    private var avgivendeSystem: String? = null
    private var avgivendeSystemAktor: String? = null
    private var konteringskode: String? = null
    private var kryptert: Boolean? = false
    private var utskriftskonfigurasjon: Utskriftskonfigurasjon? = null
    private var svarPaForsendelseId: UUID? = null
    private var svarSendesTil: SvarSendesTil? = null
    private var eksternReferanse: String? = null
    private var korrelasjonsid: String? = null
    private var dokumenter: List<PrintForsendelseDokumenterInner>? = null

    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun avgivendeSystem(avgivendeSystem: String?) = apply { this.avgivendeSystem = avgivendeSystem }
    fun konteringskode(konteringskode: String?) = apply { this.konteringskode = konteringskode }
    fun kryptert(kryptert: Boolean?) = apply { this.kryptert = kryptert }
    fun utskriftskonfigurasjon(utskriftskonfigurasjon: Utskriftskonfigurasjon?) =
        apply { this.utskriftskonfigurasjon = utskriftskonfigurasjon }

    fun svarPaForsendelse(svarPaForsendelse: UUID?) = apply { this.svarPaForsendelseId = svarPaForsendelse }
    fun eksternReferanse(eksternReferanse: String?) = apply { this.eksternReferanse = eksternReferanse }
    fun korrelasjonsid(korrelasjonsid: String?) = apply { this.korrelasjonsid = korrelasjonsid }

    fun build() = PrintForsendelse(
        tittel = tittel ?: throw IllegalArgumentException("Tittel er ikke satt"),
        mottaker = mottaker ?: throw IllegalArgumentException("Mottaker er ikke satt"),
        avgivendeSystem = avgivendeSystem,
        avgivendeSystemAktor = avgivendeSystemAktor,
        konteringskode = konteringskode,
        kryptert = kryptert,
        utskriftskonfigurasjon = utskriftskonfigurasjon,
        svarSendesTil = svarSendesTil,
        svarPaForsendelseId = svarPaForsendelseId,
        eksternReferanse = eksternReferanse,
        korrelasjonsid = korrelasjonsid,
        dokumenter = dokumenter
    )
}
