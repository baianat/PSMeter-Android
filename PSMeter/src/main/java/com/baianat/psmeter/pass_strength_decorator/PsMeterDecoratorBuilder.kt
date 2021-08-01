package com.baianat.psmeter.pass_strength_decorator


class PsMeterDecoratorBuilder {
    var emptyStateDecorator = PsMeterStateDecorator.defaultEmpty()
    var veryWeakStateDecorator = PsMeterStateDecorator.defaultVeryWeak()
    var weakStateDecorator = PsMeterStateDecorator.defaultWeak()
    var fairStateDecorator = PsMeterStateDecorator.defaultFair()
    var strongStateDecorator = PsMeterStateDecorator.defaultStrong()
    var veryStrongStateDecorator = PsMeterStateDecorator.defaultVeryStrong()

    fun setEmptyStateDecorator(emptyPsMeterStateDecorator: PsMeterStateDecorator): PsMeterDecoratorBuilder {
        this.emptyStateDecorator = emptyPsMeterStateDecorator
        return this
    }

    fun setVeryWeakStateDecorator(veryWeakPsMeterStateDecorator: PsMeterStateDecorator): PsMeterDecoratorBuilder {
        this.veryWeakStateDecorator = veryWeakPsMeterStateDecorator
        return this
    }

    fun setWeakStateDecorator(weakPsMeterStateDecorator: PsMeterStateDecorator): PsMeterDecoratorBuilder {
        this.weakStateDecorator = weakPsMeterStateDecorator
        return this
    }

    fun setFairStateDecorator(fairPsMeterStateDecorator: PsMeterStateDecorator): PsMeterDecoratorBuilder {
        this.fairStateDecorator = fairPsMeterStateDecorator
        return this
    }

    fun setStrongStateDecorator(strongPsMeterStateDecorator: PsMeterStateDecorator): PsMeterDecoratorBuilder {
        this.strongStateDecorator = strongPsMeterStateDecorator
        return this
    }

    fun setVeryStrongStateDecorator(veryStrongPsMeterStateDecorator: PsMeterStateDecorator): PsMeterDecoratorBuilder {
        this.veryStrongStateDecorator = veryStrongPsMeterStateDecorator
        return this
    }

    fun build(): PsMeterDecoratorBuilder {
        return this
    }

    companion object {
        fun builder(): PsMeterDecoratorBuilder {
            return PsMeterDecoratorBuilder()
        }
    }
}