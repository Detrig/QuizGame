package com.github.lexanovichok.course


import android.R.layout
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.lexanovichok.course.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var uiState: GameUiState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: GameViewModel = (application as QuizApp).viewModel

        val update: () -> Unit = {
            uiState.update(
                binding.questionTextView,
                binding.firstChoiceButton,
                binding.secondChoiceButton,
                binding.thirdChoiceButton,
                binding.fourthChoiceButton,
                binding.nextButton,
                binding.checkButton
            )
        }

        binding.firstChoiceButton.setOnClickListener {
            uiState = viewModel.chooseFirst()
            update.invoke()
        }

        binding.secondChoiceButton.setOnClickListener {
            uiState = viewModel.chooseSecond()
            update.invoke()
        }

        binding.thirdChoiceButton.setOnClickListener {
            uiState = viewModel.chooseThird()
            update.invoke()
        }

        binding.fourthChoiceButton.setOnClickListener {
            uiState = viewModel.chooseFourth()
            update.invoke()
        }

        binding.checkButton.setOnClickListener {
            uiState = viewModel.check()
            update.invoke()
        }

        binding.nextButton.setOnClickListener {
            uiState = viewModel.next()
            update.invoke()
        }

        uiState = viewModel.init(savedInstanceState == null)
        update.invoke()

    }
}