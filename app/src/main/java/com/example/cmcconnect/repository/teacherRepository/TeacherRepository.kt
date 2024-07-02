package com.example.cmcconnect.repository.teacherRepository

import com.example.cmcconnect.model.CoursDto
import com.example.cmcconnect.model.RessourceDto
import com.example.cmcconnect.model.StudentDto

interface TeacherRepository {
    suspend fun getCourByTeacherId(idTeacher : Int) : List<CoursDto>
    suspend fun getModuleByTeacherAndGroupId(idTeacher : Int,idGroup:Int): List<CoursDto>

    suspend fun getResourceTeacherAndModuleId(idTeacher : Int,idModule:Int) : List<RessourceDto>

    suspend fun getStudentsByGroupId(idGroup: Int): List<StudentDto>
}