package example.app.test.utils.customView

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator

class WaveHelper(waveView: WaveView?) {
    private var mWaveView: WaveView? = waveView

    private var mAnimatorSet: AnimatorSet? = null

    init {
        initAnimation()
    }

    fun start() {
        mWaveView!!.isShowWave = true
        if (mAnimatorSet != null) {
            mAnimatorSet!!.start()
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun initAnimation() {
        val animators: MutableList<Animator> = ArrayList()

        // horizontal animation.
        // wave waves infinitely.
        val waveShiftAnim = ObjectAnimator.ofFloat(
            mWaveView,
            "waveShiftRatio",
            0f,
            1f
        )
        waveShiftAnim.repeatCount = ValueAnimator.INFINITE
        waveShiftAnim.duration = 250
        waveShiftAnim.interpolator = LinearInterpolator()
        animators.add(waveShiftAnim)

        // vertical animation.
        // water level increases from 0 to center of WaveView
        val waterLevelAnim = ObjectAnimator.ofFloat(
            mWaveView,
            "waterLevelRatio",
            0f,
            1f
        )
        waterLevelAnim.duration = 2500
        waterLevelAnim.interpolator = DecelerateInterpolator()
        animators.add(waterLevelAnim)

        // amplitude animation.
        // wave grows big then grows small, repeatedly
        val amplitudeAnim = ObjectAnimator.ofFloat(
            mWaveView,
            "amplitudeRatio",
            0.0001f,
            0.05f
        )
        amplitudeAnim.repeatCount = ValueAnimator.INFINITE
        amplitudeAnim.repeatMode = ValueAnimator.REVERSE
        amplitudeAnim.duration = 1500
        amplitudeAnim.interpolator = LinearInterpolator()
        animators.add(amplitudeAnim)
        mAnimatorSet = AnimatorSet()
        mAnimatorSet!!.playTogether(animators)
    }

    fun cancel() {
        if (mAnimatorSet != null) {
            mAnimatorSet!!.end()
        }
    }
}
