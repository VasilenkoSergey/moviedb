package io.vasilenko.moviedb.network

import io.vasilenko.moviedb.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiClient {

    fun moviesApi(): MoviesApi {
        return getRetrofit(getOkHttp()).create(MoviesApi::class.java)
    }

    fun tvShowsApi(): TvShowsApi {
        return getRetrofit(getOkHttp()).create(TvShowsApi::class.java)
    }

    private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.THE_MOVIE_DATABASE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        builder.addInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.THE_MOVIE_DATABASE_API}")
                    .url(
                        chain.request().url
                            .newBuilder()
                            .addQueryParameter("language", "ru")
                            .build()
                    )
                    .build()
            )
        }

        return builder.build()
    }
}
