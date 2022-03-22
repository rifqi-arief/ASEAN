package com.example.test.utils

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.example.test.R
import com.example.test.databinding.LoadingBinding

object Dialog {

    fun loading(context: Context) : Dialog {
        var dialog = Dialog(context, R.style.CustomDialog)
        val binding = LoadingBinding.inflate(LayoutInflater.from(context))

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)

        val window = dialog.window
        window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        return dialog
    }

}