package com.github.lexanovichok.course

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class QuizApp : Application() {

    private var gameViewModel: GameViewModel? = null

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = this.getSharedPreferences("quizAppData", Context.MODE_PRIVATE)

    }

    fun makeGameViewModel(): GameViewModel {
        if (gameViewModel == null) {
            gameViewModel = GameViewModel(
                GameRepository.Base(
                    IntCache.Base(sharedPreferences, "indexKey", 0),
                    IntCache.Base(sharedPreferences, "userChoiceIndexKey", -1)
                )
            )
            //занулить остальные viewModel
        }
        return gameViewModel!!
    }
}