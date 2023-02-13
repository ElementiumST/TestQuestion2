package io.stark.testquestion.feature.first.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import io.stark.testquestion.R
import io.stark.testquestion.databinding.FragmentFirstCustomAlertBinding


class FirstCustomAlertFragment : DialogFragment() {

    private lateinit var bindings: FragmentFirstCustomAlertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            android.R.style.Theme_Black_NoTitleBar_Fullscreen
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.setBackgroundDrawable(
                ColorDrawable(
                    resources.getColor(
                        R.color.gray_with_alpha,
                        context.theme
                    )
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindings = FragmentFirstCustomAlertBinding.inflate(inflater, container, false)
        bindings.pointPart.secondPointView.select()
        setupListener()
        return bindings.root
    }

    private fun setupListener() {
        bindings.root.setOnClickListener {
            dismiss()
        }
        bindings.closeButton.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance() = FirstCustomAlertFragment()
        const val TAG = "FirstCustomAlertFragment"
    }

}