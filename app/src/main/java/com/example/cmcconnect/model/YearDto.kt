package com.example.cmcconnect.model

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class YearDto (
    val id: Int,
    val year: LocalDate
): java.io.Serializable
