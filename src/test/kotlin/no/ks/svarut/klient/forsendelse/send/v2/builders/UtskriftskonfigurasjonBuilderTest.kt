package no.ks.svarut.klient.forsendelse.send.v2.builders

import io.kotest.assertions.asClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class UtskriftskonfigurasjonBuilderTest : StringSpec({

    "Test build without any fields" {
        val utskriftskonfigurasjon = UtskriftskonfigurasjonBuilder().build()
        utskriftskonfigurasjon.asClue {
            it.tosidig shouldBe false
            it.utskriftMedFarger shouldBe false
        }
    }

    "Test set only tosidig" {
        val tosidig = Random.nextBoolean()

        val utskriftskonfigurasjon = UtskriftskonfigurasjonBuilder()
            .tosidig(tosidig)
            .build()

        utskriftskonfigurasjon.asClue {
            it.tosidig shouldBe tosidig
            it.utskriftMedFarger shouldBe false
        }
    }

    "Test set only utskriftMedFarger" {
        val utskriftMedFarger = Random.nextBoolean()

        UtskriftskonfigurasjonBuilder()
            .utskriftMedFarger(utskriftMedFarger)
            .build()
            .asClue {
                it.tosidig shouldBe false
                it.utskriftMedFarger shouldBe utskriftMedFarger
            }
    }

    "Test set all fields" {
        val tosidig = Random.nextBoolean()
        val utskriftMedFarger = Random.nextBoolean()

        UtskriftskonfigurasjonBuilder()
            .tosidig(tosidig)
            .utskriftMedFarger(utskriftMedFarger)
            .build()
            .asClue {
                it.tosidig shouldBe tosidig
                it.utskriftMedFarger shouldBe utskriftMedFarger
            }
    }
})
