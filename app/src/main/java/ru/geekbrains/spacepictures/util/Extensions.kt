package ru.geekbrains.spacepictures.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

// SnackBar с текстом из ресурсов
fun View.showSnackBarWithResText(
    textFromResources: Int,
    length: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(this, resources.getString(textFromResources), length).show()
}