package no.ks.svarut.klient

import java.util.*

class SvarUtKlientException(val errorMessage: ErrorMessage) : RuntimeException("Status ${errorMessage.status}: ${errorMessage.message}")

data class ErrorMessage(
    val timestamp: Long?,
    val status: Int?,
    val error: String?,
    val errorId: UUID?,
    val path: String?,
    val originalPath: String?,
    val message: String?,
    val errorCode: String?,
    val errorJson: String?
)
