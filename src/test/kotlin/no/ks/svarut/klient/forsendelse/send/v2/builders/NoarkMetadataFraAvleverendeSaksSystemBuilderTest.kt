package no.ks.svarut.klient.forsendelse.send.v2.builders

import io.kotest.assertions.asClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import no.ks.fiks.svarut.forsendelse.send.model.v2.Metadata
import java.time.OffsetDateTime
import java.util.*
import kotlin.random.Random

class NoarkMetadataFraAvleverendeSaksSystemBuilderTest : StringSpec({

    "Test no fields required" {
        val noarkMetadata = NoarkMetadataFraAvleverendeSaksSystemBuilder().build()
        noarkMetadata.asClue {
            it.dokumentetsDato should beNull()
            it.ekstraMetadata should beNull()
            it.journalaar should beNull()
            it.journaldato should beNull()
            it.journalpostnummer should beNull()
            it.journalposttype should beNull()
            it.journalsekvensnummer should beNull()
            it.journalstatus should beNull()
            it.mappeId should beNull()
            it.mappeSystemId should beNull()
            it.registreringsId should beNull()
            it.saksaar should beNull()
            it.saksbehandler should beNull()
            it.sakssekvensnummer should beNull()
            it.systemId should beNull()
            it.tittel should beNull()
        }
    }

    "Test set some fields" {
        val dokumentetsDato = OffsetDateTime.now()
        val journalaar = Random.nextInt()
        val journalpostnummer = Random.nextLong()
        val tittel = UUID.randomUUID().toString()
        val saksbehandler = UUID.randomUUID().toString()

        NoarkMetadataFraAvleverendeSaksSystemBuilder()
            .dokumentetsDato(dokumentetsDato)
            .journalaar(journalaar)
            .journalpostnummer(journalpostnummer)
            .tittel(tittel)
            .saksbehandler(saksbehandler)
            .build()
            .asClue {
                it.dokumentetsDato shouldBe dokumentetsDato
                it.journalaar shouldBe journalaar
                it.journalpostnummer shouldBe journalpostnummer
                it.tittel shouldBe tittel
                it.saksbehandler shouldBe saksbehandler
                it.ekstraMetadata should beNull()
                it.journaldato should beNull()
                it.journalposttype should beNull()
                it.journalsekvensnummer should beNull()
                it.journalstatus should beNull()
                it.mappeId should beNull()
                it.mappeSystemId should beNull()
                it.registreringsId should beNull()
                it.saksaar should beNull()
                it.sakssekvensnummer should beNull()
                it.systemId should beNull()
            }
    }

    "Test set all fields" {
        val dokumentetsDato = OffsetDateTime.now()
        val ekstraMetadata = List(Random.nextInt(1, 11)) { Metadata(UUID.randomUUID().toString(), UUID.randomUUID().toString()) }
        val journalaar = Random.nextInt()
        val journaldato = OffsetDateTime.now()
        val journalpostnummer = Random.nextLong()
        val journalposttype = UUID.randomUUID().toString()
        val journalsekvensnummer = Random.nextLong()
        val journalstatus = UUID.randomUUID().toString()
        val mappeId = UUID.randomUUID().toString()
        val mappeSystemId = UUID.randomUUID().toString()
        val registreringsId = UUID.randomUUID().toString()
        val saksaar = Random.nextInt()
        val saksbehandler = UUID.randomUUID().toString()
        val sakssekvensnummer = Random.nextLong()
        val systemId = UUID.randomUUID().toString()
        val tittel = UUID.randomUUID().toString()

        NoarkMetadataFraAvleverendeSaksSystemBuilder()
            .dokumentetsDato(dokumentetsDato)
            .ekstraMetadata(ekstraMetadata)
            .journalaar(journalaar)
            .journaldato(journaldato)
            .journalpostnummer(journalpostnummer)
            .journalposttype(journalposttype)
            .journalsekvensnummer(journalsekvensnummer)
            .journalstatus(journalstatus)
            .mappeId(mappeId)
            .mappeSystemId(mappeSystemId)
            .registreringsId(registreringsId)
            .saksaar(saksaar)
            .saksbehandler(saksbehandler)
            .sakssekvensnummer(sakssekvensnummer)
            .systemId(systemId)
            .tittel(tittel)
            .build()
            .asClue {
                it.dokumentetsDato shouldBe dokumentetsDato
                it.ekstraMetadata shouldBe ekstraMetadata
                it.journalaar shouldBe journalaar
                it.journaldato shouldBe journaldato
                it.journalpostnummer shouldBe journalpostnummer
                it.journalposttype shouldBe journalposttype
                it.journalsekvensnummer shouldBe journalsekvensnummer
                it.journalstatus shouldBe journalstatus
                it.mappeId shouldBe mappeId
                it.mappeSystemId shouldBe mappeSystemId
                it.registreringsId shouldBe registreringsId
                it.saksaar shouldBe saksaar
                it.saksbehandler shouldBe saksbehandler
                it.sakssekvensnummer shouldBe sakssekvensnummer
                it.systemId shouldBe systemId
                it.tittel shouldBe tittel
            }
    }
})