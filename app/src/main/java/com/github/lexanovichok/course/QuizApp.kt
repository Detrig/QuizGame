package com.github.lexanovichok.course

import android.app.Application
import android.content.Context

class QuizApp : Application() {

    lateinit var viewModel : GameViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = this.getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
        viewModel = GameViewModel(GameRepository.Base(
            IntCache.Base(sharedPreferences, "indexKey", 0),
            IntCache.Base(sharedPreferences, "userChoiceIndexKey", -1)
        ))
    }
}