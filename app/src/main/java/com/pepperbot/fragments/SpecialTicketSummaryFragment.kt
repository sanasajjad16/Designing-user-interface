package com.pepperbot.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.conversation.Say
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.pepperbot.Globals
import com.pepperbot.R
import com.pepperbot.databinding.FragmentSpecialTicketSummaryBinding


class SpecialTicketSummaryFragment : Fragment() , RobotLifecycleCallbacks {

    lateinit var binding : FragmentSpecialTicketSummaryBinding
    lateinit var qiContext: QiContext
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSpecialTicketSummaryBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        QiSDK.register(activity,this)
        binding.setSelectionType.text=Globals.ticketType
        binding.btnConfirm.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_specialTicketSummaryFragment_to_ticketGeneratedFragment)
        }

        binding.btnBack.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_specialTicketSummaryFragment_to_ticketTypeFragment)
        }
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {

        val say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
            .withText("") // Set the text to say.
            .build() // Build the say action.
        // Execute the action.
        say.run()
    }

    override fun onRobotFocusLost() {

    }

    override fun onRobotFocusRefused(reason: String?) {

    }


}