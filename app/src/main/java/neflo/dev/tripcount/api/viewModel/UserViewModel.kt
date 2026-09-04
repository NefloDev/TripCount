package neflo.dev.tripcount.api.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import neflo.dev.tripcount.api.model.group.GroupDTO
import neflo.dev.tripcount.api.model.helper.ApiResponse
import neflo.dev.tripcount.api.model.helper.BaseViewModel
import neflo.dev.tripcount.api.model.user.UserDTO
import neflo.dev.tripcount.api.model.user.UserResponseDTO
import neflo.dev.tripcount.api.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository): BaseViewModel() {
    private val _userProfile = MutableStateFlow<ApiResponse<UserResponseDTO>?>(null)
    val userProfile: StateFlow<ApiResponse<UserResponseDTO>?> = _userProfile

    private val _userGroups = MutableStateFlow<ApiResponse<List<GroupDTO>>?>(null)
    val userGroups: StateFlow<ApiResponse<List<GroupDTO>>?> = _userGroups

    private val _joinedGroup = MutableStateFlow<ApiResponse<GroupDTO>?>(null)
    val joinedGroup: StateFlow<ApiResponse<GroupDTO>?> = _joinedGroup


    fun getUserProfile() = responseRequest(_userProfile) {
        userRepository.getUserProfile()
    }

    fun getUserGroups(errorHandler: CoroutinesErrorHandler) = responseRequest(_userGroups, errorHandler) {
        userRepository.getUserGroups()
    }

    fun joinGroup(groupCode: String) = responseRequest(_joinedGroup) {
        userRepository.joinGroup(groupCode)
    }

    fun updateUser(dto: UserDTO) = responseRequest(_userProfile) {
        userRepository.updateUser(dto)
    }

    fun deleteUser() = performRequest {
        userRepository.deleteUser()
    }

}