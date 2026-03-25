package com.jrkg.jrkgbites.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.jrkg.jrkgbites.model.Restaurant

class SpinWheelView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var restaurants: List<Restaurant> = emptyList()
    private val slicePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.FILL }
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textAlign = Paint.Align.CENTER
    }
    
    private var rotationAngle = 0f
    private var isSpinning = false
    private val sliceColors = mutableListOf<Int>()
    private var animator: ValueAnimator? = null

    var isGrayscale: Boolean = false
        set(value) {
            field = value
            updatePaints()
            invalidate()
        }

    private fun updatePaints() {
        if (isGrayscale) {
            val matrix = ColorMatrix().apply { setSaturation(0f) }
            val filter = ColorMatrixColorFilter(matrix)
            slicePaint.colorFilter = filter
            // Optionally keep text white or make it slightly darker
            textPaint.colorFilter = filter
        } else {
            slicePaint.colorFilter = null
            textPaint.colorFilter = null
        }
    }

    // Palette inspired by your project's md_theme colors
    private val wheelColors = listOf(
        "#86521A", // md_theme_primary
        "#735943", // md_theme_secondary
        "#596339", // md_theme_tertiary
        "#A04523", // Accent Orange-Brown
        "#5D4037", // Darker Brown
        "#424B23"  // Darker Tertiary
    )

    fun setRestaurants(items: List<Restaurant>) {
        this.restaurants = items
        this.sliceColors.clear()
        if (items.isNotEmpty()) {
            val colorList = wheelColors.map { Color.parseColor(it) }
            for (i in items.indices) {
                sliceColors.add(colorList[i % colorList.size])
            }
        }
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (restaurants.isEmpty()) return

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = (Math.min(width, height) / 2f) * 0.85f
        val centerX = width / 2f
        val centerY = height / 2f

        val rectF = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        val sweepAngle = 360f / restaurants.size

        canvas.save()
        canvas.rotate(rotationAngle, centerX, centerY)

        for (i in restaurants.indices) {
            slicePaint.color = sliceColors[i]
            val startAngle = i * sweepAngle
            canvas.drawArc(rectF, startAngle, sweepAngle, true, slicePaint)

            // Draw restaurant name inside the slice
            drawSliceText(canvas, centerX, centerY, radius, startAngle, sweepAngle, restaurants[i].name ?: "")
        }
        canvas.restore()

        // Draw the static indicator at the top
        drawPointer(canvas, centerX, centerY, radius)
    }

    private fun drawSliceText(canvas: Canvas, cx: Float, cy: Float, radius: Float, startAngle: Float, sweepAngle: Float, text: String) {
        canvas.save()
        val angle = startAngle + sweepAngle / 2f
        canvas.rotate(angle, cx, cy)

        textPaint.textSize = radius * 0.12f
        val x = cx + radius * 0.55f
        val y = cy + (textPaint.textSize / 3f)

        // Truncate if name is too long for the slice
        val maxWidth = radius * 0.45f
        val truncatedText = TextUtils.ellipsize(text, textPaint, maxWidth, TextUtils.TruncateAt.END).toString()

        canvas.drawText(truncatedText, x, y, textPaint)
        canvas.restore()
    }

    private fun drawPointer(canvas: Canvas, cx: Float, cy: Float, radius: Float) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#BA1A1A") // md_theme_error (Red)
            style = Paint.Style.FILL
        }
        val path = Path()
        val pointerSize = radius * 0.15f
        path.moveTo(cx, cy - radius - (pointerSize / 2f))
        path.lineTo(cx - pointerSize / 2f, cy - radius + (pointerSize / 2f))
        path.lineTo(cx + pointerSize / 2f, cy - radius + (pointerSize / 2f))
        path.close()
        canvas.drawPath(path, paint)
    }

    fun spinTo(targetIndex: Int, onEnd: () -> Unit) {
        if (isSpinning || restaurants.isEmpty()) return
        isSpinning = true

        val sweepAngle = 360f / restaurants.size
        // 270 degrees is the top position in Android's coordinate system
        val targetCenterAngle = targetIndex * sweepAngle + sweepAngle / 2f
        val extraSpins = (6..10).random() * 360f
        val finalRotation = extraSpins + (270f - targetCenterAngle)

        animator = ValueAnimator.ofFloat(rotationAngle % 360f, finalRotation).apply {
            duration = 4000
            interpolator = DecelerateInterpolator()
            addUpdateListener {
                rotationAngle = it.animatedValue as Float
                invalidate()
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    isSpinning = false
                    onEnd()
                }
            })
            start()
        }
    }

    // Fast-forward feature for impatient users
    fun fastForward() {
        animator?.let { 
            if (it.isRunning && it.currentPlayTime < it.duration - 500) {
                it.currentPlayTime = it.duration - 500 
            }
        }
    }
}
