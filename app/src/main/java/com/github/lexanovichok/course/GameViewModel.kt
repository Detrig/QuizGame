package com.github.lexanovichok.course

class GameViewModel(private val repository : GameRepository) {

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

    private fun updateChoiceState() : GameUiState {
        val data = repository.questionAndChoices()
        val userChoiceIndex = repository.getUserChoice()

        return GameUiState.ChoiceMade(
            data.question,
            data.choices.mapIndexed { index, choice ->
                if (index == userChoiceIndex)
                    ChoiceUiState.NotAvailableToChoose(choice)
                else
                    ChoiceUiState.AvailableToChoose(choice)
            }
        )
    }

    fun check(): GameUiState {
        val data = repository.questionAndChoices()
        val correctAndUserChoiceIndexes = repository.check()
        return GameUiState.AnswerChecked(
            question = data.question,
            data.choices.mapIndexed { selectedIndex, choice ->
                when {
                    correctAndUserChoiceIndexes.correctIndex == selectedIndex -> ChoiceUiState.Correct(choice)

                    selectedIndex == correctAndUserChoiceIndexes.userChoiceIndex-> ChoiceUiState.Incorrect(choice)

                    else -> ChoiceUiState.NotAvailableToChoose(choice)

                }
            }
        )
    }

    fun next(): GameUiState {
        repository.next()
        return init()
    }

    fun init(): GameUiState {
        val data = repository.questionAndChoices()
        return GameUiState.AskedQuestion(
            data.question,
            data.choices
        )
    }
}