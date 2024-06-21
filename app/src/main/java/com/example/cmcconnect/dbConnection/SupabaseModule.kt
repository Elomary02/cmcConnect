package com.example.cmcconnect.dbConnection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.FlowType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SupabaseModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl  = "https://hvlzrnryaxhaudwlwvhf.supabase.co",
            supabaseKey  = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imh2bHpybnJ5YXhoYXVkd2x3dmhmIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTg4OTI0MjUsImV4cCI6MjAzNDQ2ODQyNX0.HdDh6J3bPmsg18BuC483u_0wksh9YPiL1sO1fms62gI"
        ){
            install(Postgrest)
            install(Storage)
            install(Auth){
                flowType = FlowType.PKCE
                scheme="app"
                host="supabase.com"

            }

        }
    }
    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient):Postgrest{
        return client.postgrest
    }
    @Provides
    @Singleton
    fun provideSupabaseAuth(client: SupabaseClient):Auth{
        return client.auth
    }
    @Provides
    @Singleton
    fun provideSupabaseStorage(client: SupabaseClient):Storage{
        return client.storage
    }
}