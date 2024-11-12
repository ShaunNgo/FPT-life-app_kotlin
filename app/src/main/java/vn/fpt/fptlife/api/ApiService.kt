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
                builder.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6InB1YmxpYzo1YzM0ZTUyZC0yNDczLTQ4MDktODg2Zi0zM2QwZjAwNTM3MTAiLCJ0eXAiOiJKV1QifQ.eyJhY3IiOiJhYWwxIiwiYW1yIjpbInB3ZCJdLCJhdWQiOltdLCJhdXRob3JpemVfdG9rZW4iOm51bGwsImNsaWVudF9pZCI6ImZzaC1jbGllbnQiLCJleHAiOjE3MzM2NDc3NzMsImV4dCI6eyJhY3IiOiJhYWwxIiwiYW1yIjpbInB3ZCJdLCJlbWFpbCI6bnVsbCwicGhvbmUiOiIwOTgzMDY3Mzc5IiwicGhvbmVfbnVtYmVyIjoiMDk4MzA2NzM3OSIsInByZWZlcnJlZF91c2VybmFtZSI6IjA5ODMwNjczNzkiLCJwcm9jZXNzX2lkIjowLCJzZXNzaW9uX2lkIjoiOGRjYmVjYmItZmIwYy00NDQ4LWE0YzAtYmY3YzFmYzhkZmJmIiwidXNlcl9pZCI6MjEyNywidXNlcm5hbWUiOiIwOTgzMDY3Mzc5IiwidXVpZCI6IjFjNzI0NGFlLWNjNzYtMTFlZS1iNWRkLTA0MDMwMDAwMDAwMCJ9LCJpYXQiOjE3MzEwNTU3NzMsImlzcyI6Imh0dHBzOi8vYWNjb3VudHMtc3RhZy5mcHQudm4vIiwianRpIjoiZjVjNWRiNzAtYWRlZi00NjljLTkxNjMtNTY0NWQzZjk1ZjMyIiwibmJmIjoxNzMxMDU1NzczLCJzY3AiOlsib3BlbmlkIiwicGhvbmUiLCJlbWFpbCIsInByb2ZpbGUiXSwic3ViIjoiMDk4MzA2NzM3OSJ9.WG2kl2piX1i90CrcFxxREJnnb-6m3U-RB5ZTjEyrHQFcS5AwI4giQaK8YnF1ie_TPBMH2rmlueIIegnyLs5yo3N3n0VIaRioz2c3M0rGWET_qr2B5fyaQ18jNICRxKdEQwcnMvjxHeOlv1fc-TW7G7XfA4VUi0rDQaYsLouGgq2jrqEjsI-LoEEUR-_OhEXhUhW9AwUAhWHKOcPaBqv7cjNn2YV6ouZgDdhiIpy0IoprDVDSVztDrd3hzDaAMd4Ci1Awxw_TZVDTnZqKsug1ph4nq4B4imGPHENCPSHPcTg5dZOD7wobxFQGNLK9oKcyFg_NeOMfSbQuC6UD57ar5Cimr4VRLbKPwBZD4r31ow83f7X79xHWXB04v5wiKvdoahkfKhBbY7swa3Q8Y7L7Gr3jrIwLmDIZTAuKFXpGjnUgAcw1_g879mW8v8AfRoi1uoznWCn8KYpCyPy1Pj7ibY8SexrFVDptOoB7rp3u813hcBqgZhZ-zjf7MC2LGwfYopIsgdL76znVjKMXWYo8h1vVx1r3cjdlQ7v-12dhKneTBUNr3uTW3LhS61O9AHUKFl6sOUuZkJXU8cwsYT-Iu7HuWz8VVRbEUsGrTe5hATsd-gLDRj3arYLWtTlup9r65e8CTyCquWivh7gYy7jBcfHK0AyDCuFlqUXdC_OLhJg")
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
