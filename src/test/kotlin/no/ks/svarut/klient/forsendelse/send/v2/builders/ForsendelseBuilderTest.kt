package no.ks.svarut.klient.forsendelse.send.v2.builders

import io.kotest.assertions.asClue
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import no.ks.fiks.svarut.forsendelse.send.model.v2.Forsendelse
import java.time.OffsetDateTime
import java.util.*

class ForsendelseBuilderTest : StringSpec({

    "Mottaker skal være påkrevd" {
        shouldThrow<IllegalArgumentException> { ForsendelseBuilder().build() }.asClue {
            it.message shouldBe "Mottaker er ikke satt"
        }
    }

    "Setter bare påkrevde felter" {
        val mottaker = AdresseBuilder().navn(UUID.randomUUID().toString()).build()
        val tittel = UUID.randomUUID().toString()

        ForsendelseBuilder()
            .mottaker(mottaker)
            .tittel(tittel)
            .build()
            .asClue {
                it.mottaker shouldBe mottaker
                it.tittel shouldBe tittel
                it.avgivendeSystem should beNull()
                it.dokumenter should beNull()
                it.eksponertFor should beNull()
                it.eksternReferanse should beNull()
                it.forsendelsestype should beNull()
                it.konteringskode should beNull()
                it.krevNiva4Innlogging shouldBe false
                it.kryptert shouldBe false
                it.kunDigitalLevering shouldBe false
                it.lenker should beNull()
                it.metadataForImport should beNull()
                it.metadataFraAvleverendeSystem should beNull()
                it.signaturType should beNull()
                it.signeringUtloper should beNull()
                it.svarPaForsendelse should beNull()
                it.svarPaForsendelseLink shouldBe false
                it.svarSendesTil should beNull()
                it.taushetsbelagtPost shouldBe false
                it.utskriftskonfigurasjon should beNull()
            }
    }

    "Setter alle felter" {
        val mottaker = buildRandomAdresse()
        val svarSendesTil = buildRandomAdresse()
        val tittel = UUID.randomUUID().toString()
        val forsendelsestype = UUID.randomUUID().toString()
        val avgivendeSystem = UUID.randomUUID().toString()
        val lenker = (1..Random().nextInt(2, 10)).map { buildRandomLenke() }
        val dokumenter = (1..Random().nextInt(2, 10)).map { buildRandomDokument() }
        val eksponertFor = setOf(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        val eksternReferanse = UUID.randomUUID().toString()
        val konteringskode = UUID.randomUUID().toString()
        val krevNiva4Innlogging = Random().nextBoolean()
        val kryptert = Random().nextBoolean()
        val kunDigitalLevering = Random().nextBoolean()
        val metadataForImport = buildRandomNoarkMetadataForImport()
        val metadataFraAvleverendeSystem = buildRandomNoarkMetadataFraAvleverendeSaksSystem()
        val signaturType = Forsendelse.SignaturType.entries.random()
        val signeringUtloper = OffsetDateTime.now().plusDays(1)
        val svarPaForsendelse = UUID.randomUUID()
        val svarPaForsendelseLink = Random().nextBoolean()
        val taushetsbelagtPost = Random().nextBoolean()
        val utskriftskonfigurasjon = buildRandomUtskriftskonfigurasjon()

        ForsendelseBuilder()
            .mottaker(mottaker)
            .svarSendesTil(svarSendesTil)
            .tittel(tittel)
            .forsendelsestype(forsendelsestype)
            .avgivendeSystem(avgivendeSystem)
            .lenker(lenker)
            .dokumenter(dokumenter)
            .eksponertFor(eksponertFor)
            .eksternReferanse(eksternReferanse)
            .konteringskode(konteringskode)
            .krevNiva4Innlogging(krevNiva4Innlogging)
            .kryptert(kryptert)
            .kunDigitalLevering(kunDigitalLevering)
            .metadataForImport(metadataForImport)
            .metadataFraAvleverendeSystem(metadataFraAvleverendeSystem)
            .signaturType(signaturType)
            .signeringUtloper(signeringUtloper)
            .svarPaForsendelse(svarPaForsendelse)
            .svarPaForsendelseLink(svarPaForsendelseLink)
            .taushetsbelagtPost(taushetsbelagtPost)
            .utskriftskonfigurasjon(utskriftskonfigurasjon)
            .build()
            .asClue {
                it.mottaker shouldBe mottaker
                it.svarSendesTil shouldBe svarSendesTil
                it.tittel shouldBe tittel
                it.forsendelsestype shouldBe forsendelsestype
                it.avgivendeSystem shouldBe avgivendeSystem
                it.lenker shouldBe lenker
                it.dokumenter shouldBe dokumenter
                it.eksponertFor shouldBe eksponertFor
                it.eksternReferanse shouldBe eksternReferanse
                it.konteringskode shouldBe konteringskode
                it.krevNiva4Innlogging shouldBe krevNiva4Innlogging
                it.kryptert shouldBe kryptert
                it.kunDigitalLevering shouldBe kunDigitalLevering
                it.metadataForImport shouldBe metadataForImport
                it.metadataFraAvleverendeSystem shouldBe metadataFraAvleverendeSystem
                it.signaturType shouldBe signaturType
                it.signeringUtloper shouldBe signeringUtloper
                it.svarPaForsendelse shouldBe svarPaForsendelse
                it.svarPaForsendelseLink shouldBe svarPaForsendelseLink
                it.taushetsbelagtPost shouldBe taushetsbelagtPost
                it.utskriftskonfigurasjon shouldBe utskriftskonfigurasjon
            }
    }


})
