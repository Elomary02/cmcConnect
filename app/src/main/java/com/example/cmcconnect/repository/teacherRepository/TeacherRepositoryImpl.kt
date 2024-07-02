package com.example.cmcconnect.repository.teacherRepository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.cmcconnect.model.CoursDto
import com.example.cmcconnect.model.GroupeDto
import com.example.cmcconnect.model.ModuleDto
import com.example.cmcconnect.model.ResourceToPost
import com.example.cmcconnect.model.RessourceDto
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Columns.Companion.raw
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val context: Context,
    private val postgrest: Postgrest,
    private val supabase: SupabaseClient
): TeacherRepository {
    override suspend fun loadGroupsOfTeacher(idTeacher: Int): List<GroupeDto> {
        return try {
            withContext(Dispatchers.IO) {
                val groupResult = postgrest.from("cours").select(Columns.raw("id_groupe_fk")) {
                    filter {
                        eq("id_teacher_fk", idTeacher)
                    }
                }.decodeList<Map<String, Int>>()
                val groupIds = groupResult.mapNotNull { it["id_groupe_fk"] }
                Log.d("Groupes", groupIds.toString())

                if (groupIds.isNotEmpty()) {
                    val idsFilter = groupIds.joinToString(prefix = "(", postfix = ")") { it.toString() }
                    val groups = postgrest.from("groupe").select(Columns.ALL) {
                        raw("id IN $idsFilter")
                    }.decodeList<GroupeDto>()
                    groups
                } else {
                    emptyList()
                }
            }
        } catch (e: Exception) {
            Log.e("groups for teacher", "Error loading groups", e)
            emptyList()
        }
    }

    override suspend fun loadModulesOfGroup(idGroup: Int): List<ModuleDto> {
        return try {
            withContext(Dispatchers.IO) {
                val moduleResult = postgrest.from("cours").select(Columns.raw("id_module_fk")) {
                    filter {
                        eq("id_groupe_fk", idGroup)
                    }
                }.decodeList<Map<String, Int>>()

                val moduleIds = moduleResult.mapNotNull { it["id_module_fk"] }.distinct()
                Log.d("modules", "Module IDs for group $idGroup: $moduleIds")

                if (moduleIds.isNotEmpty()) {
                    val idsFilter = moduleIds.joinToString(prefix = "(", postfix = ")") { it.toString() }
                    val modules = postgrest.from("module").select(Columns.ALL) {
                        raw("id IN $idsFilter")
                    }.decodeList<ModuleDto>()
                    Log.d("modules", "Loaded modules for group $idGroup: $modules")
                    modules
                } else {
                    emptyList()
                }
            }
        } catch (e: Exception) {
            Log.e("Modules For Group", "Error loading modules", e)
            emptyList()
        }
    }

    override suspend fun uploadResourceToBucket(uri: Uri, fileName: String): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val byteArray = inputStream?.readBytes()
            inputStream?.close()

            if (byteArray != null) {
                supabase.storage.from("ressource_file").upload(fileName, byteArray)
                "https://qoyjtvmvqtubjbwrlhgq.supabase.co/storage/v1/object/public/ressource_file/$fileName"
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("uploadFileToBucket", "Error uploading file", e)
            null
        }
    }

    override suspend fun postResource(resource: ResourceToPost): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                postgrest.from("ressource").insert(resource)
                true
            }
        } catch (e: Exception) {
            Log.e("post ressource", "Error posting ressource", e)
            false
        }
    }

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
