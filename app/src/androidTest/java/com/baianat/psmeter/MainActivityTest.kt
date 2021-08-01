package com.baianat.psmeter

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.baianat.psmeter.databinding.ActivityMainBinding
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito


class MainActivityTest : TestCase() {

    @Mock
    lateinit var ui: ActivityMainBinding

    @Before
    fun init() {
        ActivityScenario.launch(MainActivity::class.java)
        ui = Mockito.mock(ActivityMainBinding::class.java)
    }

    @Test
    fun textChanged_attachPsMeterStrengthEstimator_onAttachInvoked() {
        onView(withId(ui.passwordEditText.id)).perform(ViewActions.typeText("cczczxcxz"))
        check(withText(ui.passwordEditText.text.toString()).matches("cczczxcxz"))
//        Mockito.verify(ui.psMeterView)
//        .attachPsMeterStrengthEstimator(ui.passwordEditText.text.toString())
    }

    fun testInitViews() {}

    fun testSetUpPsMeterEstimator() {}

    fun testSetUpPsMeterDecorator() {}

    fun testSubscribeTextChanged() {}

    fun testSubscribePsMeterScoreChanged() {}
}