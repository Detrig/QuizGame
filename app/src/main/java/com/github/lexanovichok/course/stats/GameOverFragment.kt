package com.github.lexanovichok.course.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.lexanovichok.course.NavigateToGame
import com.github.lexanovichok.course.QuizApp
import com.github.lexanovichok.course.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameOverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val viewModel : GameOverViewModel = (requireActivity().application as QuizApp).gameOverViewModel
//
//        binding.statsTextView.update(viewModel.statsUiState)

        binding.newGameButton.setOnClickListener {
            (requireActivity() as NavigateToGame).navigateToGame()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}