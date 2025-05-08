package com.github.lexanovichok.course.game

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.CoreMatchers.allOf
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.lexanovichok.course.R
import org.hamcrest.Matcher
import kotlin.jvm.java

class StatsUi(
    incorrects: Int,
    corrects: Int,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) {
    private val interaction: ViewInteraction = onView(
        allOf(
            withId(R.id.statsTextView),
            containerIdMatcher,
            containerClassTypeMatcher,
            isAssignableFrom(TextView::class.java),
            withText("Game Over\n\nCorrects: $corrects\nIncorrects: $incorrects")
        )
    )

    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertDoesNotExist() {
        interaction.check(doesNotExist())
    }
}