package no.ks.svarut.klient.forsendelse.send.v2.builders

import io.kotest.assertions.asClue
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import no.ks.fiks.svarut.forsendelse.send.model.v2.Metadata
import java.util.*
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextInt

class DokumentBuilderTest : StringSpec({

    "Test filnavn should be required" {
        shouldThrow<IllegalArgumentException> { DokumentBuilder().build() }.asClue {
            it.message shouldBe "Dokument må ha filnavn"
        }
    }

    "Test mimeType should be required" {
        shouldThrow<IllegalArgumentException> {
            DokumentBuilder().filnavn(UUID.randomUUID().toString()).build()
        }.asClue {
            it.message shouldBe "Mimetype på dokumentet må være satt"
        }
    }

    "Test only required fields" {
        DokumentBuilder()
            .filnavn(UUID.randomUUID().toString())
            .mimeType(UUID.randomUUID().toString())
            .build()
            .asClue {
                it.dokumentType should beNull()
                it.ekstraMetadata should beNull()
                it.giroarkSider should beNull()
                it.inneholderPersonsensitivInformasjon shouldBe false
                it.skalEkskluderesFraUtskrift shouldBe false
                it.skalSigneres shouldBe false
            }
    }

    "Test set all fields" {
        val dokumentType = UUID.randomUUID().toString()
        val ekstraMetadata = (1..nextInt(2, 10)).map { Metadata(UUID.randomUUID().toString(), UUID.randomUUID().toString()) }
        val filnavn = UUID.randomUUID().toString()
        val giroarkSider = (1..nextInt(2, 10)).map { nextInt() }
        val inneholderPersonsensitivInformasjon = nextBoolean()
        val mimeType = UUID.randomUUID().toString()
        val skalEkskluderesFraUtskrift = nextBoolean()
        val skalSigneres = nextBoolean()

        DokumentBuilder()
            .dokumentType(dokumentType)
            .ekstraMetadata(ekstraMetadata)
            .filnavn(filnavn)
            .giroarkSider(giroarkSider.toSet())
            .inneholderPersonsensitivInformasjon(inneholderPersonsensitivInformasjon)
            .mimeType(mimeType)
            .skalEkskluderesFraUtskrift(skalEkskluderesFraUtskrift)
            .skalSigneres(skalSigneres)
            .build()
            .asClue {
                it.dokumentType shouldBe dokumentType
                it.ekstraMetadata shouldBe ekstraMetadata
                it.filnavn shouldBe filnavn
                it.giroarkSider shouldBe giroarkSider
                it.inneholderPersonsensitivInformasjon shouldBe inneholderPersonsensitivInformasjon
                it.mimeType shouldBe mimeType
                it.skalEkskluderesFraUtskrift shouldBe skalEkskluderesFraUtskrift
                it.skalSigneres shouldBe skalSigneres
            }
    }
})
