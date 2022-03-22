package com.example.test.di

import android.app.Application
import androidx.room.Room
import com.example.test.app.viewmodel.AppViewModel
import com.example.test.data.repository.AppRepository
import com.example.test.data.repository.AuthenticationDataSource
import com.example.test.data.resource.api.AppApi
import com.example.test.data.resource.database.AppDatabase
import com.example.test.data.resource.database.ProductDao
import com.example.test.utils.Constants
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { AppViewModel(get())}
}

val repositoryModule = module {
    fun provideAppRepository(api: AppApi, db: AppDatabase, dataSource: AuthenticationDataSource): AppRepository {
        return AppRepository(api, db, dataSource )
    }

    single { provideAppRepository(get(), get(), get()) }
}

val dataSourceModule = module {
    single { AuthenticationDataSource(get()) }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit) : AppApi {
        return retrofit.create(AppApi::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {
    fun provideGson() : Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient() : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient.Builder()
        return  okHttpClientBuilder.addInterceptor(httpLoggingInterceptor).build()
    }

    fun provideRetrofit(factory : Gson, client: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(),get()) }
}

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "asean")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideProductDao(get()) }
}