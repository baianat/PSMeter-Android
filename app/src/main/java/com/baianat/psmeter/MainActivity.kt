package com.baianat.psmeter

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.baianat.psmeter.databinding.ActivityMainBinding
import com.baianat.psmeter.pass_estimator.PsMeterEstimator
import com.baianat.psmeter.pass_meter_view.PsMeterView
import com.baianat.psmeter.pass_strength_category.PsMeterStrengthCategoryEnum
import com.baianat.psmeter.pass_strength_decorator.PsMeterDecoratorBuilder
import com.baianat.psmeter.pass_strength_decorator.PsMeterStateDecorator

class MainActivity : AppCompatActivity() {
    lateinit var ui : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setUpPsMeterDecorator()
        setUpPsMeterEstimator()
        subscribeTextChanged()
        subscribePsMeterScoreChanged()

    }

    private fun setUpPsMeterEstimator() {
        ui.psMeterView.configurePsMeterEstimator(CustomEstimation())
    }

    private fun setUpPsMeterDecorator() {
        val psMeterBuilder = PsMeterDecoratorBuilder()
            .setEmptyStateDecorator(
                PsMeterStateDecorator(
                    psLabel = R.string.empty,
                    psTextColor = android.R.color.black,
                    psProgressColor = android.R.color.black,
                )
            )
            .setVeryWeakStateDecorator(
                PsMeterStateDecorator(
                    psLabel = R.string.very_Weak,
                    psTextColor = R.color.psColorVeryWeak,
                    psProgressColor = R.color.psColorVeryWeak
                )
            ).setWeakStateDecorator(
                PsMeterStateDecorator(
                    psLabel = R.string.weak,
                    psTextColor = R.color.psColorWeak,
                    psProgressColor = R.color.psColorWeak
                )
            ).setFairStateDecorator(
                PsMeterStateDecorator(
                    psLabel = R.string.fair,
                    psTextColor = R.color.psColorFair,
                    psProgressColor = R.color.psColorFair
                )
            ).setStrongStateDecorator(
                PsMeterStateDecorator(
                    psLabel = R.string.strong,
                    psTextColor = R.color.psColorStrong,
                    psProgressColor = R.color.psColorStrong
                )
            ).setVeryStrongStateDecorator(
                PsMeterStateDecorator(
                    psLabel = R.string.very_strong,
                    psTextColor = R.color.psColorVeryStrong,
                    psProgressColor = R.color.psColorVeryStrong
                )
            ).build()
        ui.psMeterView.configurePsMeterDecoratorBuilder(psMeterBuilder)
    }

    private fun subscribeTextChanged() {
        ui.passwordEditText.addTextChangedListener {
            ui.psMeterView.attachPsMeterStrengthEstimator(it.toString())
        }
    }

    private fun subscribePsMeterScoreChanged() {
        ui.psMeterView.onPsMeterScoreChanged { passStrengthEnum, score, strengthMessage ->
            Toast.makeText(this, "passStrengthEnum: $passStrengthEnum", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "score: $score", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "passStrengthMessage: $strengthMessage", Toast.LENGTH_SHORT).show()
        }
    }

    inner class CustomEstimation : PsMeterEstimator {
        override fun estimatePassword(pass: String): PsMeterStrengthCategoryEnum {
            return when (pass.length) {
                0 -> {
                    Toast.makeText(this@MainActivity, "Elgoe: ${pass.length}", Toast.LENGTH_SHORT)
                        .show()
                    PsMeterStrengthCategoryEnum.EMPTY
                }
                1 -> {
                    Toast.makeText(this@MainActivity, "Elgoe: ${pass.length}", Toast.LENGTH_SHORT)
                        .show()
                    PsMeterStrengthCategoryEnum.VERY_WEAK

                }
                2 -> {
                    Toast.makeText(this@MainActivity, "Elgoe: ${pass.length}", Toast.LENGTH_SHORT)
                        .show()
                    PsMeterStrengthCategoryEnum.Weak

                }
                3 -> {
                    Toast.makeText(this@MainActivity, "Elgoe: ${pass.length}", Toast.LENGTH_SHORT)
                        .show()
                    PsMeterStrengthCategoryEnum.FAIR

                }
                4 -> {
                    Toast.makeText(this@MainActivity, "Elgoe: ${pass.length}", Toast.LENGTH_SHORT)
                        .show()
                    PsMeterStrengthCategoryEnum.STRONG

                }
                else -> {
                    Toast.makeText(this@MainActivity, "Elgoe: ${pass.length}", Toast.LENGTH_SHORT)
                        .show()
                    PsMeterStrengthCategoryEnum.VERY_STRONG
                }
            }

        }


    }
}