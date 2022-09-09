package co.kr.hoyaho

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WantedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}