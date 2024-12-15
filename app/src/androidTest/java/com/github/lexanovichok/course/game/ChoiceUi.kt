package com.github.lexanovichok.course.game

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotClickable
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.lexanovichok.course.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ChoiceUi(
    id: Int,
    text: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButton(
    onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(id),
            withText(text),
            isAssignableFrom(AppCompatButton::class.java),
            isDisplayed()
        )
    )
) {
    fun assertNotAvailableToChooseState() {
        interaction
            .check(matches(isNotEnabled()))
    }

    fun assertAvailableToChooseState() {
        interaction
            .check(matches(isEnabled()))
            .check(matches(ButtonColorMatcher(R.color.blue)))
            .check(matches(isClickable()))
    }

    fun assertCorrectState() {
        interaction
            .check(matches(isEnabled()))
            .check(matches(ButtonColorMatcher(R.color.green)))
            .check(matches(isNotClickable()))
    }

    fun assertIncorrectState() {
        interaction
            .check(matches(isEnabled()))
            .check(matches(ButtonColorMatcher(R.color.red)))
            .check(matches(isNotClickable()))
    }

}
