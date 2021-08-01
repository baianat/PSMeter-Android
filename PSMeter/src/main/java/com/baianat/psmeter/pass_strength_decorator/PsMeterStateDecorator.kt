package com.baianat.psmeter.pass_strength_decorator

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.baianat.psmeter.R

data class PsMeterStateDecorator(
    @StringRes val psLabel: Int,
    @ColorRes val psTextColor: Int,
    @ColorRes val psProgressColor: Int
) {
    companion object {
        fun defaultEmpty() = PsMeterStateDecorator(
            psLabel = R.string.strength,
            psTextColor = android.R.color.black,
            psProgressColor = android.R.color.black
        )

        fun defaultVeryWeak() = PsMeterStateDecorator(
            psLabel = R.string.very_Weak,
            psTextColor = R.color.psColorVeryWeak,
            psProgressColor = R.color.psColorVeryWeak
        )

        fun defaultWeak() = PsMeterStateDecorator(
            psLabel = R.string.weak,
            psTextColor = R.color.psColorWeak,
            psProgressColor = R.color.psColorWeak
        )

        fun defaultFair() = PsMeterStateDecorator(
            psLabel = R.string.fair,
            psTextColor = R.color.psColorFair,
            psProgressColor = R.color.psColorFair
        )

        fun defaultStrong() = PsMeterStateDecorator(
            psLabel = R.string.strong,
            psTextColor = R.color.psColorStrong,
            psProgressColor = R.color.psColorStrong
        )

        fun defaultVeryStrong() = PsMeterStateDecorator(
            psLabel = R.string.very_strong,
            psTextColor = R.color.psColorVeryStrong,
            psProgressColor = R.color.psColorVeryStrong
        )
    }
}
