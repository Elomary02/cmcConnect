package com.example.cmcconnect.dbConnection

import com.example.cmcconnect.repository.testRepo
import com.example.cmcconnect.repository.testRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.jan.supabase.postgrest.Postgrest

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun provideTestRepo(postgrest: Postgrest):testRepo = testRepoImp(postgrest)
}