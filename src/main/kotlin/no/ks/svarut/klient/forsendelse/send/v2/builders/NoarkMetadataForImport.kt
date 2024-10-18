package no.ks.svarut.klient.forsendelse.send.v2.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.NoarkMetadataForImport

class NoarkMetadataForImportBuilder {
    private var dokumentetsDato: java.time.OffsetDateTime? = null
    private var journalaar: Int? = null
    private var journalpostnummer: Long? = null
    private var journalposttype: String? = null
    private var journalsekvensnummer: Long? = null
    private var journalstatus: String? = null
    private var mappeId: String? = null
    private var mappeSystemId: String? = null
    private var registreringsId: String? = null
    private var saksaar: Int? = null
    private var sakssekvensnummer: Long? = null
    private var systemId: String? = null
    private var tittel: String? = null

    fun dokumentetsDato(dokumentetsDato: java.time.OffsetDateTime?) = apply { this.dokumentetsDato = dokumentetsDato }
    fun journalaar(journalaar: Int?) = apply { this.journalaar = journalaar }
    fun journalpostnummer(journalpostnummer: Long?) = apply { this.journalpostnummer = journalpostnummer }
    fun journalposttype(journalposttype: String?) = apply { this.journalposttype = journalposttype }
    fun journalsekvensnummer(journalsekvensnummer: Long?) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalstatus(journalstatus: String?) = apply { this.journalstatus = journalstatus }
    fun mappeId(mappeId: String?) = apply { this.mappeId = mappeId }
    fun mappeSystemId(mappeSystemId: String?) = apply { this.mappeSystemId = mappeSystemId }
    fun registreringsId(registreringsId: String?) = apply { this.registreringsId = registreringsId }
    fun saksaar(saksaar: Int?) = apply { this.saksaar = saksaar }
    fun sakssekvensnummer(sakssekvensnummer: Long?) = apply { this.sakssekvensnummer = sakssekvensnummer }
    fun systemId(systemId: String?) = apply { this.systemId = systemId }
    fun tittel(tittel: String?) = apply { this.tittel = tittel }

    fun build() = NoarkMetadataForImport (
        dokumentetsDato = dokumentetsDato,
        journalaar = journalaar,
        journalpostnummer = journalpostnummer,
        journalposttype = journalposttype,
        journalsekvensnummer = journalsekvensnummer,
        journalstatus = journalstatus,
        mappeId = mappeId,
        mappeSystemId = mappeSystemId,
        registreringsId = registreringsId,
        saksaar = saksaar,
        sakssekvensnummer = sakssekvensnummer,
        systemId = systemId,
        tittel = tittel
    )
}