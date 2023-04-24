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

//        FirebaseApp.initializeApp(applicationContext)

        startDIifNeed(this)
    }

    companion object {

        @Synchronized
        fun startDIifNeed(context: Context) {

            startKoin {
                androidContext(context)
                modules(
                    listOf(
                        MainModules.create(),
//                        module {
//                            FirebaseApp.initializeApp(context)
//                        }
                    )
                )
            }
        }
    }
}