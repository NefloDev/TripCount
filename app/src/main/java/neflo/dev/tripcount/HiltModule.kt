package neflo.dev.tripcount

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import neflo.dev.tripcount.api.repository.AuthRepository
import neflo.dev.tripcount.api.repository.UserRepository
import neflo.dev.tripcount.api.service.AuthService
import neflo.dev.tripcount.api.service.UserService

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideAuthRepository(authService: AuthService) = AuthRepository(authService)

    @Provides
    fun provideUserRepository(userService: UserService) = UserRepository(userService)

}