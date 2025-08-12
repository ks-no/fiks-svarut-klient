package no.ks.svarut.klient.forsendelse.send.v3

import java.io.InputStream

data class NhnDokument(val filnavn: String, val contentType: String, val data: InputStream)