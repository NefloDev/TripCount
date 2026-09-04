package neflo.dev.tripcount.api.repository

import neflo.dev.tripcount.api.model.helper.apiRequestFlow
import neflo.dev.tripcount.api.model.user.UserDTO
import neflo.dev.tripcount.api.service.UserService
import retrofit2.http.Body
import retrofit2.http.Path
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {

    fun getUserProfile() = apiRequestFlow {
        userService.getUserProfile()
    }
    fun getUserGroups() = apiRequestFlow {
        userService.getUserGroups()
    }
    fun joinGroup(@Path("groupCode") groupCode: String) = apiRequestFlow {
        userService.joinGroup(groupCode)
    }
    fun updateUser(@Body userDTO: UserDTO) = apiRequestFlow {
        userService.updateUser(userDTO)
    }
    fun deleteUser() = apiRequestFlow {
        userService.deleteUser()
    }

}