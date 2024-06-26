package com.example.cmcconnect.model

import kotlinx.serialization.Serializable

@Serializable
data class TeacherDto (
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val image: String,
    val id_pole_fk: Int
): java.io.Serializable