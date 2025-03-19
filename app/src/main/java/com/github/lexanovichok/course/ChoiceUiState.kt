package com.github.lexanovichok.course

import androidx.appcompat.widget.AppCompatButton

interface ChoiceUiState {

    fun update(button: AppCompatButton)

    abstract class Abstract(
        private val value: String,
        private val color: Int,
        private val clickable: Boolean,
        private val enabled: Boolean
    ) : ChoiceUiState {

        override fun update(button: AppCompatButton) = with(button) {
            text = value
            isEnabled = enabled
            if (enabled)
                setBackgroundResource(color)
            isClickable = clickable
        }
    }

    data class NotAvailableToChoose(private val text: String) :
        ChoiceUiState.Abstract(text, 0, false, false)

    data class AvailableToChoose(private val text: String) : ChoiceUiState.Abstract(
        text,
        R.color.blue, true, true
    )

    data class Correct(private val text: String) : ChoiceUiState.Abstract(
        text,
        R.color.green, false, true
    )

    data class Incorrect(private val text: String) : ChoiceUiState.Abstract(
        text,
        R.color.red, false, true
    )


}