package com.github.lexanovichok.course.customviews.choice

import com.github.lexanovichok.course.R
import java.io.Serializable

interface ChoiceUiState : Serializable {

//    fun update(button: AppCompatButton)

    fun update(choiceButton: UpdateChoiceButton)

    abstract class Abstract(
        private val color: Int,
        private val clickable: Boolean,
        private val enabled: Boolean,
        private val text: String
    ) : ChoiceUiState {
        override fun update(choiceButton: UpdateChoiceButton) {
            choiceButton.update(color, clickable, enabled, text)
        }
    }

    data class Initial(private val text: String) : Abstract(
        R.color.blue, true, true, text
    ) {
        override fun update(choiceButton: UpdateChoiceButton) {
            super.update(choiceButton)
            choiceButton.update(text)
        }
    }

    data class NotAvailableToChoose(private val text: String) :
        Abstract(R.color.gray, false, false, text)

    data class AvailableToChoose(private val text: String) : Abstract(
        R.color.blue, true, true, text
    )

    data class Correct(private val text: String) : Abstract(
        R.color.green, false, true, text
    )

    data class Incorrect(private val text: String) : Abstract(
        R.color.red, false, true, text
    )
}