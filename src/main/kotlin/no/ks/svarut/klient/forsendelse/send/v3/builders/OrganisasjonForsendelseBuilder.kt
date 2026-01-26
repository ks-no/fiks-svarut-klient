package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.NoarkMetadataFraAvleverendeSakssystem
import no.ks.fiks.svarut.forsendelse.send.model.v3.*
import java.util.*

class OrganisasjonForsendelseBuilder {
    private var mottaker: Mottaker? = null
    private var tittel: String? = null
    private var eksponertFor: Set<String>? = null
    private var avgivendeSystem: String? = null
    private var avgivendeSystemAktor: String? = null
    private var konteringskode: String? = null
    private var kunDigitalLevering: Boolean? = false
    private var kryptert: Boolean? = false
    private var utskriftskonfigurasjon: Utskriftskonfigurasjon? = null
    private var sikkerhetsniva: Sikkerhetsniva? = null
    private var metadataFraAvleverendeSystem: NoarkMetadataFraAvleverendeSakssystem? = null
    private var metadataForImport: NoarkMetadataForImport? = null
    private var svarSendesTil: SvarSendesTil? = null
    private var svarPaForsendelseId: UUID? = null
    private var dokumenter: List<OrganisasjonForsendelseDokumenterInner>? = null
    private var lenker: List<Lenke>? = null
    private var forsendelsestype: String? = null
    private var eksternReferanse: String? = null
    private var korrelasjonsid: String? = null
    private var kanSvareViaEdialog: Boolean? = false
    private var taushetsbelagtPost: Boolean? = false

    fun mottaker(mottaker: Mottaker) = apply { this.mottaker = mottaker }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun eksponertFor(eksponertFor: Set<String>?) = apply { this.eksponertFor = eksponertFor }
    fun avgivendeSystem(avgivendeSystem: String?) = apply { this.avgivendeSystem = avgivendeSystem }
    fun avgivendeSystemAktor(avgivendeSystemAktor: String?) = apply { this.avgivendeSystemAktor = avgivendeSystemAktor }
    fun konteringskode(konteringskode: String?) = apply { this.konteringskode = konteringskode }
    fun kunDigitalLevering(kunDigitalLevering: Boolean?) = apply { this.kunDigitalLevering = kunDigitalLevering }
    fun kryptert(kryptert: Boolean?) = apply { this.kryptert = kryptert }
    fun utskriftskonfigurasjon(utskriftskonfigurasjon: Utskriftskonfigurasjon?) = apply { this.utskriftskonfigurasjon = utskriftskonfigurasjon }
    fun sikkerhetsniva(sikkerhetsniva: Sikkerhetsniva?) = apply { this.sikkerhetsniva = sikkerhetsniva }
    fun metadataFraAvleverendeSystem(metadataFraAvleverendeSystem: NoarkMetadataFraAvleverendeSakssystem?) = apply { this.metadataFraAvleverendeSystem = metadataFraAvleverendeSystem }
    fun metadataForImport(metadataForImport: NoarkMetadataForImport?) = apply { this.metadataForImport = metadataForImport }
    fun svarSendesTil(svarSendesTil: SvarSendesTil?) = apply { this.svarSendesTil = svarSendesTil }
    fun svarPaForsendelse(svarPaForsendelse: UUID?) = apply { this.svarPaForsendelseId = svarPaForsendelse }
    fun dokumenter(dokumenter: List<OrganisasjonForsendelseDokumenterInner>?) = apply { this.dokumenter = dokumenter }
    fun lenker(lenker: List<Lenke>?) = apply { this.lenker = lenker }
    fun forsendelsestype(forsendelsestype: String?) = apply { this.forsendelsestype = forsendelsestype }
    fun eksternReferanse(eksternReferanse: String?) = apply { this.eksternReferanse = eksternReferanse }
    fun korrelasjonsid(korrelasjonsid: String?) = apply { this.korrelasjonsid = korrelasjonsid }
    fun kanSvareViaEdialog(kanSvareViaEdialog: Boolean?) = apply { this.kanSvareViaEdialog = kanSvareViaEdialog }
    fun taushetsbelagtPost(taushetsbelagtPost: Boolean?) = apply { this.taushetsbelagtPost = taushetsbelagtPost }

    fun build() = OrganisasjonForsendelse(
        mottaker = mottaker ?: throw IllegalArgumentException("Mottaker er ikke satt"),
        tittel = tittel ?: throw IllegalArgumentException("Tittel er ikke satt"),
        eksponertFor = eksponertFor,
        avgivendeSystem = avgivendeSystem,
        avgivendeSystemAktor = avgivendeSystemAktor,
        konteringskode = konteringskode,
        kunDigitalLevering = kunDigitalLevering,
        kryptert = kryptert,
        utskriftskonfigurasjon = utskriftskonfigurasjon,
        sikkerhetsniva = sikkerhetsniva,
        metadataFraAvleverendeSystem = metadataFraAvleverendeSystem,
        metadataForImport = metadataForImport,
        svarSendesTil = svarSendesTil,
        svarPaForsendelseId = svarPaForsendelseId,
        dokumenter = dokumenter,
        lenker = lenker,
        forsendelsestype = forsendelsestype,
        eksternReferanse = eksternReferanse,
        korrelasjonsid = korrelasjonsid,
        kanSvareViaEdialog = kanSvareViaEdialog,
        taushetsbelagtPost = taushetsbelagtPost
    )
}
