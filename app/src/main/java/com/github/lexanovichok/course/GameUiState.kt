package com.github.lexanovichok.course

import android.view.View
import com.github.lexanovichok.course.databinding.ActivityMainBinding
import java.io.Serializable

interface GameUiState : Serializable {

    fun update(binding: ActivityMainBinding): Unit = throw IllegalStateException("")

    abstract class Abstract(
        private val questionText: String,
        private val choicesStateList: List<ChoiceUiState>,
        private val checkVisibility: Int,
        private val nextVisibility: Int
    ) : GameUiState {

        override fun update(binding: ActivityMainBinding) = with(binding) {
            questionTextView.text = questionText
            choicesStateList[0].update(firstChoiceButton)
            choicesStateList[1].update(secondChoiceButton)
            choicesStateList[2].update(thirdChoiceButton)
            choicesStateList[3].update(fourthChoiceButton)

            checkButton.visibility = checkVisibility
            nextButton.visibility = nextVisibility
        }
    }

    data class AskedQuestion(
        private val question: String,
        private val choices: List<String>
    ) : GameUiState.Abstract(
        question,
        choices.map { ChoiceUiState.AvailableToChoose(it) },
        checkVisibility = View.INVISIBLE,
        nextVisibility = View.INVISIBLE
    )

    data class ChoiceMade(
        private val question: String,
        private val choices: List<ChoiceUiState>
    ) : GameUiState.Abstract(question, choices, checkVisibility = View.VISIBLE, nextVisibility = View.INVISIBLE)

    data class AnswerChecked(
        private val question: String,
        private val choices: List<ChoiceUiState>
    ) : GameUiState.Abstract(
        question,
        choices,
        checkVisibility = View.INVISIBLE,
        nextVisibility = View.VISIBLE
    )
}


