package com.xxcactussell.presentation.tools

import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.graphics.Path
import kotlin.math.sin
import kotlin.random.Random


private data class Particle(
    val creationTime: Long,
    val pathBounds: Rect,
    val initialX: Float,
    val initialY: Float,
    val velocityX: Float,
    val velocityY: Float,
    val maxLife: Long,
    val radius: Float,
    val color: Color
)

@Composable
fun SpoilerEffect(
    modifier: Modifier = Modifier,
    textLayoutResult: TextLayoutResult?,
    spoilerPaths: List<Path>,
    particleColor: Color
) {
    val particles = remember { mutableListOf<Particle>() }
    val random = remember { Random(System.currentTimeMillis()) }

    val time by produceState(0L) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it
            }
        }
    }

    Canvas(modifier = modifier) {
        if (textLayoutResult == null || spoilerPaths.isEmpty()) return@Canvas
        particles.removeAll { (time - it.creationTime) > it.maxLife }
        if (particles.size < 150) {
            repeat(5) {
                val path = spoilerPaths.random(random)
                val bounds = path.getBounds()
                particles.add(
                    Particle(
                        creationTime = time,
                        pathBounds = bounds,
                        initialX = random.nextFloat() * bounds.width + bounds.left,
                        initialY = random.nextFloat() * bounds.height + bounds.top,
                        velocityX = (random.nextFloat() - 0.5f) * 0.5f,
                        velocityY = (random.nextFloat() - 0.5f) * 0.5f,
                        maxLife = random.nextLong(1500, 3000),
                        radius = random.nextFloat() * 1.5f + 1f,
                        color = particleColor
                    )
                )
            }
        }

        particles.forEach { particle ->
            val age = time - particle.creationTime
            val lifeRatio = age.toFloat() / particle.maxLife
            val currentX = particle.initialX + particle.velocityX * age
            val currentY = particle.initialY + particle.velocityY * age + sin(age / 200f) * 2f
            val alpha = (1f - lifeRatio).coerceIn(0f, 1f)

            if (particle.pathBounds.contains(Offset(currentX, currentY))) {
                drawCircle(
                    color = particle.color,
                    radius = particle.radius,
                    center = Offset(currentX, currentY),
                    alpha = alpha
                )
            }
        }
    }
}