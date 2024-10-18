package no.ks.svarut.klient.forsendelse.send.v2.builders

import io.kotest.assertions.asClue
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import java.util.*

class LenkeBuilderTest : StringSpec({

    "Test url should be required" {
        shouldThrow<IllegalArgumentException> { LenkeBuilder().build() }.asClue {
            it.message shouldBe "Url er ikke satt"
        }
    }

    "Test tekst should be required" {
        shouldThrow<IllegalArgumentException> { LenkeBuilder().url(UUID.randomUUID().toString()).build() }.asClue {
            it.message shouldBe "Tekst er ikke satt"
        }
    }

    "Test only required fields" {
        val tekst = UUID.randomUUID().toString()
        val url = UUID.randomUUID().toString()

        val lenke = LenkeBuilder()
            .tekst(tekst)
            .url(url)
            .build()

        lenke.asClue {
            it.ledetekst should beNull()
            it.tekst shouldBe tekst
            it.url shouldBe url
        }
    }

    "Test set all fields" {
        val ledetekst = UUID.randomUUID().toString()
        val tekst = UUID.randomUUID().toString()
        val url = UUID.randomUUID().toString()

        val lenke = LenkeBuilder()
            .ledetekst(ledetekst)
            .tekst(tekst)
            .url(url)
            .build()

        lenke.asClue {
            it.ledetekst shouldBe ledetekst
            it.tekst shouldBe tekst
            it.url shouldBe url
        }
    }
})
