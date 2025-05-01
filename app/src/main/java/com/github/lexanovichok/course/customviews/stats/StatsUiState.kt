package com.github.lexanovichok.course.customviews.stats

import java.io.Serializable

interface StatsUiState : Serializable {

    fun update(statsTextView: UpdateStats)

    class Base(
        private val corrects: Int,
        private val incorrects: Int
    ) : StatsUiState {
        override fun update(statsTextView: UpdateStats) {
            statsTextView.update(corrects, incorrects)
        }

    }
}
