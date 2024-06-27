package com.example.cmcconnect.repository.sharedRepository

import com.example.cmcconnect.model.*
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val auth: Auth, private val postgrest: Postgrest): UserRepository {
//    override suspend fun getUserInfo(userEmail: String): UserDto {
//        return withContext(Dispatchers.IO) {
//            val userId = auth.retrieveUserForCurrentSession(updateSession = true).id ?: throw Exception("User not authenticated")
//
//            val user = postgrest.from("user").select {
//                filter {
//                    eq("id", userId)
//                }
//            }.decodeSingle<UserDto>()
//
//            when {
//                user.id_student_fk != null -> {
//                    val studentDetails = postgrest.from("student").select {
//                        filter {
//                            eq("id", user.id_student_fk)
//                        }
//                    }.decodeSingle<StudentDto>()
//                    UserDto(
//                        id = user.id,
//                        id_student_fk = studentDetails.id,
//                        id_teacher_fk = null,
//                        id_admin_fk = null
//                    )
//                }
//                user.id_teacher_fk != null -> {
//                    val teacherDetails = postgrest.from("teacher").select {
//                        filter {
//                            eq("id", user.id_teacher_fk)
//                        }
//                    }.decodeSingle<TeacherDto>()
//                    UserDto(
//                        id = user.id,
//                        id_student_fk = null,
//                        id_teacher_fk = teacherDetails.id,
//                        id_admin_fk = null
//                    )
//                }
//                user.id_admin_fk != null -> {
//                    val adminDetails = postgrest.from("admin").select {
//                        filter {
//                            eq("id", user.id_admin_fk)
//                        }
//                    }.decodeSingle<AdminDto>()
//                    UserDto(
//                        id = user.id,
//                        id_student_fk = null,
//                        id_teacher_fk = null,
//                        id_admin_fk = adminDetails.id
//                    )
//                }
//                else -> throw Exception("User role not found")
//            }
//        }
//    }
}
