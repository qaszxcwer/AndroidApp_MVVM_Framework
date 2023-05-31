package com.example.mvvmframe.api


import android.content.Context
import com.example.mvvmframe.BuildConfig
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import person.qaszxcwer.appbaseframe.BaseApplication
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *
 * date: 2023/5/5
 * 
 */
class RetrofitClient {
    companion object {
        private const val BaseUrl = BuildConfig.API_HOST // todo 替换服务器地址
        private const val CacheMaxSize: Long = 1024 * 1024 * 50 // 50MB的网络缓存大小
        private const val CacheFileName = "OkHttpCache"
        private const val TimeOutSeconds: Long = 60
        private val TimeOutUnit: TimeUnit = TimeUnit.SECONDS

        val instance: RetrofitClient by lazy {
            RetrofitClient()
        }
    }

    private var retrofit: Retrofit? = null

    val usualApi: ApiRequest by lazy {
        getRetrofit().create(ApiRequest::class.java)
    }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(getOkHttpClient(BaseApplication.application.applicationContext))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        }
        return retrofit!!
    }

    private fun getOkHttpClient(context: Context): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val cacheFile = File(context.cacheDir, CacheFileName)
        val cache = Cache(cacheFile, CacheMaxSize)
        val loggingInterceptor = HttpLoggingInterceptor()
        //debug下展示完整的请求地址到logcat
        loggingInterceptor.setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )
        builder.run {
            cache(cache)
            connectTimeout(TimeOutSeconds, TimeOutUnit)
            readTimeout(TimeOutSeconds, TimeOutUnit)
            writeTimeout(TimeOutSeconds, TimeOutUnit)
            retryOnConnectionFailure(true) // 错误重连
            addInterceptor(loggingInterceptor) // 拦截器-日志打印
        }

        return  builder.build()
    }
}