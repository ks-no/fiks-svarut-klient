package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.Metadata
import no.ks.fiks.svarut.forsendelse.send.model.v2.NoarkMetadataFraAvleverendeSaksSystem
import java.time.OffsetDateTime

class NoarkMetadataFraAvleverendeSaksSystemBuilder {

    private var mappeId: String? = null
    private var mappeSystemId: String? = null
    private var saksaar: Int? = null
    private var sakssekvensnummer: Long? = null
    private var systemId: String? = null
    private var registreringsId: String? = null
    private var journalpostnummer: Long? = null
    private var journalaar: Int? = null
    private var journalsekvensnummer: Long? = null
    private var journalposttype: String? = null
    private var journalstatus: String? = null
    private var journaldato: OffsetDateTime? = null
    private var dokumentetsDato: OffsetDateTime? = null
    private var tittel: String? = null
    private var saksbehandler: String? = null
    private var ekstraMetadata: List<Metadata>? = null

    fun mappeId(mappeId: String?) = apply { this.mappeId = mappeId }
    fun mappeSystemId(mappeSystemId: String?) = apply { this.mappeSystemId = mappeSystemId }
    fun saksaar(saksaar: Int?) = apply { this.saksaar = saksaar }
    fun sakssekvensnummer(sakssekvensnummer: Long?) = apply { this.sakssekvensnummer = sakssekvensnummer }
    fun systemId(systemId: String?) = apply { this.systemId = systemId }
    fun registreringsId(registreringsId: String?) = apply { this.registreringsId = registreringsId }
    fun journalpostnummer(journalpostnummer: Long?) = apply { this.journalpostnummer = journalpostnummer }
    fun journalaar(journalaar: Int?) = apply { this.journalaar = journalaar }
    fun journalsekvensnummer(journalsekvensnummer: Long?) = apply { this.journalsekvensnummer = journalsekvensnummer }
    fun journalposttype(journalposttype: String?) = apply { this.journalposttype = journalposttype }
    fun journalstatus(journalstatus: String?) = apply { this.journalstatus = journalstatus }
    fun journaldato(journaldato: OffsetDateTime?) = apply { this.journaldato = journaldato }
    fun dokumentetsDato(dokumentetsDato: OffsetDateTime?) = apply { this.dokumentetsDato = dokumentetsDato }
    fun tittel(tittel: String?) = apply { this.tittel = tittel }
    fun saksbehandler(saksbehandler: String?) = apply { this.saksbehandler = saksbehandler }
    fun ekstraMetadata(ekstraMetadata: List<Metadata>?) = apply { this.ekstraMetadata = ekstraMetadata }

    fun build() = NoarkMetadataFraAvleverendeSaksSystem(
        mappeId = mappeId,
        mappeSystemId = mappeSystemId,
        saksaar = saksaar,
        sakssekvensnummer = sakssekvensnummer,
        systemId = systemId,
        registreringsId = registreringsId,
        journalpostnummer = journalpostnummer,
        journalaar = journalaar,
        journalsekvensnummer = journalsekvensnummer,
        journalposttype = journalposttype,
        journalstatus = journalstatus,
        journaldato = journaldato,
        dokumentetsDato = dokumentetsDato,
        tittel = tittel,
        saksbehandler = saksbehandler,
        ekstraMetadata = ekstraMetadata?.toSet(),
    )

}
