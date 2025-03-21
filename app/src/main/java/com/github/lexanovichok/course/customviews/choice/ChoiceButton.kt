package com.github.lexanovichok.course.customviews.choice

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import com.github.lexanovichok.course.customviews.question.UpdateText

class ChoiceButton : AppCompatButton, UpdateChoiceButton {

    private lateinit var state: ChoiceUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun update(state: ChoiceUiState) {
        this.state = state
        state.update(this)
    }

    override fun update(color: Int, clickable: Boolean, enabled: Boolean, text: String) {
        isEnabled = enabled
        if (enabled)
            setBackgroundResource(color)
        isClickable = clickable
        this.text = text
    }

    override fun update(text: String) {
        this.text = text
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = ChoiceButtonSavedState(it)
            savedState.save(state)
            Log.d("ChoiceButton", "state: $state")
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as ChoiceButtonSavedState
        super.onRestoreInstanceState(state)
        update(restoredState.restore())
    }
}

interface UpdateChoiceButton : UpdateText {

    fun update(state: ChoiceUiState)

    fun update(
        color: Int,
        clickable: Boolean,
        enabled: Boolean,
        text: String
    )
}