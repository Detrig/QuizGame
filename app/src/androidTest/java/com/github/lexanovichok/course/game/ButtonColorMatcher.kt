package com.github.lexanovichok.course.game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class ButtonColorMatcher(private val color: Int) :
    BoundedMatcher<View, AppCompatButton>(AppCompatButton::class.java) {

    constructor(colorString: String) : this(Color.parseColor(colorString))

    override fun describeTo(description: Description) {
        description.appendText("color for button doesn't match expected $color")
    }

    override fun matchesSafely(item: AppCompatButton): Boolean {
        val colorDrawable = item.background as? ColorDrawable ?: return false
        return colorDrawable.color == color
    }

}