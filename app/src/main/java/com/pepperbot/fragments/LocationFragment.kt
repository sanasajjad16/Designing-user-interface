package com.pepperbot.fragments

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.conversation.Say
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.pepperbot.Globals
import com.pepperbot.R
import com.pepperbot.databinding.FragmentLocationBinding


class LocationFragment : Fragment() , RobotLifecycleCallbacks {
    lateinit var binding: FragmentLocationBinding
    lateinit var qiContext: QiContext

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLocationBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        QiSDK.register(activity,this)

        if (!Globals.from.isNullOrEmpty()){
            binding.enterFromLocation.setText(Globals.from.toString())
        }
        if (!Globals.destination.isNullOrEmpty()){
            binding.enterToLocation.setText(Globals.from.toString())
        }

        binding.enterFromLocation.addTextChangedListener(object  : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               Globals.from=s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                Globals.from=s.toString()
            }
        })

        binding.enterToLocation.addTextChangedListener(object  : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Globals.destination=s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                Globals.destination=s.toString()
            }
        })

        binding.btnNext.setOnClickListener {
            if (validate()){
                if (Globals.ticketType.equals("49 Euro")){
                    Navigation.findNavController(view).navigate(R.id.action_locationFragment_to_specialTicketSummaryFragment)
                }
                else
                {
                    Navigation.findNavController(view).navigate(R.id.action_locationFragment_to_ticketSummaryFragment)
                }
            }
        }
        binding.btnBack.setOnClickListener {
                Globals.destination=""
                Globals.from=""
                Navigation.findNavController(view).navigate(R.id.action_locationFragment_to_ticketTypeFragment)
        }

        binding.parentLayout.setOnClickListener {
            activity?.let { it1 -> hideKeyboardFrom(it1,view) }
        }

    }

    private fun  validate() : Boolean
    {
        if (TextUtils.isEmpty(Globals.from)) {
            binding.enterFromLocation.error="Enter Start Location"
            return false
        }
        if (TextUtils.isEmpty(Globals.destination)){
            binding.enterToLocation.error="Enter Your Destination"
            return false
        }

       return true
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {
        if (qiContext != null) {
            this.qiContext=qiContext
        }
//                val say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
//                .withText("Please Enter your Start and End location") // Set the text to say.
//                .build() // Build the say action.
//        // Execute the action.
//                 say.run()
    }


    override fun onRobotFocusLost() {

    }

    override fun onRobotFocusRefused(reason: String?) {

    }

    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}