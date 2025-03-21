package com.github.lexanovichok.course.customviews.choice

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class ChoiceButtonSavedState : View.BaseSavedState { //Реализует Parcelable

    private lateinit var state: ChoiceUiState

    constructor(superState: Parcelable?) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            state = parcelIn.readSerializable(
                ChoiceUiState::class.java.classLoader,
                ChoiceUiState::class.java
            ) as ChoiceUiState
        } else {
            parcelIn.readSerializable() as ChoiceUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) { //Вызывается при уничтожении View
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): ChoiceUiState = state

    fun save(uiState: ChoiceUiState) {
        state = uiState
    }

    override fun describeContents(): Int = 0

    companion object CREATOR :
        Parcelable.Creator<ChoiceButtonSavedState> { //Отвечает за создание объекта Parcel

        override fun createFromParcel(parcel: Parcel): ChoiceButtonSavedState =
            ChoiceButtonSavedState(parcel)


        override fun newArray(size: Int): Array<ChoiceButtonSavedState?> =
            arrayOfNulls(size)

    }
}