package com.tenclouds.fluidbottomnavigation.view

import android.animation.AnimatorSet
import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.tenclouds.fluidbottomnavigation.KEY_FRAME_IN_MS
import com.tenclouds.fluidbottomnavigation.extension.interpolators
import com.tenclouds.fluidbottomnavigation.extension.scaleYAnimator
import com.tenclouds.fluidbottomnavigation.extension.translationYAnimator

class RectangleView @JvmOverloads constructor(context: Context,
                                              attrs: AttributeSet? = null,
                                              defStyleAttr: Int = 0)
    : AppCompatImageView(context, attrs, defStyleAttr), AnimatedView {

    init {
        scaleY = 0f
    }

    override val selectAnimator by lazy {
        AnimatorSet().apply {
            playTogether(
                    selectScaleAnimator,
                    selectMoveAnimator)
        }
    }

    override val deselectAnimator by lazy {
        AnimatorSet().apply {
            playTogether(
                    deselectScaleAnimator,
                    deselectMoveAnimator)
        }
    }

    private val selectScaleAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                scaleYAnimator(0.0f, 0.8f, 3 * KEY_FRAME_IN_MS, interpolators[1]),
                                scaleYAnimator(0.8f, 0.0f, 5 * KEY_FRAME_IN_MS, interpolators[1]))
                        startDelay = 11 * KEY_FRAME_IN_MS
                    }

    private val selectMoveAnimator =
            AnimatorSet()
                    .apply {
                        play(
                                translationYAnimator(
                                        0f,
                                        getItemYTransitionYValue(context),
                                        5 * KEY_FRAME_IN_MS,
                                        interpolators[1]))
                        startDelay = 14 * KEY_FRAME_IN_MS
                    }

    private val deselectScaleAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                scaleYAnimator(0.0f, 0.8f, 5 * KEY_FRAME_IN_MS, interpolators[1]),
                                scaleYAnimator(0.8f, 0.0f, 3 * KEY_FRAME_IN_MS, interpolators[1]))
                        startDelay = 4 * KEY_FRAME_IN_MS
                    }

    private val deselectMoveAnimator =
            AnimatorSet()
                    .apply {
                        play(
                                translationYAnimator(
                                        getItemYTransitionYValue(context) * 3 / 5,
                                        0f,
                                        2 * KEY_FRAME_IN_MS,
                                        interpolators[1]))
                        startDelay = 4 * KEY_FRAME_IN_MS
                    }
}
