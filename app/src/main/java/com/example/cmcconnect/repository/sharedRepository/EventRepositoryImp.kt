package com.example.cmcconnect.repository.sharedRepository

import com.example.cmcconnect.model.EventDto
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class EventRepositoryImp @Inject constructor(private val postgrest: Postgrest): EventRepository {
    override suspend fun getEvents(): EventDto {
        TODO("Not yet implemented")
    }

}