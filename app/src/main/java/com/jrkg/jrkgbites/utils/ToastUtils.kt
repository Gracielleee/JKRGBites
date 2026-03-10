package com.jrkg.jrkgbites.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.jrkg.jrkgbites.R

object ToastUtils {

    enum class ToastType {
        SUCCESS, ERROR, INFO, WARNING
    }

    /**
     * Shows a custom toast with a specific type and position.
     * @param durationMs If provided, will cancel the toast after this duration (e.g., 1000ms).
     * @param gravity The position of the toast on screen (default is BOTTOM).
     */
    fun showCustomToast(
        context: Context,
        message: String,
        type: ToastType = ToastType.INFO,
        durationMs: Long? = null,
        gravity: Int = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL,
        yOffset: Int = 100
    ) {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.layout_custom_toast, null)

        val text: TextView = layout.findViewById(R.id.toast_text)
        val icon: ImageView = layout.findViewById(R.id.toast_icon)
        val card: View = layout.findViewById(R.id.toast_card)

        text.text = message

        // Customize based on type
        when (type) {
            ToastType.SUCCESS -> {
                icon.setImageResource(R.drawable.ic_check_circle)
                icon.setColorFilter(context.getColor(R.color.md_theme_primary))
            }
            ToastType.ERROR -> {
                icon.setImageResource(R.drawable.ic_error)
                icon.setColorFilter(context.getColor(R.color.md_theme_error))
            }
            ToastType.WARNING -> {
                icon.setImageResource(R.drawable.ic_warning)
                icon.setColorFilter(context.getColor(R.color.md_theme_error_mediumContrast))
            }
            ToastType.INFO -> {
                icon.setImageResource(R.drawable.ic_info)
                icon.setColorFilter(context.getColor(R.color.md_theme_secondary))
            }
        }

        val toast = Toast(context).apply {
            setGravity(gravity, 0, yOffset)
            duration = if (durationMs != null && durationMs > 2000) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
            view = layout
        }

        toast.show()

        // If a specific duration in ms is requested (like 1 second = 1000ms), we cancel it after that delay.
        if (durationMs != null) {
            Handler(Looper.getMainLooper()).postDelayed({
                toast.cancel()
            }, durationMs)
        }
    }
}
