package com.baianat.psmeter.pass_meter_view

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.baianat.psmeter.R
import com.baianat.psmeter.pass_estimator.PsMeterEstimator
import com.baianat.psmeter.pass_estimator.PsMeterInitialEstimator
import com.baianat.psmeter.pass_meter_utils.PsMeterUtils.extProgressTint
import com.baianat.psmeter.pass_meter_utils.PsMeterUtils.extSetText
import com.baianat.psmeter.pass_meter_utils.PsMeterUtils.extTextColor
import com.baianat.psmeter.pass_strength_call_back.PsMeterStrengthListener
import com.baianat.psmeter.pass_strength_category.PsMeterStrengthCategory
import com.baianat.psmeter.pass_strength_category.PsMeterStrengthCategoryEnum
import com.baianat.psmeter.pass_strength_decorator.PsMeterDecoratorBuilder
import com.baianat.psmeter.pass_strength_decorator.PsMeterStateDecorator
import com.baianat.psmeter.pass_strength_handler.PsMeterStrengthHandler.getProperPassStrength
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit


class PsMeterView constructor(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {
    private var mPsMeterLabelTextView: TextView
    private var mPsMeterProgressBar: ProgressBar
    private var mPsMeterEstimator: PsMeterEstimator = PsMeterInitialEstimator()
    private var mPsMeterDecoratorBuilder = PsMeterDecoratorBuilder()
    private val mPsMeterStrengthSubject = PublishSubject.create<String>()
    private val mPsMeterCompositeDisposable = CompositeDisposable()
    private var mPsMeterStrengthListener: PsMeterStrengthListener? = null
    var passStrengthEnum = PsMeterStrengthCategoryEnum.EMPTY
    var passStrengthMessage = ""

    init {
        val mView: View = inflate(context, R.layout.layout_password_strength, this)
        mPsMeterLabelTextView = mView.findViewById(R.id.psTextView)
        mPsMeterProgressBar = mView.findViewById(R.id.psProgressBar)
        subscribePasswordTextChanged()
        initAttrs(attrs)

    }

    private fun initAttrs(attrs: AttributeSet) {
        val defaultProgressBgColor = ContextCompat.getColor(context, android.R.color.white)
        val defaultLabelTextColor = ContextCompat.getColor(context, android.R.color.black)
        val styleableArray =
            context.obtainStyledAttributes(attrs, R.styleable.PsMeterView, 0, 0)
        val psLabel = styleableArray.getString(R.styleable.PsMeterView_psLabel)
        val psTextColor =
            styleableArray.getColor(R.styleable.PsMeterView_psTextColor, defaultLabelTextColor)
        val psBgProgressColor =
            styleableArray.getColor(
                R.styleable.PsMeterView_psBackgroundProgressColor,
                defaultProgressBgColor
            )
        val psFont =
            styleableArray.getResourceId(R.styleable.PsMeterView_psFont, R.font.font_medium)
        styleableArray.recycle()
        initPsMeterViewConfiguration(psLabel, psTextColor, psBgProgressColor, psFont)

    }

    private fun initPsMeterViewConfiguration(
        psLabel: String?,
        psTextColor: Int,
        psBgProgressColor: Int,
        psFont: Int
    ) {
        mPsMeterLabelTextView.typeface = ResourcesCompat.getFont(context, psFont)
        mPsMeterLabelTextView.text =
            psLabel ?: context.getString(mPsMeterDecoratorBuilder.emptyStateDecorator.psLabel)
        mPsMeterLabelTextView.setTextColor(psTextColor)
        mPsMeterProgressBar.progressBackgroundTintList = ColorStateList.valueOf(psBgProgressColor)
    }

    fun attachPsMeterStrengthEstimator(password: String) {
        mPsMeterStrengthSubject.onNext(password)
    }

    private fun updatePsMeterProgress(password: String) {
        val passwordStrengthCategory =
        getProperPassStrength(password, mPsMeterDecoratorBuilder, mPsMeterEstimator)
        passStrengthMessage = context.getString(passwordStrengthCategory.strengthMessage())
        passStrengthEnum = passwordStrengthCategory.strengthPhase()
        mPsMeterStrengthListener?.onPsMeterScoreChanged()
        setUpPsMeterDecorator(passwordStrengthCategory.psMeterStateDecorator)
        setUpPsMeterProgress(passwordStrengthCategory)
    }

    private fun setUpPsMeterDecorator(psMeterStateDecorator: PsMeterStateDecorator) {
        mPsMeterProgressBar.extProgressTint(psMeterStateDecorator.psProgressColor)
        mPsMeterLabelTextView.extTextColor(psMeterStateDecorator.psTextColor)
        mPsMeterLabelTextView.extSetText(psMeterStateDecorator.psLabel)
    }

    private fun setUpPsMeterProgress(psStrengthCategory: PsMeterStrengthCategory) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mPsMeterProgressBar.setProgress(psStrengthCategory.strengthRatio(), true)
        } else {
            mPsMeterProgressBar.progress = psStrengthCategory.strengthRatio()
        }
        mPsMeterLabelTextView.extSetText(psStrengthCategory.strengthMessage())
    }

    private fun subscribePasswordTextChanged() {
        mPsMeterCompositeDisposable.addAll(mPsMeterStrengthSubject
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { searchText ->
                updatePsMeterProgress(searchText)
            }
        )
    }


    fun configurePsMeterDecoratorBuilder(psMeterDecoratorBuilder: PsMeterDecoratorBuilder) {
        mPsMeterDecoratorBuilder = psMeterDecoratorBuilder
    }

    fun configurePsMeterEstimator(psEstimator: PsMeterEstimator) {
        mPsMeterEstimator = psEstimator
    }

    fun onPsMeterScoreChanged(score: (psMeterStrengthCategoryEnum: PsMeterStrengthCategoryEnum, score: Int, strengthMessage: String) -> Unit) {
        mPsMeterStrengthListener = object : PsMeterStrengthListener {
            override fun onPsMeterScoreChanged() {
                score(passStrengthEnum, passStrengthEnum.score, passStrengthMessage)
            }
        }
    }

}
