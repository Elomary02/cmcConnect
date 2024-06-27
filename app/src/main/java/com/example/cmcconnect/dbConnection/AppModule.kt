package com.example.cmcconnect.dbConnection

import com.example.cmcconnect.repository.sharedRepository.AuthenticationRepository
import com.example.cmcconnect.repository.sharedRepository.AuthenticationRepositoryImp
import com.example.cmcconnect.repository.sharedRepository.EventRepository
import com.example.cmcconnect.repository.sharedRepository.EventRepositoryImp
import com.example.cmcconnect.repository.sharedRepository.UserRepository
import com.example.cmcconnect.repository.sharedRepository.UserRepositoryImp
import com.example.cmcconnect.repository.testRepo
import com.example.cmcconnect.repository.testRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun provideTestRepo(postgrest: Postgrest): testRepo = testRepoImp(postgrest)
    @Provides
    fun provideSignInRepository(auth: Auth): AuthenticationRepository = AuthenticationRepositoryImp(auth)
    @Provides
    fun provideUserRepository(auth: Auth, postgrest: Postgrest) : UserRepository = UserRepositoryImp(auth, postgrest)
    @Provides
    fun provideEventRepository(postgrest: Postgrest): EventRepository = EventRepositoryImp(postgrest)
}