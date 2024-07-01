package com.example.cmcconnect.repository.studentRepository

import android.util.Log
import com.example.cmcconnect.model.AdminDto
import com.example.cmcconnect.model.JustifToSend
import com.example.cmcconnect.model.RequestDto
import com.example.cmcconnect.model.RequestToSend
import com.example.cmcconnect.model.RequestTypeDto
import com.example.cmcconnect.model.RessourceDto
import com.example.cmcconnect.model.TeacherDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class StudentRepositoryImp @Inject constructor(private val postgrest: Postgrest): StudentRepository {

    override suspend fun getRecentResourcesForStudentGroup(idStudent: Int): List<RessourceDto>? {
        return try {
            withContext(Dispatchers.IO) {
                val resources = postgrest.from("student").select(Columns.raw("id_groupe_fk, groupe!inner(id), cours!inner(id, id_groupe_fk), ressource!inner(*)")) {
                    filter {
                        eq("student.id", idStudent)
                    }
                    order("ressource.id", order = Order.DESCENDING)
                    limit(4)
                }.decodeList<RessourceDto>()
                resources
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun loadRequests(): List<RequestTypeDto> {
        return try {
            withContext(Dispatchers.IO) {
                val requests = postgrest.from("type_request").select(Columns.ALL)
                    .decodeList<RequestTypeDto>()
                requests
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun loadTeachersForStudent(idStudent: Int): List<TeacherDto> {
        return try {
            withContext(Dispatchers.IO) {
                val groupIdResult = postgrest.from("student").select(Columns.raw("id_groupe_fk")) {
                    filter {
                        eq("id", idStudent)
                    }
                }.decodeSingle<Map<String, Int>>()
                val groupId = groupIdResult["id_groupe_fk"]
                Log.d("groupId", groupId.toString())

                val teacherIdResult = postgrest.from("cours").select(Columns.raw("id_teacher_fk")) {
                    filter {
                        if (groupId != null) {
                            eq("id_groupe_fk", groupId)
                        }
                    }
                }.decodeSingle<Map<String, Int>>()
                val teacherId = teacherIdResult["id_teacher_fk"]
                Log.d("teacherId", teacherId.toString())

                val teachers = postgrest.from("teacher").select(Columns.ALL) {
                    filter {
                        if (teacherId != null) {
                            eq("id", teacherId)
                        }
                    }
                }.decodeList<TeacherDto>()

                Log.d("teachers", teachers.toString())
                teachers
            }
        } catch (e: Exception) {
            Log.e("loadTeachersForStudent", "Error loading teachers", e)
            emptyList()
        }
    }

    override suspend fun getStudentAdmin(idStudent: Int): AdminDto {
        return try {
            withContext(Dispatchers.IO) {
                val groupIdResult = postgrest.from("student").select(Columns.raw("id_groupe_fk")) {
                    filter {
                        eq("id", idStudent)
                    }
                }.decodeSingle<Map<String, Int>>()
                val groupId = groupIdResult["id_groupe_fk"]
                Log.d("groupId", groupId.toString())

                val filiereIdResult = postgrest.from("groupe").select(Columns.raw("id_filiere_fk")) {
                    filter {
                        if (groupId != null) {
                            eq("id", groupId)
                        }
                    }
                }.decodeSingle<Map<String, Int>>()
                val filiereId = filiereIdResult["id_filiere_fk"]
                Log.d("filiereId", filiereId.toString())

                val poleIdResult = postgrest.from("filiere").select(Columns.raw("id_pole_fk")) {
                    filter {
                        if (filiereId != null) {
                            eq("id", filiereId)
                        }
                    }
                }.decodeSingle<Map<String, Int>>()
                val poleId = poleIdResult["id_pole_fk"]
                Log.d("poleId", poleId.toString())

                val adminResult = postgrest.from("admin").select(Columns.ALL) {
                    filter {
                        if (poleId != null) {
                            eq("id_pole_fk", poleId)
                        }
                    }
                }.decodeSingle<AdminDto>()
                adminResult
            }
        } catch (e: Exception) {
            throw e
        }
    }


    override suspend fun sendRequest(request: RequestToSend): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                postgrest.from("request").insert(request)
                true
            }
            true
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    override suspend fun sendJustif(justif: JustifToSend): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                postgrest.from("justif").insert(justif)
                true
            }
            true
        } catch (e: java.lang.Exception) {
            throw e
        }
    }


}