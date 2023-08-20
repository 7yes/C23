package com.example.ui.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.c23.databinding.DialogCustomBinding

class QuantityDialog(
    private val onSubmitClickLis: (Float) -> Unit
) : DialogFragment() {

    private lateinit var binding: DialogCustomBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogCustomBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)

        binding.btnSend.setOnClickListener {
            onSubmitClickLis.invoke(binding.etAmount.text.toString().toFloat())
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.CYAN))
        dialog.window!!.setGravity(Gravity.HORIZONTAL_GRAVITY_MASK)
        return dialog
    }

}