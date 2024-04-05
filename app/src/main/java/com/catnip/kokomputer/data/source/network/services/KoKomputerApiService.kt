package com.catnip.kokomputer.data.source.network.services

import com.catnip.kokomputer.BuildConfig
import com.catnip.kokomputer.data.source.network.model.category.CategoriesResponse
import com.catnip.kokomputer.data.source.network.model.products.ProductResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface KoKomputerApiService {

    @GET("categories")
    suspend fun getCategories(): CategoriesResponse

    @GET("products")
    suspend fun getProducts(@Query("category") category: String? = null): ProductResponse

    companion object {
        @JvmStatic
        operator fun invoke(): KoKomputerApiService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(KoKomputerApiService::class.java)
        }
    }
}