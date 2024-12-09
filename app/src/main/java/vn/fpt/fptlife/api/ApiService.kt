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
                builder.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6InB1YmxpYzo1YzM0ZTUyZC0yNDczLTQ4MDktODg2Zi0zM2QwZjAwNTM3MTAiLCJ0eXAiOiJKV1QifQ.eyJhY3IiOiJhYWwxIiwiYW1yIjpbInB3ZCJdLCJhdWQiOltdLCJhdXRob3JpemVfdG9rZW4iOm51bGwsImNsaWVudF9pZCI6ImZzaC1jbGllbnQiLCJleHAiOjE3MzYzMjI2MTUsImV4dCI6eyJhY3IiOiJhYWwxIiwiYW1yIjpbInB3ZCJdLCJlbWFpbCI6bnVsbCwicGhvbmUiOiIwOTgzMDY3Mzc5IiwicGhvbmVfbnVtYmVyIjoiMDk4MzA2NzM3OSIsInByZWZlcnJlZF91c2VybmFtZSI6IjA5ODMwNjczNzkiLCJwcm9jZXNzX2lkIjowLCJzZXNzaW9uX2lkIjoiNWJiMTQ4ZjgtMDRiZC00YTE5LWJiMDEtNDZjOGM0Y2ExYWNlIiwidXNlcl9pZCI6MjEyNywidXNlcm5hbWUiOiIwOTgzMDY3Mzc5IiwidXVpZCI6IjFjNzI0NGFlLWNjNzYtMTFlZS1iNWRkLTA0MDMwMDAwMDAwMCJ9LCJpYXQiOjE3MzM3MzA2MTQsImlzcyI6Imh0dHBzOi8vYWNjb3VudHMtc3RhZy5mcHQudm4vIiwianRpIjoiYjQyMzk2N2QtOTk0NS00ZjA4LTkzNzAtYzFhY2RhNTJiMGJhIiwibmJmIjoxNzMzNzMwNjE0LCJzY3AiOlsib3BlbmlkIiwicGhvbmUiLCJlbWFpbCIsInByb2ZpbGUiXSwic3ViIjoiMDk4MzA2NzM3OSJ9.gwLObl4MxKzgX4UtMZ0Eu4pR2-AXYcz39S2J_lMeV6jpv9REyMFniUIiebmM47XGUPiTgoMdPOGXtY8jn3ICUfzhz4J8ldFYCaC9SjDpu79LK0A5kHJW2BHZPZdnrSh5u6vSxtqr3IwkMb7B1Cae--MkI_8kJ-f1y2xhov8HsPNhtgDdd9VZ0rSwLKmchWZM4Mmolv3iY-ECXaZhBymQC5zBjfXamy5faNTHDN6TkkljYBmDH1Imor86fKG7QXY7uP0PLnfFHPOjFeB3M0vmLqLySZJ34WyECse0m4NznpgoVRrfgHuBQAtdkj8IIzkpGwjqsGzxyuYsQlobcfxQepqD0H6_W2r4dBbQtWuAbiCZ3AyPzwTWwMK8CCiF5tH8odFqcgHd0DQDTRVDrQZwDbFeo0JZq_v0mF_UcxyvxdL2PKd5wWHhHYTZGEoHoL7-9QFBVK2DtZCox5CPMcF5DTov8mW6i0RWpkIuVXhCqqJiKN6ZdrOEBA2tmOQqqSLfAfDRxJmJlC6HXauDNINU0-wOlyZ2KStDa6BnGP12KI0S9-AmgML2E6Wz1WPRbDhILax8pjH9Gn6QbyfDOsvcczDX0e7p9BciM1n_bUF1UNqWe-c7YHoE_qbjqfw0OsoFyLfgigYbwa0_REjaFgwyN3nDvNwjZKu--l0Ga9hOXeg")
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
