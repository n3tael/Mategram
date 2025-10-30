package com.xxcactussell.mategram

import android.app.Application
import com.xxcactussell.data.TdClientManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MategramApplication : Application() {
    @Inject
    lateinit var tdClientManager: TdClientManager
    override fun onCreate() {
        super.onCreate()
        tdClientManager.initialize(this)
    }
}