package com.github.lexanovichok.course.customviews.stats

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.github.lexanovichok.course.R

class StatsTextView : AppCompatTextView, UpdateStats {

    private lateinit var uiState: StatsUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val state = StatsSavedState(it)
            state.save(uiState)
            return state
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as StatsSavedState
        super.onRestoreInstanceState(state)
        update(restoredState.restore())
    }

    override fun update(uiState: StatsUiState) {
        this.uiState = uiState
        update(uiState)
    }

    override fun update(corrects: Int, incorrects: Int) {
        setText(resources.getString(R.string.stats, corrects, incorrects))
    }
}

interface UpdateStats {
    fun update(corrects: Int, incorrects: Int)
    fun update(uiState: StatsUiState)
}