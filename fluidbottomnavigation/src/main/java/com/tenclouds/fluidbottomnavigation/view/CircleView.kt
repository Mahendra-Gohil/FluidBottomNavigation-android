package com.tenclouds.fluidbottomnavigation.view

import android.animation.AnimatorSet
import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.tenclouds.fluidbottomnavigation.KEY_FRAME_IN_MS
import com.tenclouds.fluidbottomnavigation.extension.interpolators
import com.tenclouds.fluidbottomnavigation.extension.scaleAnimator
import com.tenclouds.fluidbottomnavigation.extension.translationYAnimator

class CircleView @JvmOverloads constructor(context: Context,
                                           attrs: AttributeSet? = null,
                                           defStyleAttr: Int = 0)
    : AppCompatImageView(context, attrs, defStyleAttr), AnimatedView {

    init {
        scaleY = 0f
        scaleX = 0f
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
                                scaleAnimator(0.0f, 1.0f, 7 * KEY_FRAME_IN_MS, interpolators[0]),
                                scaleAnimator(1.0f, 0.33f, 4 * KEY_FRAME_IN_MS, interpolators[2]),
                                scaleAnimator(0.33f, 1.2f, 7 * KEY_FRAME_IN_MS, interpolators[1]),
                                scaleAnimator(1.2f, 0.8f, 3 * KEY_FRAME_IN_MS, interpolators[1]),
                                scaleAnimator(0.8f, 1.0f, 3 * KEY_FRAME_IN_MS, interpolators[1]))
                    }

    private val selectMoveAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                translationYAnimator(
                                        0f,
                                        getItemYTransitionYValue(context) * 11 / 10,
                                        7 * KEY_FRAME_IN_MS,
                                        interpolators[0]),
                                translationYAnimator(
                                        getItemYTransitionYValue(context) * 11 / 10,
                                        getItemYTransitionYValue(context),
                                        3 * KEY_FRAME_IN_MS,
                                        interpolators[4]))
                        startDelay = 11 * KEY_FRAME_IN_MS
                    }

    private val deselectScaleAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                scaleAnimator(1.0f, 0.8f, 3 * KEY_FRAME_IN_MS, interpolators[1]),
                                scaleAnimator(0.8f, 1.2f, 3 * KEY_FRAME_IN_MS, interpolators[1]),
                                scaleAnimator(1.2f, 0.33f, 7 * KEY_FRAME_IN_MS, interpolators[1]),
                                scaleAnimator(0.33f, 1.0f, 6 * KEY_FRAME_IN_MS, interpolators[2]),
                                scaleAnimator(1.0f, 0.0f, 7 * KEY_FRAME_IN_MS, interpolators[0]))
                    }

    private val deselectMoveAnimator =
            AnimatorSet()
                    .apply {
                        playSequentially(
                                translationYAnimator(
                                        getItemYTransitionYValue(context),
                                        getItemYTransitionYValue(context) * 11 / 10,
                                        3 * KEY_FRAME_IN_MS,
                                        interpolators[4]),
                                translationYAnimator(
                                        getItemYTransitionYValue(context) * 11 / 10,
                                        0f,
                                        7 * KEY_FRAME_IN_MS,
                                        interpolators[0]))
                        startDelay = 6 * KEY_FRAME_IN_MS
                    }
}
