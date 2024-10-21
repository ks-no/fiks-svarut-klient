package no.ks.svarut.klient.forsendelse.send.v2.builders

import io.kotest.assertions.asClue
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import java.util.*

class AdresseBuilderTest : StringSpec({

    "Navn skal være påkrevd" {
        shouldThrow<IllegalArgumentException> { AdresseBuilder().build() }.asClue {
            it.message shouldBe "Navn må være satt"
        }
    }

    "Setter bare påkrevde felter" {
        val navn = UUID.randomUUID().toString()
        AdresseBuilder().navn(navn).build().asClue {
            it.navn shouldBe navn
            it.digitalId should beNull()
            it.adresselinje1 should beNull()
            it.adresselinje2 should beNull()
            it.adresselinje3 should beNull()
            it.poststed should beNull()
            it.postnummer should beNull()
            it.land should beNull()
        }
    }

    "Setter alle felter" {
        val navn = UUID.randomUUID().toString()
        val digitalId = UUID.randomUUID().toString()
        val adresselinje1 = UUID.randomUUID().toString()
        val adresselinje2 = UUID.randomUUID().toString()
        val adresselinje3 = UUID.randomUUID().toString()
        val postnummer = UUID.randomUUID().toString()
        val poststed = UUID.randomUUID().toString()
        val land = UUID.randomUUID().toString()

        AdresseBuilder()
            .navn(navn)
            .digitalId(digitalId)
            .adresselinje1(adresselinje1)
            .adresselinje2(adresselinje2)
            .adresselinje3(adresselinje3)
            .postnummer(postnummer)
            .poststed(poststed)
            .land(land)
            .build()
            .asClue {
                it.navn shouldBe navn
                it.digitalId shouldBe digitalId
                it.adresselinje1 shouldBe adresselinje1
                it.adresselinje2 shouldBe adresselinje2
                it.adresselinje3 shouldBe adresselinje3
                it.poststed shouldBe poststed
                it.postnummer shouldBe postnummer
                it.land shouldBe land
            }
    }

})