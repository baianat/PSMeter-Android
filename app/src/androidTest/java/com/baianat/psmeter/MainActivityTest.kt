package com.baianat.psmeter

import android.view.View
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.baianat.psmeter.pass_meter_view.PsMeterView
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import junit.framework.TestCase
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit


@RunWith(AndroidJUnit4::class)
class MainActivityTest : TestCase() {
    private lateinit var mainActivity:ActivityScenario<MainActivity>

    @Mock
    private lateinit var mPsMeterView: PsMeterView


    @Before
    fun init() {
        mainActivity = ActivityScenario.launch(MainActivity::class.java)

    }

    @Test
    fun textChanged_attachPsMeterStrengthEstimator_onAttachInvoked() {
        writeTo(R.id.passwordEditText, "cczczxcxz")
        assertDisplayed(R.id.passwordEditText, "cczczxcxz")
        onView(withId(R.id.passwordEditText)).perform(ViewActions.)
    }

    fun testInitViews() {}

    fun testSetUpPsMeterEstimator() {}

    fun testSetUpPsMeterDecorator() {}

    fun testSubscribeTextChanged() {}

    fun testSubscribePsMeterScoreChanged() {}

}