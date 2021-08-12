package com.baianat.psmeter.pass_strength_category

import com.baianat.psmeter.pass_strength_decorator.PsMeterStateDecorator
import com.baianat.psmeter.R

class PsMeterWeakStrengthCategory(override var psMeterStateDecorator:PsMeterStateDecorator) : PsMeterStrengthCategory() {

    override fun strengthRatio(): Int = 40
    override fun strengthMessage(): Int {
        return R.string.weak
    }

    override fun strengthPhase(): PsMeterStrengthCategoryEnum {
        return PsMeterStrengthCategoryEnum.Weak
    }
}