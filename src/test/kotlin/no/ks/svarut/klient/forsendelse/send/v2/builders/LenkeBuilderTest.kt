package no.ks.svarut.klient.forsendelse.send.v2.builders

import io.kotest.assertions.asClue
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import java.util.*

class LenkeBuilderTest : StringSpec({

    "Url skal være påkrevd" {
        shouldThrow<IllegalArgumentException> { LenkeBuilder().build() }.asClue {
            it.message shouldBe "Url er ikke satt"
        }
    }

    "Tekst skal være påkrevd" {
        shouldThrow<IllegalArgumentException> { LenkeBuilder().url(UUID.randomUUID().toString()).build() }.asClue {
            it.message shouldBe "Tekst er ikke satt"
        }
    }

    "Setter bare påkrevde felter" {
        val tekst = UUID.randomUUID().toString()
        val url = UUID.randomUUID().toString()

        LenkeBuilder()
            .tekst(tekst)
            .url(url)
            .build()
            .asClue {
                it.ledetekst should beNull()
                it.tekst shouldBe tekst
                it.url shouldBe url
            }
    }

    "Setter alle felter" {
        val ledetekst = UUID.randomUUID().toString()
        val tekst = UUID.randomUUID().toString()
        val url = UUID.randomUUID().toString()

        LenkeBuilder()
            .ledetekst(ledetekst)
            .tekst(tekst)
            .url(url)
            .build()
            .asClue {
                it.ledetekst shouldBe ledetekst
                it.tekst shouldBe tekst
                it.url shouldBe url
            }
    }
})
