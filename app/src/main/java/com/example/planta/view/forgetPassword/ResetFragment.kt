package com.example.planta.view.forgetPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.planta.R
import com.example.planta.view.login.loginViewModel


class ResetFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vm: ForgetViewModel by viewModels()

        var v = inflater.inflate(R.layout.fragment_reset, container, false)
        val email = v.findViewById<EditText>(R.id.editTextRestEmail)
        val sendButton = v.findViewById<Button>(R.id.buttonSend)
        val instruction = InstructionFragment()
        val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()


        sendButton.setOnClickListener {
            if(email.text.isEmpty()){
                Toast.makeText(context, "Please enter email", Toast.LENGTH_SHORT).show()
            }else{
                vm.resetPassword(email.text.toString())
                    .observe(this, {
                        if (it) {
                            supportFragmentManager
                                ?.replace(R.id.mFrameLayout, instruction)?.commit()
                        } else {
                            Toast.makeText(context, "Failed to sent", Toast.LENGTH_SHORT).show()
                        }
                    })
            }

        }


        return v
    }

    companion object {

    }
}