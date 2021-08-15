package com.baianat.psmeter.pass_meter_view

class PasswordValidator {
    fun isValid(s: String): Boolean {
        return s.length in 5..20
    }

}
