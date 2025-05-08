package com.github.lexanovichok.course.game

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.lexanovichok.course.GameUiState
import com.github.lexanovichok.course.GameViewModel
import com.github.lexanovichok.course.NavigateToGameOver
import com.github.lexanovichok.course.QuizApp
import com.github.lexanovichok.course.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: GameViewModel =
            (requireActivity().application as QuizApp).makeGameViewModel()

        lateinit var uiState: GameUiState
        val update: () -> Unit = {
            //todo ().navigateToGameOver()
            uiState.update(
                binding.questionTextView,
                binding.firstChoiceButton,
                binding.secondChoiceButton,
                binding.thirdChoiceButton,
                binding.fourthChoiceButton,
                binding.nextButton,
                binding.checkButton
            )
            uiState.navigate(requireActivity() as NavigateToGameOver)
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

