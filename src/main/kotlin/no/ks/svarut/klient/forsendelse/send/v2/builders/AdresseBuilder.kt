package no.ks.svarut.klient.forsendelse.send.v2.builders

import no.ks.fiks.svarut.forsendelse.send.model.v2.Adresse

class AdresseBuilder {

    private var navn: String? = null
    private var digitalId: String? = null
    private var adresselinje1: String? = null
    private var adresselinje2: String? = null
    private var adresselinje3: String? = null
    private var poststed: String? = null
    private var postnummer: String? = null
    private var land: String? = null

    fun navn(navn: String) = this.also { this.navn = navn }
    fun digitalId(digitalId: String) = this.also { this.digitalId = digitalId }
    fun adresselinje1(adresselinje1: String) = this.also { this.adresselinje1 = adresselinje1 }
    fun adresselinje2(adresselinje2: String) = this.also { this.adresselinje2 = adresselinje2 }
    fun adresselinje3(adresselinje3: String) = this.also { this.adresselinje3 = adresselinje3 }
    fun poststed(poststed: String) = this.also { this.poststed = poststed }
    fun postnummer(postnummer: String) = this.also { this.postnummer = postnummer }
    fun land(land: String) = this.also { this.land = land }

    fun build() = Adresse(navn ?: throw IllegalArgumentException("Navn må være satt"), digitalId, adresselinje1, adresselinje2, adresselinje3, poststed, postnummer, land)

}