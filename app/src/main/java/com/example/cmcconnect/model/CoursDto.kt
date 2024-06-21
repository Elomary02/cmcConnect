package com.example.cmcconnect.model

import kotlinx.serialization.Serializable

@Serializable
data class CoursDto (
    val id: Int,
    val id_teacher_fk: TeacherDto,
    val id_groupe_fk: GroupeDto,
    val id_module_fk: ModuleDto,
): java.io.Serializable
