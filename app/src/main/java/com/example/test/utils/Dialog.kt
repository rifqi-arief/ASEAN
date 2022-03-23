package com.example.test.utils

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.example.test.R
import com.example.test.app.listener.DialogInfoListener
import com.example.test.databinding.DialogInfoBinding
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

    fun information(context: Context, title: String, content: String, imageResource : Int?, buttonText : String, listener : DialogInfoListener){
        var dialogInfo = Dialog(context, R.style.CustomDialog)
        val binding = DialogInfoBinding.inflate(LayoutInflater.from(context))

        dialogInfo.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogInfo.setContentView(binding.root)
        dialogInfo.setCancelable(false)

        if (imageResource != null){
            binding.ivDialogInfo.visibility = View.VISIBLE
            binding.ivDialogInfo.setImageResource(imageResource)
        }else {
            binding.ivDialogInfo.visibility = View.GONE
        }
        binding.tvDialogInfoTitle.text = title
        binding.tvDialogInfoContent.text = content
        binding.btnDialogInfo.text = buttonText

        val window = dialogInfo.window
        window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)

        dialogInfo.show()

        binding.btnDialogInfo.setOnClickListener {
            dialogInfo.dismiss()
            listener.onButtonClickListener()
        }
    }
}