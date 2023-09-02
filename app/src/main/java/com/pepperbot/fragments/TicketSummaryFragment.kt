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
import com.pepperbot.databinding.FragmentTicketSummaryBinding


class TicketSummaryFragment : Fragment() , RobotLifecycleCallbacks{

    lateinit var binding: FragmentTicketSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentTicketSummaryBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        QiSDK.register(activity,this)
        binding.setSelectionType.text=Globals.ticketType.toString()
        binding.locationFrom.text=Globals.from
        binding.locationTo.text=Globals.destination

    binding.btnConfirm.setOnClickListener {

        Navigation.findNavController(view).navigate(R.id.action_ticketSummaryFragment_to_ticketGeneratedFragment)
    }
        binding.btnBack.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_ticketSummaryFragment_to_locationFragment)
        }

    }

    override fun onRobotFocusGained(qiContext: QiContext?) {
                val say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("Your Start location is ${Globals.from} and your Destination is ${Globals.destination}. Please Click confirm to Generate your ticket") // Set the text to say.
                .build() // Build the say action.
        // Execute the action.
        say.run()
    }

    override fun onRobotFocusLost() {

    }

    override fun onRobotFocusRefused(reason: String?) {

    }
}