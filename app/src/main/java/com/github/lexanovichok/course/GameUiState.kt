package com.github.lexanovichok.course

import com.github.lexanovichok.course.customviews.question.UpdateText
import com.github.lexanovichok.course.customviews.choice.ChoiceUiState
import com.github.lexanovichok.course.customviews.choice.UpdateChoiceButton
import com.github.lexanovichok.course.customviews.visiblebutton.UpdateVisibility
import com.github.lexanovichok.course.customviews.visiblebutton.VisibilityUiState
import java.io.Serializable

interface GameUiState : Serializable {

    fun update(
        questionTextView: UpdateText,
        firstChoiceButton: UpdateChoiceButton,
        secondChoiceButton: UpdateChoiceButton,
        thirdChoiceButton: UpdateChoiceButton,
        fourthChoiceButton: UpdateChoiceButton,
        nextButton: UpdateVisibility,
        checkButton: UpdateVisibility
    )

    object Empty : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) = Unit
    }

    data class AskedQuestion(
        private val question: String,
        private val choices: List<String>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            questionTextView.update(question)
            firstChoiceButton.update(ChoiceUiState.Initial(choices[0]))
            secondChoiceButton.update(ChoiceUiState.Initial(choices[1]))
            thirdChoiceButton.update(ChoiceUiState.Initial(choices[2]))
            fourthChoiceButton.update(ChoiceUiState.Initial(choices[3]))
            nextButton.update(VisibilityUiState.Invisible)
            checkButton.update(VisibilityUiState.Invisible)
        }

    }

    data class ChoiceMade(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            fourthChoiceButton.update(choices[3])
            checkButton.update(VisibilityUiState.Visible)
        }
    }

    data class AnswerChecked(
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            fourthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.update(choices[0])
            secondChoiceButton.update(choices[1])
            thirdChoiceButton.update(choices[2])
            fourthChoiceButton.update(choices[3])
            checkButton.update(VisibilityUiState.Invisible)
            nextButton.update(VisibilityUiState.Visible)
        }
    }
}


