package android.kode.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *@author Moroz V.A. on 05.03.2022
 **/

class ApiClient private constructor() {

    val api: ApiInterface
        get() = retrofit!!.create(
            ApiInterface::class.java
        )

    init {

        retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetworkModule.httpClient) //таймауты
                .build()

    }

    companion object {

        private const val BASE_URL =
            "https://stoplight.io/mocks/kode-education/trainee-test/25143926/"

        private var apiClient: ApiClient? = null
        private var retrofit: Retrofit? = null

        val instance: ApiClient?
            @Synchronized get() {

                if (apiClient == null) {

                    apiClient =
                        ApiClient()
                }

                return apiClient

            }
    }
}