package com.github.lexanovichok.course.game

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher

class ButtonUi(
    id: Int,
    textResId: Int,
    colorHex: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButton(
    onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(id),
            withText(textResId),
            ButtonColorMatcher(colorHex)
        )
    )
) {
    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

}

abstract class AbstractButton(
    protected val interaction: ViewInteraction
) {
    fun click() {
        interaction.perform(androidx.test.espresso.action.ViewActions.click())
    }
}
