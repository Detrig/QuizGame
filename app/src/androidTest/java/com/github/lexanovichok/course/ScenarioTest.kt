package com.github.lexanovichok.course

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.lexanovichok.course.game.GamePage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage : GamePage

    @Before
    fun setup() {
        gamePage = GamePage(question = "What color is the sky?", choices = listOf("blue", "red", "yellow", "brown"))
    }
    /**
     * Тесткейс номер 1 (happy pass)
     */
    @Test
    fun caseNumber1() {
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickCheckButton()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
    }

    /**
     * Тесткейс номер 2
     */
    @Test
    fun caseNumber2() {
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertSecondChoiceMadeState()

        gamePage.clickCheckButton()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNext()

        var gamePage = GamePage(question = "What color is the grass?", choices = listOf("green", "red", "yellow", "brown"))
        gamePage.assertAskedQuestionState()
        activityScenarioRule.scenario.recreate()
        gamePage.assertAskedQuestionState()
    }
}