package com.github.lexanovichok.course


import android.R.layout
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.lexanovichok.course.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel : GameViewModel = GameViewModel(GameRepository.Base())

        binding.firstChoiceButton.setOnClickListener {
            val uiState : GameUiState = viewModel.chooseFirst()
            uiState.update(binding = binding)
        }

        binding.secondChoiceButton.setOnClickListener {
            val uiState : GameUiState = viewModel.chooseSecond()
            uiState.update(binding = binding)
        }

        binding.thirdChoiceButton.setOnClickListener {
            val uiState : GameUiState = viewModel.chooseThird()
            uiState.update(binding = binding)
        }

        binding.fourthChoiceButton.setOnClickListener {
            val uiState : GameUiState = viewModel.chooseFourth()
            uiState.update(binding = binding)
        }

        binding.checkButton.setOnClickListener {
            val uiState : GameUiState = viewModel.check()
            uiState.update(binding = binding)
        }

        binding.nextButton.setOnClickListener {
            val uiState : GameUiState = viewModel.next()
            uiState.update(binding = binding)
        }

        val uiState : GameUiState = viewModel.init()
        uiState.update(binding = binding)
    }
}