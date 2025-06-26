package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.*
import java.time.OffsetDateTime
import java.util.*

class ForsendelseBuilder {
    private var mottaker: Adresse? = null
    private var tittel: String? = null
    private var eksponertFor: Set<String>? = null
    private var avgivendeSystem: String? = null
    private var konteringskode: String? = null
    private var kunDigitalLevering: Boolean? = false
    private var kryptert: Boolean? = false
    private var utskriftskonfigurasjon: Utskriftskonfigurasjon? = null
    private var krevNiva4Innlogging: Boolean? = false
    private var metadataFraAvleverendeSystem: NoarkMetadataFraAvleverendeSaksSystem? = null
    private var metadataForImport: NoarkMetadataForImport? = null
    private var svarSendesTil: Adresse? = null
    private var svarPaForsendelse: UUID? = null
    private var dokumenter: List<Dokument>? = null
    private var lenker: List<Lenke>? = null
    private var forsendelsestype: String? = null
    private var eksternReferanse: String? = null
    private var korrelasjonsid: String? = null
    private var svarPaForsendelseLink: Boolean? = false
    private var signeringUtloper: OffsetDateTime? = null
    private var signaturType: Forsendelse.SignaturType? = null
    private var taushetsbelagtPost: Boolean? = false

    fun mottaker(mottaker: Adresse) = apply { this.mottaker = mottaker }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun eksponertFor(eksponertFor: Set<String>?) = apply { this.eksponertFor = eksponertFor }
    fun avgivendeSystem(avgivendeSystem: String?) = apply { this.avgivendeSystem = avgivendeSystem }
    fun konteringskode(konteringskode: String?) = apply { this.konteringskode = konteringskode }
    fun kunDigitalLevering(kunDigitalLevering: Boolean?) = apply { this.kunDigitalLevering = kunDigitalLevering }
    fun kryptert(kryptert: Boolean?) = apply { this.kryptert = kryptert }
    fun utskriftskonfigurasjon(utskriftskonfigurasjon: Utskriftskonfigurasjon?) = apply { this.utskriftskonfigurasjon = utskriftskonfigurasjon }
    fun krevNiva4Innlogging(krevNiva4Innlogging: Boolean?) = apply { this.krevNiva4Innlogging = krevNiva4Innlogging }
    fun metadataFraAvleverendeSystem(metadataFraAvleverendeSystem: NoarkMetadataFraAvleverendeSaksSystem?) = apply { this.metadataFraAvleverendeSystem = metadataFraAvleverendeSystem }
    fun metadataForImport(metadataForImport: NoarkMetadataForImport?) = apply { this.metadataForImport = metadataForImport }
    fun svarSendesTil(svarSendesTil: Adresse?) = apply { this.svarSendesTil = svarSendesTil }
    fun svarPaForsendelse(svarPaForsendelse: UUID?) = apply { this.svarPaForsendelse = svarPaForsendelse }
    fun dokumenter(dokumenter: List<Dokument>?) = apply { this.dokumenter = dokumenter }
    fun lenker(lenker: List<Lenke>?) = apply { this.lenker = lenker }
    fun forsendelsestype(forsendelsestype: String?) = apply { this.forsendelsestype = forsendelsestype }
    fun eksternReferanse(eksternReferanse: String?) = apply { this.eksternReferanse = eksternReferanse }
    fun korrelasjonsid(korrelasjonsid: String?) = apply { this.korrelasjonsid = korrelasjonsid }
    fun svarPaForsendelseLink(svarPaForsendelseLink: Boolean?) = apply { this.svarPaForsendelseLink = svarPaForsendelseLink }
    fun signeringUtloper(signeringUtloper: OffsetDateTime?) = apply { this.signeringUtloper = signeringUtloper }
    fun signaturType(signaturType: Forsendelse.SignaturType?) = apply { this.signaturType = signaturType }
    fun taushetsbelagtPost(taushetsbelagtPost: Boolean?) = apply { this.taushetsbelagtPost = taushetsbelagtPost }

    fun build() = Forsendelse(
        mottaker = mottaker ?: throw IllegalArgumentException("Mottaker er ikke satt"),
        tittel = tittel ?: throw IllegalArgumentException("Tittel er ikke satt"),
        eksponertFor = eksponertFor,
        avgivendeSystem = avgivendeSystem,
        konteringskode = konteringskode,
        kunDigitalLevering = kunDigitalLevering,
        kryptert = kryptert,
        utskriftskonfigurasjon = utskriftskonfigurasjon,
        krevNiva4Innlogging = krevNiva4Innlogging,
        metadataFraAvleverendeSystem = metadataFraAvleverendeSystem,
        metadataForImport = metadataForImport,
        svarSendesTil = svarSendesTil,
        svarPaForsendelse = svarPaForsendelse,
        dokumenter = dokumenter,
        lenker = lenker,
        forsendelsestype = forsendelsestype,
        eksternReferanse = eksternReferanse,
        korrelasjonsid = korrelasjonsid,
        svarPaForsendelseLink = svarPaForsendelseLink,
        signeringUtloper = signeringUtloper,
        signaturType = signaturType,
        taushetsbelagtPost = taushetsbelagtPost
    )
}
