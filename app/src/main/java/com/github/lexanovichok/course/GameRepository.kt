package com.github.lexanovichok.course

interface GameRepository {

    fun questionAndChoices(): QuestionAndChoices
    fun saveUserChoice(index: Int)
    fun getUserChoice() : Int
    fun check(): CorrectAndUserChoiceIndexes
    fun next()
}

