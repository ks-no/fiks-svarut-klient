package no.ks.svarut.klient.forsendelse.send.v3.builders

import no.ks.fiks.svarut.forsendelse.send.model.v3.Adresse

class AdresseBuilder {

    private var adresselinje1: String? = null
    private var adresselinje2: String? = null
    private var adresselinje3: String? = null
    private lateinit var poststed: String
    private lateinit var postnummer: String
    private var land: String? = null

    fun adresselinje1(adresselinje1: String?) = this.also { this.adresselinje1 = adresselinje1 }
    fun adresselinje2(adresselinje2: String?) = this.also { this.adresselinje2 = adresselinje2 }
    fun adresselinje3(adresselinje3: String?) = this.also { this.adresselinje3 = adresselinje3 }
    fun poststed(poststed: String) = this.also { this.poststed = poststed }
    fun postnummer(postnummer: String) = this.also { this.postnummer = postnummer }
    fun land(land: String?) = this.also { this.land = land }

    fun build() = Adresse(
        adresselinje1 = adresselinje1,
        adresselinje2 = adresselinje2,
        adresselinje3 = adresselinje3,
        poststed = poststed,
        postnummer = postnummer,
        land = land)

}