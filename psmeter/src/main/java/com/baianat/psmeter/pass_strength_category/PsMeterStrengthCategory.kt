package com.baianat.psmeter.pass_strength_category

import com.baianat.psmeter.pass_strength_decorator.PsMeterStateDecorator

abstract class PsMeterStrengthCategory {
    open var psMeterStateDecorator: PsMeterStateDecorator = PsMeterStateDecorator.defaultEmpty()
    abstract fun strengthRatio(): Int
    abstract fun strengthMessage(): Int
    abstract fun strengthPhase(): PsMeterStrengthCategoryEnum
}