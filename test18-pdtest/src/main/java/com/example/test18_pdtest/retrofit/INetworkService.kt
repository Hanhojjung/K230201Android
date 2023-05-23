import com.example.test18_pdtest.Model.UserListModel
import com.example.test18_pdtest.Model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface INetworkService {

    @GET("getFoodKr")
//    @GET("api/users")
    // baseurl : https://reqres.in/
    // https://reqres.in/api/users?page=2
    // 예를 들어 doGetList(2) -> page=2 의미
    //공공데이터 주소 샘플
    // http://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=인증키&numOfRows=10&pageNo=1
    // https://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=인증키&resultType=JSON&numOfRows=20&pageNo=1
    fun doGetUserList
                (@Query("serviceKey") serviceKey : String,
                 @Query("numOfRows") numOfRows: Int,
                 @Query("pageNo") pageNo: Int,
                 @Query("resultType") resultType: String
    ): Call<UserListModel>

    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

//    @GET("users/list?sort=desc")
    @GET("/api/users/2")
    fun test1(): Call<UserModel>
}