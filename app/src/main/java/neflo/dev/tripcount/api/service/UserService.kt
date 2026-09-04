package neflo.dev.tripcount.api.service

import neflo.dev.tripcount.api.model.group.GroupDTO
import neflo.dev.tripcount.api.model.user.UserDTO
import neflo.dev.tripcount.api.model.user.UserResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {

    @GET("users/profile")
    suspend fun getUserProfile() : Response<UserResponseDTO>

    @GET("users/groups")
    suspend fun getUserGroups() : Response<List<GroupDTO>>

    @GET("users/join/{groupCode}")
    suspend fun joinGroup(@Path("groupCode") groupCode: String) : Response<GroupDTO>

    @PUT("users/update")
    suspend fun updateUser(@Body userDTO: UserDTO) : Response<UserResponseDTO>

    @DELETE("users/delete")
    suspend fun deleteUser() : Response<Nothing>

}