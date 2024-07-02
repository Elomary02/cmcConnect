package com.example.cmcconnect.repository.teacherRepository

import android.net.Uri
import com.example.cmcconnect.model.CoursDto
import com.example.cmcconnect.model.GroupeDto
import com.example.cmcconnect.model.ModuleDto
import com.example.cmcconnect.model.ResourceToPost
import com.example.cmcconnect.model.RessourceDto

interface TeacherRepository {
    suspend fun loadGroupsOfTeacher(idTeacher: Int): List<GroupeDto>
    suspend fun loadModulesOfGroup(idGroup: Int): List<ModuleDto>
    suspend fun uploadResourceToBucket(uri: Uri, fileName: String): String?
    suspend fun postResource(resource: ResourceToPost): Boolean
    suspend fun getCourByTeacherId(idTeacher : Int) : List<CoursDto>
    suspend fun getModuleByTeacherAndGroupId(idTeacher : Int,idGroup:Int): List<CoursDto>
    suspend fun getResourceTeacherAndModuleId(idTeacher : Int,idModule:Int) : List<RessourceDto>
}