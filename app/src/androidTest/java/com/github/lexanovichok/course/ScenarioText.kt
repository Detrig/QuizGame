package com.github.lexanovichok.course

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class ScenarioText {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage : GamePage

    @Before
    fun setup() {
        var gamePage = GamePage(question = "What color is the sky?", choices = listOf("blue", "red", "yellow", "brown"))
    }
    /**
     * Тесткейс номер 1 (happy pass)
     */

    @Test
    fun caseNumber1() {
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickCheckButton()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
    }

    /**
     * Тесткейс номер 2
     */

    @Test
    fun caseNumber2() {
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()

        gamePage.clickCheckButton()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNext()

        var gamePage = GamePage(question = "What color is the grass?", choices = listOf("green", "red", "yellow", "brown"))
        gamePage.assertAskedQuestionState()
    }
}