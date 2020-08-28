package igor.kuridza.ferit.hr.najboljiucenik.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NajboljiUcenikApp : Application() {
    companion object {
        lateinit var instance: NajboljiUcenikApp
            private set

        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}