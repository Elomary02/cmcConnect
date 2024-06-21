package com.example.cmcconnect.model

import kotlinx.serialization.Serializable

@Serializable
data class FiliereDto (
    val id: Int,
    val name: String,
    val id_pole_fk: Int
): java.io.Serializable
