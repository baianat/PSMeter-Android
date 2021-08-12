package com.baianat.psmeter.pass_meter_utils

import android.os.Build
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

internal object PsMeterUtils {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun ProgressBar.extProgressTint(color: Int) {
        this.progressTintList = ContextCompat.getColorStateList(context, color)

    }

    fun TextView.extTextColor(color: Int) {
        this.setTextColor(ContextCompat.getColor(context, color))
    }

    fun TextView.extSetText(strRes: Int) {
        this.text = context.getString(strRes)
    }

}