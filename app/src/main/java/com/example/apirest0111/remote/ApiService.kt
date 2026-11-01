import com.example.apirest0111.data.model.Post
import retrofit2.http.GET

// Esta interfaz define los endpoints HTTP
interface ApiService {

    // Define una solicitud GET al endpoint /posts
    @GET("/posts")
    suspend fun getPosts(): List<Post>
}

