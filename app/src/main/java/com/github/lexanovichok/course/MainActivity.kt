package com.github.lexanovichok.course


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.lexanovichok.course.game.GameScreen
import com.github.lexanovichok.course.stats.GameOverScreen


class MainActivity : AppCompatActivity(), Navigate {

    private lateinit var uiState: GameUiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null)
            navigateToGame()

        navigate(GameScreen)
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container, supportFragmentManager)
    }

}

interface Navigate : NavigateToGame, NavigateToGameOver {
    fun navigate(screen: Screen)

    override fun navigateToGame() {
        navigate(GameScreen)
    }

    override fun navigateToGameOver() {
        navigate(GameOverScreen())
    }
}

interface NavigateToGame {
    fun navigateToGame()
}

interface NavigateToGameOver {
    fun navigateToGameOver()
}

