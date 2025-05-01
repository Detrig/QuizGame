package com.github.lexanovichok.course.customviews.stats

import android.os.Build
 import android.os.Parcel
 import android.os.Parcelable
 import android.view.View

 class StatsSavedState : View.BaseSavedState { //Реализует Parcelable

     private lateinit var state: StatsUiState

     constructor(superState: Parcelable?) : super(superState)

     private constructor(parcelIn: Parcel) : super(parcelIn) {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             state = parcelIn.readSerializable(
                 StatsUiState::class.java.classLoader,
                 StatsUiState::class.java
             ) as StatsUiState
         } else {
             parcelIn.readSerializable() as StatsUiState
         }
     }

     override fun writeToParcel(out: Parcel, flags: Int) { //Вызывается при уничтожении View
         super.writeToParcel(out, flags)
         out.writeSerializable(state)
     }

     fun restore(): StatsUiState = state

     fun save(uiState: StatsUiState) {
         state = uiState
     }

     override fun describeContents(): Int = 0

     companion object CREATOR :
         Parcelable.Creator<StatsSavedState> { //Отвечает за создание объекта Parcel

         override fun createFromParcel(parcel: Parcel): StatsSavedState =
             StatsSavedState(parcel)


         override fun newArray(size: Int): Array<StatsSavedState?> =
             arrayOfNulls(size)

     }
 }