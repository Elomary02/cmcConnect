package com.example.cmcconnect.repository.teacherRepository

import com.example.cmcconnect.model.CoursDto
import com.example.cmcconnect.model.RessourceDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(private val postgrest: Postgrest) :
    TeacherRepository {
    override suspend fun getCourByTeacherId(idTeacher: Int): List<CoursDto> {
        return withContext(Dispatchers.IO) {
            val lisModuleRes =
                postgrest.from("cours")
                    .select(columns = Columns.list("groupe(id,name,id_filiere_fk)")) {
                        filter {
                            eq("id_teacher_fk", idTeacher)

                        }
                    }.decodeList<CoursDto>()
            val uniqueGroups = lisModuleRes.distinctBy { it.groupe?.id }
            uniqueGroups
        }
    }

    override suspend fun getModuleByTeacherAndGroupId(
        idTeacher: Int,
        idGroup: Int
    ): List<CoursDto> {
        return withContext(Dispatchers.IO) {
            val lisModuleRes =
                postgrest.from("cours")
                    .select(columns = Columns.list("module(id,name)")) {
                        filter {
                            eq("id_teacher_fk", idTeacher)
                            eq("id_groupe_fk", idGroup)

                        }
                    }.decodeList<CoursDto>()
            lisModuleRes
        }
    }

    override suspend fun getResourceTeacherAndModuleId(
        idTeacher: Int,
        idModule: Int
    ): List<RessourceDto> {
        return withContext(Dispatchers.IO) {
            val listRes = postgrest.from("ressource").select {
                filter {
                    eq("id_teacher_fk",idTeacher)
                    eq("id_module_fk", idModule)
                }
            }.decodeList<RessourceDto>()
            listRes
        }

    }
}