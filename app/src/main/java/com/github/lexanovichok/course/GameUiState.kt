package com.github.lexanovichok.course

import com.github.lexanovichok.course.databinding.ActivityMainBinding

interface GameUiState {

    fun update(binding : ActivityMainBinding) : Unit = throw IllegalStateException("") //todo remove it

    data class AskedQuestion(
        val question: String,
        val choices: List<String>) :
        GameUiState {

    }

    data class ChoiceMade(
        val question: String,
        val choices: List<ChoiceUiState>) : GameUiState {

    }

    data class AnswerChecked(
        val question: String,
        val choices: List<ChoiceUiState>) : GameUiState {

    }
}

interface ChoiceUiState {

    data class NotAvailableToChoose(private val text: String) : ChoiceUiState

    data class AvailableToChoose(private val text: String) : ChoiceUiState

    data class Correct(private val text: String) : ChoiceUiState

    data class Incorrect(private val text: String) : ChoiceUiState


}