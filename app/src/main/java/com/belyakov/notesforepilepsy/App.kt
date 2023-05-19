package com.belyakov.notesforepilepsy

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startDIifNeed(this@App)
    }

    companion object {

        @Synchronized
        fun startDIifNeed(context: Context) {

            startKoin {
                androidContext(context)
                modules(
                    listOf(
                        MainModules.create(context.getString(R.string.firebase_database_url)),
                        module {
                            FirebaseApp.initializeApp(context)
                        }
                    )
                )
            }
        }
    }
}