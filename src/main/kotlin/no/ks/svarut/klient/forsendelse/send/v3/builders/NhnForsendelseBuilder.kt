package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.*
import java.util.*

class NhnForsendelseBuilder {
    private var tittel: String? = null
    private var eksponertFor: Set<String>? = null
    private var avgivendeSystem: String? = null
    private var avgivendeSystemAktor: String? = null
    private var konteringskode: String? = null
    private var kunDigitalLevering: Boolean? = false
    private var kryptert: Boolean? = false
    private var utskriftskonfigurasjon: Utskriftskonfigurasjon? = null
    private var svarPaForsendelseId: UUID? = null
    private var eksternReferanse: String? = null
    private var korrelasjonsid: String? = null
    private var mottakerType: NhnForsendelse.MottakerType? = null
    private var avsenderKontakt: NhnForsendelseAvsenderKontakt? = null
    private var pasient: NhnForsendelsePasient? = null
    private var beskrivelse: String? = null
    private var mottakerHerId: String? = null


    fun tittel(tittel: String) = apply { this.tittel = tittel }
    fun eksponertFor(eksponertFor: Set<String>?) = apply { this.eksponertFor = eksponertFor }
    fun avgivendeSystem(avgivendeSystem: String?) = apply { this.avgivendeSystem = avgivendeSystem }
    fun konteringskode(konteringskode: String?) = apply { this.konteringskode = konteringskode }
    fun kunDigitalLevering(kunDigitalLevering: Boolean?) = apply { this.kunDigitalLevering = kunDigitalLevering }
    fun kryptert(kryptert: Boolean?) = apply { this.kryptert = kryptert }
    fun utskriftskonfigurasjon(utskriftskonfigurasjon: Utskriftskonfigurasjon?) =
        apply { this.utskriftskonfigurasjon = utskriftskonfigurasjon }

    fun svarPaForsendelse(svarPaForsendelse: UUID?) = apply { this.svarPaForsendelseId = svarPaForsendelse }
    fun eksternReferanse(eksternReferanse: String?) = apply { this.eksternReferanse = eksternReferanse }
    fun korrelasjonsid(korrelasjonsid: String?) = apply { this.korrelasjonsid = korrelasjonsid }
    fun mottakerType(mottakerType: NhnForsendelse.MottakerType?) = apply { this.mottakerType = mottakerType }
    fun avsenderKontakt(avsenderKontakt: NhnForsendelseAvsenderKontakt?) =
        apply { this.avsenderKontakt = avsenderKontakt }

    fun pasient(pasient: NhnForsendelsePasient?) = apply { this.pasient = pasient }
    fun beskrivelse(beskrivelse: String?) = apply { this.beskrivelse = beskrivelse }
    fun mottakerHerId(mottakerHerId: String?) = apply { this.mottakerHerId = mottakerHerId }
    fun avgivendeSystemAktor(avgivendeSystemAktor: String?) = apply { this.avgivendeSystemAktor = avgivendeSystemAktor }
    fun mottakerType(mottakerType: String?) =
        apply { this.mottakerType = mottakerType?.let { NhnForsendelse.MottakerType.valueOf(it) } }

    fun build() = NhnForsendelse(
        tittel = tittel ?: throw IllegalArgumentException("Tittel er ikke satt"),
        eksponertFor = eksponertFor,
        avgivendeSystem = avgivendeSystem,
        avgivendeSystemAktor = avgivendeSystemAktor,
        konteringskode = konteringskode,
        kunDigitalLevering = kunDigitalLevering,
        kryptert = kryptert,
        utskriftskonfigurasjon = utskriftskonfigurasjon,
        svarPaForsendelseId = svarPaForsendelseId,
        eksternReferanse = eksternReferanse,
        korrelasjonsid = korrelasjonsid,
        mottakerType = mottakerType ?: throw IllegalArgumentException("MottakerType er ikke satt"),
        avsenderKontakt = avsenderKontakt ?: throw IllegalArgumentException("AvsenderKontakt er ikke satt"),
        pasient = pasient ?: throw IllegalArgumentException("Pasient er ikke satt"),
        beskrivelse = beskrivelse,
        mottakerHerId = mottakerHerId,
    )
}
