package com.github.lexanovichok.course

interface GameRepository {

    fun questionAndChoices(): QuestionAndChoices
    fun saveUserChoice(index: Int)
    fun getUserChoice() : Int
    fun check(): CorrectAndUserChoiceIndexes
    fun next()

    class Base(
        private val list : List<QuestionAndChoices> = listOf(
            QuestionAndChoices(question = "What color is the sky?", choices = listOf("blue", "red", "yellow", "brown"), correctIndex = 0),
            QuestionAndChoices(question = "What color is the grass?", choices = listOf("green", "red", "yellow", "brown"), correctIndex = 0),
        )
    ) : GameRepository {


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
            userChoiceIndex = -1
            if (index + 1 == list.size)
                index = 0
            else
                index++
        }

    }
}

