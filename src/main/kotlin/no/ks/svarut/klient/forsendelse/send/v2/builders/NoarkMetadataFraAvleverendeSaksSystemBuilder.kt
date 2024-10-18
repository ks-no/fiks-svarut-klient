package no.ks.svarut.klient.forsendelse.send.v2.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.Metadata
import no.ks.fiks.svarut.forsendelse.send.model.v2.NoarkMetadataFraAvleverendeSaksSystem
import java.time.OffsetDateTime

class NoarkMetadataFraAvleverendeSaksSystemBuilder {
    private var dokumentetsDato: OffsetDateTime? = null
    private var ekstraMetadata: List<Metadata>? = null
    private var journalaar: Int? = null
    private var journaldato: OffsetDateTime? = null
    private var journalpostnummer: Long? = null
    private var journalposttype: String? = null
    private var journalsekvensnummer: Long? = null
    private var journalstatus: String? = null
    private var mappeId: String? = null
    private var mappeSystemId: String? = null
    private var registreringsId: String? = null
    private var saksaar: Int? = null
    private var saksbehandler: String? = null
    private var sakssekvensnummer: Long? = null
    private var systemId: String? = null
    private var tittel: String? = null

    fun dokumentetsDato(dokumentetsDato: OffsetDateTime?) = apply { this.dokumentetsDato = dokumentetsDato }
    fun ekstraMetadata(ekstraMetadata: List<Metadata>?) = apply { this.ekstraMetadata = ekstraMetadata }
    fun journalaar(journalaar: Int?) = apply { this.journalaar = journalaar }
    fun journaldato(journaldato: OffsetDateTime?) = apply { this.journaldato = journaldato }
    fun journalpostnummer(journalpostnummer: Long?) = apply { this.journalpostnummer = journalpostnummer }
    fun journalposttype(journalposttype: String?) = apply { this.journalposttype = journalposttype }
    fun journalsekvensnummer(journalsekvensnummer: Long?) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalstatus(journalstatus: String?) = apply { this.journalstatus = journalstatus }
    fun mappeId(mappeId: String?) = apply { this.mappeId = mappeId }
    fun mappeSystemId(mappeSystemId: String?) = apply { this.mappeSystemId = mappeSystemId }
    fun registreringsId(registreringsId: String?) = apply { this.registreringsId = registreringsId }
    fun saksaar(saksaar: Int?) = apply { this.saksaar = saksaar }
    fun saksbehandler(saksbehandler: String?) = apply { this.saksbehandler = saksbehandler }
    fun sakssekvensnummer(sakssekvensnummer: Long?) = apply { this.sakssekvensnummer = sakssekvensnummer }
    fun systemId(systemId: String?) = apply { this.systemId = systemId }
    fun tittel(tittel: String?) = apply { this.tittel = tittel }

    fun build() = NoarkMetadataFraAvleverendeSaksSystem(
        dokumentetsDato = dokumentetsDato,
        ekstraMetadata = ekstraMetadata,
        journalaar = journalaar,
        journaldato = journaldato,
        journalpostnummer = journalpostnummer,
        journalposttype = journalposttype,
        journalsekvensnummer = journalsekvensnummer,
        journalstatus = journalstatus,
        mappeId = mappeId,
        mappeSystemId = mappeSystemId,
        registreringsId = registreringsId,
        saksaar = saksaar,
        saksbehandler = saksbehandler,
        sakssekvensnummer = sakssekvensnummer,
        systemId = systemId,
        tittel = tittel
    )
}