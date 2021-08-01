package com.baianat.psmeter.pass_meter_view

import org.junit.Assert
import org.junit.Test

class TestPassword {
    @Test
    fun testPasswordLength() {
        val passwordValidator =  PasswordValidator()
        Assert.assertEquals(true,passwordValidator.isValid("skjflsjf"))
    }
}