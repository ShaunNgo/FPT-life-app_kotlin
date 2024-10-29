package vn.fpt.fptlife.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import vn.fpt.fptlife.model.DeviceResponse
import vn.fpt.fptlife.model.GetAllDeviceHomeIndexResponse
import vn.fpt.fptlife.model.HousesResponse
import vn.fpt.fptlife.model.RoomResponse
import java.util.concurrent.TimeUnit

interface ApiService {
    companion object {
        val gson
            get() = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create()

        val interceptor
            get() = Interceptor { chain ->
                val request = chain.request()
                val builder: Request.Builder = request.newBuilder()
                builder.addHeader("x-api-key", "flksjtfejalokl#!23sds")
                builder.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6InB1YmxpYzo1YzM0ZTUyZC0yNDczLTQ4MDktODg2Zi0zM2QwZjAwNTM3MTAiLCJ0eXAiOiJKV1QifQ.eyJhY3IiOiJhYWwxIiwiYW1yIjpbIm90cCJdLCJhdWQiOltdLCJhdXRob3JpemVfdG9rZW4iOm51bGwsImNsaWVudF9pZCI6ImZzaC1jbGllbnQiLCJleHAiOjE3MzEwNTUyOTgsImV4dCI6eyJhY3IiOiJhYWwxIiwiYW1yIjpbIm90cCJdLCJlbWFpbCI6bnVsbCwicGhvbmUiOiIwOTgzMDY3Mzc5IiwicGhvbmVfbnVtYmVyIjoiMDk4MzA2NzM3OSIsInByZWZlcnJlZF91c2VybmFtZSI6IjA5ODMwNjczNzkiLCJwcm9jZXNzX2lkIjowLCJzZXNzaW9uX2lkIjoiOGRjYmVjYmItZmIwYy00NDQ4LWE0YzAtYmY3YzFmYzhkZmJmIiwidXNlcl9pZCI6MjEyNywidXNlcm5hbWUiOiIwOTgzMDY3Mzc5IiwidXVpZCI6IjFjNzI0NGFlLWNjNzYtMTFlZS1iNWRkLTA0MDMwMDAwMDAwMCJ9LCJpYXQiOjE3Mjg0NjMyOTcsImlzcyI6Imh0dHBzOi8vYWNjb3VudHMtc3RhZy5mcHQudm4vIiwianRpIjoiOTM2MzliMTQtZDEwNy00MjFmLTk4NTMtYWU1OGJiNTBmNjY2IiwibmJmIjoxNzI4NDYzMjk3LCJzY3AiOlsib3BlbmlkIiwicGhvbmUiLCJlbWFpbCIsInByb2ZpbGUiXSwic3ViIjoiMDk4MzA2NzM3OSJ9.b4F3-GsOt2oEWdoom1Agh9ZI4wFZ063Rb_O113UWj-WGAi4js2CmMOPYRzm65lLvFqLjaUxDsTCdNUEwwgaC5ou_cnaQMCr1TdLYIdsCWCWyoTI5iYzFeP89HnPDOswV9gp0Dlj9PoB-ey34nyDf4wcjIe9BOvApDQNnnpEkrDpJincyo7HLWghyaHHqwSfdaPTqd88X-aSJ1IUfsobU6OkZgGg3GQrKHppz1mJ8jdtu6wo-e2euzRNU_xphWKwe6ET4OsODy9_0eMUKq0p0tmgSESp2gPgHhdtCShf-8-IeuDz2qKZcQSOib5e3rJARIRSwJqxtrSQ-lGKoJiZes1qjwc6rjVoDJLTFFlqmB3Ni6MHhlwgRPNynZTOJJFQm60VM3uOrxDNVTXy2l0infwhP8F-lMPgVfa3RMd586Uu4Ot_orqxE1PBFbXfTr6o_iwtZ3IEL__gHxr8Cy2hys4gkWMyRJVay2IkS8yZ3oDdDJ7iEtSaktfUWNP1MIC8fAFk75jOVukRL60wmLodn6MGLXSK-iplf_WfzCDgebyiic3tGyPZAIeeCQHntW4rQBqyGEf4Sqeroyz1kDrsrFq3rcQvKPBCN7BcEgvF50vdHuLSAGTQIujyNgRIsRA56-3Po_0q-u2eIdX-o3rw7p9cXKMxj0VI6UUq4kcHGaX8")
                builder.addHeader("Content-Type", "application/json")
                builder.addHeader("Cookie", "connect.sid=s%3AQFVvdlGYCQkYUTpPIjn3KMYvHpv_v5kP.JIHshHGtciTp8Xt9Dhb6Px0FXje6nuzZmvOdSqAqhww'")
                chain.proceed(builder.build())
            }

        val loggingInterceptor
            get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val okBuilder
            get() = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)

        val apiService
            get() = Retrofit.Builder()
                .baseUrl("https://api-fptlife-stag.fptsmarthome.vn")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okBuilder.build())
                .build()
                .create(ApiService::class.java)
    }

    @GET("/api/v1.2.5/public/houses?limit=20&page=1")
    fun getAllHouse(): Call<HousesResponse>

    //https://api-fptlife-stag.fptsmarthome.vn/api/v1.2.5/public/rooms?limit=20&page=1&profileHouseId=030b566c-ae24-4b52-af65-6f43b7ad226c
    @GET("/api/v1.2.5/public/rooms")
    fun getRoom(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("profileHouseId") profileHouseId: String?
    ): Call<RoomResponse>

    //https://api-fptlife-stag.fptsmarthome.vn/api/v1.2.5/public/home/index?profileHouseId=9268915b-a437-4173-b181-1175fc7010f5
    @GET("/api/v1.2.5/public/home/index")
    fun getDevice(@Query("profileHouseId") profileHouseId: String?): Call<DeviceResponse?>?

    @GET("/api/v1.2.5/public/home/index")
    fun getDeviceOfRoom(
        @Query("profileHouseId") profileHouseId: String?,
        @Query("profileRoomId") profileRoomId: String?
    ): Call<DeviceResponse>

    @GET("/api/v1.2.5/public/home/index")
    fun getDeviceFavorite(
        @Query("profileHouseId") profileHouseId: String?,
        @Query("isFavorited") isFavorited: Int
    ): Call<DeviceResponse>

    @GET
    fun getAllDeviceHomeIndex(@Url url: String?): Call<GetAllDeviceHomeIndexResponse>
}
