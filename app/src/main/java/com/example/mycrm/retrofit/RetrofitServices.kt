import com.example.mycrm.Project
import com.example.mycrm.User
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("projects")
    fun getProjectsList(): Call<MutableList<Project>>

    @POST("user/login")
    fun login(@Body userName: String, password: String): String
}