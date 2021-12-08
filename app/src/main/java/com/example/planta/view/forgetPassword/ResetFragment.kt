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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResetFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vm: loginViewModel by viewModels()

        var v = inflater.inflate(R.layout.fragment_reset, container, false)
        val email = v.findViewById<EditText>(R.id.editTextRestEmail)
        val sendButton = v.findViewById<Button>(R.id.buttonSend)
        val instruction = InstructionFragment()
        val supportFragmentManager = activity?.supportFragmentManager?.beginTransaction()


        sendButton.setOnClickListener {
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


        return v
    }

    companion object {

    }
}