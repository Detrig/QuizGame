package com.github.lexanovichok.course

data class QuestionAndChoices(
    val question: String,
    val choices: List<String>,
    val correctIndex: Int
)
