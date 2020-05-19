package com.homm3.livewallpaper.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.app.KtxGame

class Engine : KtxGame<Screen>(null, true) {
    var onSettingsButtonClick: () -> Unit = { }

    internal lateinit var assets: Assets
    internal lateinit var skin: Skin
    internal val camera = OrthographicCamera()
    internal val viewport = ScreenViewport(camera)

    override fun create() {
        assets = Assets()
        skin = assets.loadSkin()
        assets.tryLoadWallpaperAssets()
        addScreen(LoadingScreen(this))
        addScreen(SettingsScreen(this))
        Gdx.app.postRunnable(::updateVisibleScreen)
    }

    fun updateVisibleScreen() {
        if (assets.isWallpaperAssetsLoaded()) {
            if (!screens.containsKey(WallpaperScreen::class.java)) {
                addScreen(WallpaperScreen(this))
            }
            setScreen<WallpaperScreen>()
        } else {
            assets.tryLoadWallpaperAssets()

            if (!assets.manager.isFinished) {
                setScreen<LoadingScreen>()
                return
            }

            setScreen<SettingsScreen>()
        }
    }

    override fun resume() {
        super.resume()
        updateVisibleScreen()
    }
}