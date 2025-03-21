package com.github.lexanovichok.course.customviews.visiblebutton

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class VisibilitySavedState : View.BaseSavedState {

    private lateinit var state : VisibilityUiState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            state = parcelIn.readSerializable(VisibilityUiState::class.java.classLoader, VisibilityUiState::class.java) as VisibilityUiState
        } else {
            parcelIn.readSerializable() as VisibilityUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore() : VisibilityUiState = state

    fun save(uiState: VisibilityUiState) {
        state = uiState
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<VisibilitySavedState> {

        override fun createFromParcel(parcel: Parcel): VisibilitySavedState =
            VisibilitySavedState(parcel)

        override fun newArray(size: Int): Array<VisibilitySavedState?> =
            arrayOfNulls(size)
    }
}

