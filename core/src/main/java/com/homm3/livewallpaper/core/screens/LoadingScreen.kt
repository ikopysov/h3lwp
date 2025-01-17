package com.homm3.livewallpaper.core.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
import com.badlogic.gdx.utils.Align
import com.homm3.livewallpaper.core.Assets
import ktx.actors.stage
import ktx.app.KtxScreen
import ktx.app.clearScreen

class LoadingScreen(private val assets: Assets, val onLoadDone: () -> Unit) : KtxScreen {
    private val label =
        Label(assets.i18n.get("loading"), assets.skin)
            .apply {
                setFillParent(true)
                setAlignment(Align.center)
                setWrap(true)
            }
    private val loadingStage = stage().apply { addActor(label) }

    override fun render(delta: Float) {
        clearScreen(0f, 0f, 0f, 1f)
        loadingStage.draw()

        if (assets.manager.update()) {
            onLoadDone()
        }
    }

    override fun resize(width: Int, height: Int) {
        loadingStage.viewport.update(width, height, true)
    }

    override fun dispose() {
        loadingStage.dispose()
    }
}