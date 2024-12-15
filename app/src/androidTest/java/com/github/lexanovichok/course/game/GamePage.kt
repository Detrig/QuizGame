package com.github.lexanovichok.course.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import com.github.lexanovichok.course.R
import org.hamcrest.Matcher

class GamePage(question: String, choices: List<String>) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val containerClassTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    //From blueprint (под каждый элемент ui создаем поле):
    private val questionUi = QuestionUi(
        text = question,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val choicesUiIdList = listOf(
        R.id.firstChoiceButton,
        R.id.secondChoiceButton,
        R.id.thirdChoiceButton,
        R.id.fourthChoiceButton
    )

    private val choicesUiList = choices.mapIndexed { index, text ->
        ChoiceUi(
            choicesUiIdList[index],
            text = text,
            containerIdMatcher = containerIdMatcher,
            containerClassTypeMatcher = containerClassTypeMatcher
        )
    }

    private val checkUi = ButtonUi(
        id = R.id.checkButton,
        textResId = R.string.check,
        colorHex = "#E162EC",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )

    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        textResId = R.string.next,
        colorHex = "#1B8B85",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = containerClassTypeMatcher
    )


    fun assertAskedQuestionState() {
        questionUi.assertTextVisible()
        choicesUiList.forEach {
            it.assertAvailableToChooseState()
        }
        checkUi.assertNotVisible()
        nextUi.assertNotVisible()
    }

    fun clickFirstChoice() {
        choicesUiList.first().click()
    }

    fun assertFirstChoiceMadeState() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertNotAvailableToChooseState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertAvailableToChooseState()
        }
        checkUi.assertVisible()
        nextUi.assertNotVisible()
    }

    fun clickCheckButton() {
        checkUi.click()
    }

    fun assertAnswerCheckedStateFirstIsCorrect() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertCorrectState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertNotAvailableToChooseState()
        }
        checkUi.assertNotVisible()
        nextUi.assertVisible()
    }

    fun clickSecondChoice() {
        choicesUiList[1].click()
    }

    fun assertSecondChoiceMadeState() {
        questionUi.assertTextVisible()
        choicesUiList.forEachIndexed { index, choiceUi ->
            if (index == 1)
                choiceUi.assertNotAvailableToChooseState()
            else
                choiceUi.assertAvailableToChooseState()

        }
        checkUi.assertVisible()
        nextUi.assertNotVisible()
    }

    fun assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect() {
        questionUi.assertTextVisible()
        choicesUiList.forEachIndexed { index, choiceUi ->
            if (index == 0) {
                choiceUi.assertCorrectState()
            } else if (index == 1) {
                choiceUi.assertIncorrectState()
            } else {
                choiceUi.assertNotAvailableToChooseState()
            }
        }
        checkUi.assertNotVisible()
        nextUi.assertVisible()
    }

    fun clickNext() {
        nextUi.click()
    }

}