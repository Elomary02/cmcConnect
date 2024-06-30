package com.example.cmcconnect.repository.studentRepository

import com.example.cmcconnect.model.RessourceDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

}