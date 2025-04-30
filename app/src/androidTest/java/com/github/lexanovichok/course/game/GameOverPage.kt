package com.github.lexanovichok.course.game

import android.R
import android.view.View
import android.widget.FrameLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher

class GameOverPage(incorrects: Int, corrects: Int) {
    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.gameOverContainer))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(FrameLayout::class.java))

    private val statsUi = StatsUi(incorrects = incorrects, corrects = corrects, containerIdMatcher, containerClassTypeMatcher)

    private val newGameUi = ButtonUi(
        R.id.newGameButton,
        R.string.new_game,
        "#462CE1",
        containerIdMatcher,
        containerClassTypeMatcher
    )

    fun assertInitialState() {
        statsUi.assertVisible()
    }

    fun clickNewGame() {
        newGameUi.click()
    }

    fun assertNotVisible() {
        statsUi.assertDoesNotExist()
    }
}