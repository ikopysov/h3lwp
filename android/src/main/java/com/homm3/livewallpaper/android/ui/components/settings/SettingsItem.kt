package com.homm3.livewallpaper.android.ui.components

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsItem(
    title: String,
    subtitle: String = "",
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    enabled: Boolean = true,
    nextLine: Boolean = false,
    action: @Composable (interactionSource: MutableInteractionSource) -> Unit = {}
) {
    val context = LocalContext.current.resources
    val displayMetrics = context.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                enabled = enabled,
                onClick = onClick
            )
    ) {
        Row(
            Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    title,
                    Modifier.width(screenWidth.times(0.8f).dp),
                    style = MaterialTheme.typography.body1,
                    color = if (enabled) Color.Unspecified else MaterialTheme.colors.onSurface.copy(
                        ContentAlpha.disabled
                    )
                )
                if (subtitle.isNotEmpty()) {
                    Text(
                        subtitle,
                        Modifier.width(screenWidth.times(0.8f).dp),
                        style = MaterialTheme.typography.body2,
                        color = if (enabled) MaterialTheme.colors.onSurface.copy(0.5f) else MaterialTheme.colors.onSurface.copy(
                            ContentAlpha.disabled
                        )
                    )
                }
                if (nextLine) {
                    action(interactionSource)
                }
            }

            if (!nextLine) {
                action(interactionSource)
            }
        }
    }
}