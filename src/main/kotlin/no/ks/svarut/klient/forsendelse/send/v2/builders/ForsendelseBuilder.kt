package no.ks.svarut.klient.forsendelse.send.v2.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.*
import java.time.OffsetDateTime
import java.util.*

class ForsendelseBuilder {
    private var avgivendeSystem: String? = null
    private var dokumenter: List<Dokument>? = null
    private var eksponertFor: Set<String>? = null
    private var eksternReferanse: String? = null
    private var forsendelsestype: String? = null
    private var konteringskode: String? = null
    private var krevNiva4Innlogging: Boolean? = false
    private var kryptert: Boolean? = false
    private var kunDigitalLevering: Boolean? = false
    private var lenker: List<Lenke>? = null
    private var metadataForImport: NoarkMetadataForImport? = null
    private var metadataFraAvleverendeSystem: NoarkMetadataFraAvleverendeSaksSystem? = null
    private var mottaker: Adresse? = null
    private var signaturType: Forsendelse.SignaturType? = null
    private var signeringUtloper: OffsetDateTime? = null
    private var svarPaForsendelse: UUID? = null
    private var svarPaForsendelseLink: Boolean? = false
    private var svarSendesTil: Adresse? = null
    private var taushetsbelagtPost: Boolean? = false
    private var tittel: String? = null
    private var utskriftskonfigurasjon: Utskriftskonfigurasjon? = null

    fun avgivendeSystem(avgivendeSystem: String?) = apply { this.avgivendeSystem = avgivendeSystem }
    fun dokumenter(dokumenter: List<Dokument>?) = apply { this.dokumenter = dokumenter }
    fun eksponertFor(eksponertFor: Set<String>?) = apply { this.eksponertFor = eksponertFor }
    fun eksternReferanse(eksternReferanse: String?) = apply { this.eksternReferanse = eksternReferanse }
    fun forsendelsestype(forsendelsestype: String?) = apply { this.forsendelsestype = forsendelsestype }
    fun konteringskode(konteringskode: String?) = apply { this.konteringskode = konteringskode }
    fun krevNiva4Innlogging(krevNiva4Innlogging: Boolean?) = apply { this.krevNiva4Innlogging = krevNiva4Innlogging }
    fun kryptert(kryptert: Boolean?) = apply { this.kryptert = kryptert }
    fun kunDigitalLevering(kunDigitalLevering: Boolean?) = apply { this.kunDigitalLevering = kunDigitalLevering }
    fun lenker(lenker: List<Lenke>?) = apply { this.lenker = lenker }
    fun metadataForImport(metadataForImport: NoarkMetadataForImport?) = apply { this.metadataForImport = metadataForImport }
    fun metadataFraAvleverendeSystem(metadataFraAvleverendeSystem: NoarkMetadataFraAvleverendeSaksSystem?) = apply { this.metadataFraAvleverendeSystem = metadataFraAvleverendeSystem }
    fun mottaker(mottaker: Adresse?) = apply { this.mottaker = mottaker }
    fun signaturType(signaturType: Forsendelse.SignaturType?) = apply { this.signaturType = signaturType }
    fun signeringUtloper(signeringUtloper: java.time.OffsetDateTime?) = apply { this.signeringUtloper = signeringUtloper }
    fun svarPaForsendelse(svarPaForsendelse: java.util.UUID?) = apply { this.svarPaForsendelse = svarPaForsendelse }
    fun svarPaForsendelseLink(svarPaForsendelseLink: Boolean?) = apply { this.svarPaForsendelseLink = svarPaForsendelseLink }
    fun svarSendesTil(svarSendesTil: Adresse?) = apply { this.svarSendesTil = svarSendesTil }
    fun taushetsbelagtPost(taushetsbelagtPost: Boolean?) = apply { this.taushetsbelagtPost = taushetsbelagtPost }
    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun utskriftskonfigurasjon(utskriftskonfigurasjon: Utskriftskonfigurasjon?) = apply { this.utskriftskonfigurasjon = utskriftskonfigurasjon }

    fun build() = Forsendelse(
        avgivendeSystem = avgivendeSystem,
        dokumenter = dokumenter,
        eksponertFor = eksponertFor,
        eksternReferanse = eksternReferanse,
        forsendelsestype = forsendelsestype,
        konteringskode = konteringskode,
        krevNiva4Innlogging = krevNiva4Innlogging,
        kryptert = kryptert,
        kunDigitalLevering = kunDigitalLevering,
        lenker = lenker,
        metadataForImport = metadataForImport,
        metadataFraAvleverendeSystem = metadataFraAvleverendeSystem,
        mottaker = mottaker ?: throw IllegalArgumentException("Mottaker er ikke satt"),
        signaturType = signaturType,
        signeringUtloper = signeringUtloper,
        svarPaForsendelse = svarPaForsendelse,
        svarPaForsendelseLink = svarPaForsendelseLink,
        svarSendesTil = svarSendesTil,
        taushetsbelagtPost = taushetsbelagtPost,
        tittel = tittel ?: throw IllegalArgumentException("Tittel er ikke satt"),
        utskriftskonfigurasjon = utskriftskonfigurasjon
    )
}