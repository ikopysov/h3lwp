package com.homm3.livewallpaper.android

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.badlogic.gdx.backends.android.AndroidLiveWallpaperService
import com.homm3.livewallpaper.android.data.WallpaperPreferencesRepository
import com.homm3.livewallpaper.android.data.dataStore

class LiveWallpaperService : AndroidLiveWallpaperService() {
    private val prefs = WallpaperPreferencesRepository(dataStore)

    override fun onCreateEngine(): Engine {
        return AndroidWallpaperEngine()
    }

    override fun onCreateApplication() {
        super.onCreateApplication()

        initialize(
            AndroidEngine(this, prefs.preferencesFlow),
            AndroidApplicationConfiguration().apply {
                useAccelerometer = false
                useCompass = false
                disableAudio = true
            }
        )
    }
}