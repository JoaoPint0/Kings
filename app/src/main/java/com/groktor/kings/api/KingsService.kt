package com.groktor.kings.api

import android.util.Log
import com.groktor.kings.model.Account
import com.groktor.kings.model.Battle
import com.groktor.kings.model.Player
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val TAG = "KingsService"

fun searchBattles(
        service: KingsService,
        onSuccess: (battles: List<Battle>) -> Unit,
        onError: (error: String) -> Unit) {

    service.searchBattles().enqueue(
        object : Callback<List<Battle>> {

            override fun onFailure(call: Call<List<Battle>>?, t: Throwable) {
                Log.d(TAG, "fail to get data")
                Log.d(TAG, t.message)
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                    call: Call<List<Battle>>?,
                    response: Response<List<Battle>>
            ) {
                Log.d(TAG, "got a response ${response.body()}")
                if (response.isSuccessful) {
                    val battles = response.body()!!
                    onSuccess(battles)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

fun searchPlayers(
        service: KingsService,
        onSuccess: (battles: List<Player>) -> Unit,
        onError: (error: String) -> Unit) {

    service.searchPlayers().enqueue(
        object : Callback<List<Player>> {

            override fun onFailure(call: Call<List<Player>>?, t: Throwable) {
                Log.d(TAG, "fail to get players data")
                Log.d(TAG, t.message)
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                    call: Call<List<Player>>?,
                    response: Response<List<Player>>
            ) {
                Log.d(TAG, "got a response ${response.body()}")
                if (response.isSuccessful) {
                    val players = response.body()!!
                    onSuccess(players)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

fun searchBattleById(
        service: KingsService,
        id:String,
        onSuccess: (battle: Battle?) -> Unit,
        onError: (error: String) -> Unit) {

    service.getBattleById(id).enqueue(
        object : Callback<Battle> {

            override fun onFailure(call: Call<Battle>?, t: Throwable) {
                Log.d(TAG, "fail to get data")
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                    call: Call<Battle>?,
                    response: Response<Battle>
            ) {
                Log.d(TAG, "got a response ${response.body()}")
                if (response.isSuccessful) {
                    val battles = response.body()
                    onSuccess(battles)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

fun searchAccount(
        service: KingsService,
        username: String,
        onSuccess: (account: Account?) -> Unit,
        onError: (error: String) -> Unit) {

    service.getAccount(username).enqueue(
        object : Callback<Account> {
            override fun onFailure(call: Call<Account>?, t: Throwable) {
                Log.d(TAG, "fail to get data")
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                    call: Call<Account>?,
                    response: Response<Account>
            ) {
                Log.d(TAG, "got a response $response")
                if (response.isSuccessful) {
                    val account = response.body()
                    onSuccess(account)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}


interface KingsService {

    @GET("battles")
    fun searchBattles(): Call<List<Battle>>

    @GET("players")
    fun searchPlayers(): Call<List<Player>>

    @GET("accounts/{id}")
    fun getAccount(@Path("id") id: String): Call<Account>

    @GET("battles/{id}")
    fun getBattleById(@Path("id") id: String): Call<Battle>


    companion object {
        private const val BASE_URL = "http://192.168.1.4:5000/"

        fun create(): KingsService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(KingsService::class.java)
        }
    }
}