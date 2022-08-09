package com.example.testaxiata.core

import android.app.Application
import com.example.testaxiata.di.networkModule
import com.example.testaxiata.di.repositoryModule
import com.example.testaxiata.di.useCaseModule
import com.example.testaxiata.di.viewModelModule
import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Paper.init(this)

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}