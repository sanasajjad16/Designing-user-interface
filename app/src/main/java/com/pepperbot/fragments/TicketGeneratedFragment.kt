package com.pepperbot.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.conversation.Say
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.pepperbot.Globals
import com.pepperbot.R
import com.pepperbot.databinding.FragmentStartBinding
import com.pepperbot.databinding.FragmentTicketGeneratedBinding
import com.pepperbot.databinding.FragmentTicketSummaryBinding


class TicketGeneratedFragment : Fragment() , RobotLifecycleCallbacks{
    lateinit var binding: FragmentTicketGeneratedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding= FragmentTicketGeneratedBinding.inflate(layoutInflater, container, false)
            // Inflate the layout for this fragment
            return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        QiSDK.register(activity,this)
        val randomString = randomString(8)
        binding.ticketId.text=randomString

        binding.btnBack.setOnClickListener {
            Globals.from=""
            Globals.destination=""
            Globals.ticketType=""
            Navigation.findNavController(view).navigate(R.id.action_ticketGeneratedFragment_to_startFragment)

        }

    }

    fun randomString(length: Int): String {
        val alphabet: List<Char> = ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { alphabet.random() }
            .joinToString("")
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {
        val say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
            .withText("Ticket purchased successfully here is your ticket") // Set the text to say.
            .build() // Build the say action.
        // Execute the action.
        say.run()
    }

    override fun onRobotFocusLost() {

    }

    override fun onRobotFocusRefused(reason: String?) {

    }


}