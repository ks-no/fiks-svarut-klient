package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.*
import java.util.*

class PersonforsendelseBuilder {
    private var mottaker: Mottaker? = null
    private var tittel: String? = null
    private var eksponertFor: Set<String>? = null
    private var avgivendeSystem: String? = null
    private var konteringskode: String? = null
    private var kunDigitalLevering: Boolean? = false
    private var kryptert: Boolean? = false
    private var utskriftskonfigurasjon: Utskriftskonfigurasjon? = null
    private var sikkerhetsniva: Sikkerhetsniva? = Sikkerhetsniva.BETYDELIG
    private var svarSendesTil: SvarSendesTil? = null
    private var svarPaForsendelseId: UUID? = null
    private var dokumenter: List<PersonForsendelseDokumenterInner>? = null
    private var lenker: List<Lenke>? = null
    private var forsendelsestype: String? = null
    private var eksternReferanse: String? = null
    private var korrelasjonsid: String? = null
    private var kanSvarPaForsendelse: Boolean? = false
    private var signering: PersonForsendelseSignering? = null

    fun mottaker(mottaker: Mottaker) = apply { this.mottaker = mottaker }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun eksponertFor(eksponertFor: Set<String>?) = apply { this.eksponertFor = eksponertFor }
    fun avgivendeSystem(avgivendeSystem: String?) = apply { this.avgivendeSystem = avgivendeSystem }
    fun konteringskode(konteringskode: String?) = apply { this.konteringskode = konteringskode }
    fun kunDigitalLevering(kunDigitalLevering: Boolean?) = apply { this.kunDigitalLevering = kunDigitalLevering }
    fun kryptert(kryptert: Boolean?) = apply { this.kryptert = kryptert }
    fun utskriftskonfigurasjon(utskriftskonfigurasjon: Utskriftskonfigurasjon?) = apply { this.utskriftskonfigurasjon = utskriftskonfigurasjon }
    fun svarSendesTil(svarSendesTil: SvarSendesTil?) = apply { this.svarSendesTil = svarSendesTil }
    fun svarPaForsendelseId(svarPaForsendelseId: UUID?) = apply { this.svarPaForsendelseId = svarPaForsendelseId }
    fun dokumenter(dokumenter: List<PersonForsendelseDokumenterInner>?) = apply { this.dokumenter = dokumenter }
    fun lenker(lenker: List<Lenke>?) = apply { this.lenker = lenker }
    fun forsendelsestype(forsendelsestype: String?) = apply { this.forsendelsestype = forsendelsestype }
    fun eksternReferanse(eksternReferanse: String?) = apply { this.eksternReferanse = eksternReferanse }
    fun korrelasjonsid(korrelasjonsid: String?) = apply { this.korrelasjonsid = korrelasjonsid }
    fun svarPaForsendelse(svarPaForsendelse: Boolean?) = apply { this.kanSvarPaForsendelse = svarPaForsendelse }
    fun signering(signering: PersonForsendelseSignering) = apply { this.signering = signering }

    fun build() = PersonForsendelse(
        mottaker = mottaker ?: throw IllegalArgumentException("Mottaker er ikke satt"),
        tittel = tittel ?: throw IllegalArgumentException("Tittel er ikke satt"),
        eksponertFor = eksponertFor,
        avgivendeSystem = avgivendeSystem,
        konteringskode = konteringskode,
        kunDigitalLevering = kunDigitalLevering,
        kryptert = kryptert,
        utskriftskonfigurasjon = utskriftskonfigurasjon,
        sikkerhetsniva = sikkerhetsniva,
        signering = signering,
        svarSendesTil = svarSendesTil,
        svarPaForsendelseId = svarPaForsendelseId,
        dokumenter = dokumenter,
        lenker = lenker,
        eksternReferanse = eksternReferanse,
        korrelasjonsid = korrelasjonsid,
        kanSvarePaForsendelse = kanSvarPaForsendelse,
    )
}
