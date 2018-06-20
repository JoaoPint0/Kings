package com.groktor.kings

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Interpolator
import android.widget.ImageView

/**
 * [android.view.View.OnClickListener] used to translate the product grid sheet downward on
 * the Y-axis when the navigation icon in the toolbar is pressed.
 */
class NavigationIconClickListener @JvmOverloads internal constructor(
        private val context: Context, private val sheet: View,
        private val interpolator: Interpolator? = null,
        private val openIcon: Drawable? = null,
        val closeIcon: Drawable? = null) : View.OnClickListener {

    private val animatorSet = AnimatorSet()
    private val height: Int
    private var backdropShown = false
    private lateinit var imageView:ImageView

    init {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels
    }

    override fun onClick(view: View) {
        if (view !is ImageView) {
            throw IllegalArgumentException("updateIcon() must be called on an ImageView")
        }
        imageView = view

        animateBackdrop()
    }

    private fun updateIcon() {
        if (openIcon != null && closeIcon != null) {
            if (backdropShown) {
                imageView.setImageDrawable(closeIcon)
            } else {
                imageView.setImageDrawable(openIcon)
            }
        }
    }

    fun animateBackdrop(){
        backdropShown = !backdropShown

        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        updateIcon()

        val translateY = height - context.resources.getDimensionPixelSize(R.dimen.nav_host_reveal_height)

        val animator = ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())
        animator.duration = 500
        if (interpolator != null) {
            animator.interpolator = interpolator
        }
        animatorSet.play(animator)
        animator.start()
    }
}
