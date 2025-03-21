package com.github.lexanovichok.course

import com.github.lexanovichok.course.customviews.choice.ChoiceUiState

class GameViewModel(private val repository: GameRepository) {

    fun chooseFirst(): GameUiState {
        repository.saveUserChoice(0)
        return updateChoiceState()
    }

    fun chooseSecond(): GameUiState {
        repository.saveUserChoice(1)
        return updateChoiceState()
    }

    fun chooseThird(): GameUiState {
        repository.saveUserChoice(2)
        return updateChoiceState()
    }

    fun chooseFourth(): GameUiState {
        repository.saveUserChoice(3)
        return updateChoiceState()
    }

    private fun updateChoiceState(): GameUiState {
        val data = repository.questionAndChoices()
        val userChoiceIndex = repository.getUserChoice()

        return GameUiState.ChoiceMade(
            data.choices.mapIndexed { index, choice ->
                if (index == userChoiceIndex)
                    ChoiceUiState.NotAvailableToChoose(text = choice)
                else
                    ChoiceUiState.AvailableToChoose(text = choice)
            }
        )
    }

    fun check(): GameUiState {
        val data = repository.questionAndChoices()
        val correctAndUserChoiceIndexes = repository.check()
        return GameUiState.AnswerChecked(
            data.choices.mapIndexed { selectedIndex, choice ->
                when {
                    correctAndUserChoiceIndexes.correctIndex == selectedIndex -> ChoiceUiState.Correct(text = choice)
                    correctAndUserChoiceIndexes.userChoiceIndex == selectedIndex -> ChoiceUiState.Incorrect(text = choice)
                    else -> ChoiceUiState.NotAvailableToChoose(text = choice)
                }
            }
        )
    }

    fun next(): GameUiState {
        repository.next()
        return init()
    }

    fun init(firstRun: Boolean = true): GameUiState {
        if (firstRun) {
            val data = repository.questionAndChoices()
            return GameUiState.AskedQuestion(
                data.question,
                data.choices
            )
        } else
            return GameUiState.Empty
    }
}