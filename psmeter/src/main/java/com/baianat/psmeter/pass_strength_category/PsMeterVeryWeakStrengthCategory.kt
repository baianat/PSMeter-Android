package com.baianat.psmeter.pass_strength_category

import com.baianat.psmeter.pass_strength_decorator.PsMeterStateDecorator
import com.baianat.psmeter.R

class PsMeterVeryWeakStrengthCategory(override var psMeterStateDecorator:PsMeterStateDecorator) : PsMeterStrengthCategory() {

    override fun strengthRatio(): Int = 20

    override fun strengthMessage(): Int {
        return R.string.very_Weak
    }

    override fun strengthPhase(): PsMeterStrengthCategoryEnum {
        return PsMeterStrengthCategoryEnum.VERY_WEAK
    }

}