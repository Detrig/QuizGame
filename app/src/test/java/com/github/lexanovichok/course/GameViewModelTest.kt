package com.github.lexanovichok.course

import com.github.lexanovichok.course.customviews.choice.ChoiceUiState
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class GameViewModelTest {

    private lateinit var viewModel : GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }
    /**
     * Тесткейс номер 1 (happy pass)
     */

    @Test
    fun caseNumber1() {
        var actual : GameUiState = viewModel.init()
        var expected : GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4")
                )
        )
        assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            choices = listOf(
                ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.NotAvailableToChoose(text = "c4")
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun caseNumber2() {
        var actual : GameUiState = viewModel.init()
        var expected : GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4")
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseSecond()
        expected = GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4")
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseThird()
        expected = GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4")
            )
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseFourth()
        expected = GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.NotAvailableToChoose(text = "c4")
            )
        )
        assertEquals(expected, actual)


        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            choices = listOf(
                ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.Incorrect(text = "c4")
            )
        )
        assertEquals(expected, actual)


        actual = viewModel.next()
        expected = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4")
        )
        assertEquals(expected, actual)
    }
}

private class FakeRepository : GameRepository {

    private val list : List<QuestionAndChoices> = listOf(
        QuestionAndChoices(question = "q1", choices = listOf("c1", "c2", "c3", "c4"), correctIndex = 0),
        QuestionAndChoices(question = "q2", choices = listOf("cd1", "cd2", "cd3", "cd4"), correctIndex = 0),
    )

    private var index = 0

    override fun questionAndChoices() : QuestionAndChoices {
        return list[index]
    }

    private var userChoiceIndex = -1

    override fun saveUserChoice(index : Int) {
        userChoiceIndex = index
    }

    override fun getUserChoice(): Int = userChoiceIndex

    override fun check() : CorrectAndUserChoiceIndexes {
        return CorrectAndUserChoiceIndexes(
            correctIndex =  questionAndChoices().correctIndex,
            userChoiceIndex = this.userChoiceIndex
        )
    }

    override fun next() {
        index++
        userChoiceIndex = -1
        if (index == list.size)
            index = 0
    }
}