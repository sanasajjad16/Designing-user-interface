package com.pepperbot.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.conversation.Say
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.pepperbot.Globals
import com.pepperbot.R
import com.pepperbot.databinding.FragmentTicketTypeBinding
import kotlinx.coroutines.*


class TicketTypeFragment : Fragment(), RobotLifecycleCallbacks {
    lateinit var binding : FragmentTicketTypeBinding
    lateinit var qiContext: QiContext
    lateinit var job : Deferred<Unit>
    lateinit var speech : Deferred<Unit>
    lateinit var speech2 : Deferred<Unit>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTicketTypeBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        QiSDK.register(activity,this)


            when(Globals.ticketType){
                "49 Euro"->{
                    binding.radioButton1.setBackgroundColor(Color.parseColor("#551F1F"))
                    binding.radioButton1.setTextColor(Color.WHITE)
                    binding.radioButton2.setTextColor(Color.BLACK)
                    binding.radioButton3.setTextColor(Color.BLACK)

                }
                "Whole Day"->{
                    binding.radioButton2.setBackgroundColor(Color.parseColor("#551F1F"))
                    binding.radioButton1.setTextColor(Color.BLACK)
                    binding.radioButton2.setTextColor(Color.WHITE)
                    binding.radioButton3.setTextColor(Color.BLACK)
                }
                "Single Ride"->{
                    binding.radioButton3.setBackgroundColor(Color.parseColor("#551F1F"))
                    binding.radioButton1.setTextColor(Color.BLACK)
                    binding.radioButton2.setTextColor(Color.BLACK)
                    binding.radioButton3.setTextColor(Color.WHITE)
                }
            }



        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radio_button_1 ->{
                   Globals.ticketType=binding.radioButton1.text.toString()
                    binding.radioButton1.setTextColor(Color.WHITE)
                    binding.radioButton2.setTextColor(Color.BLACK)
                    binding.radioButton3.setTextColor(Color.BLACK)
                    GlobalScope.async {
                        var say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
                            .withText("You have Selected ${Globals.ticketType.toString()} ticket. In 49 euro ticket you can travel for whole month in all means of transport except ICE trains. Please click confirm to purchase the ticket") // Set the text to say.
                            .build() // Build the say action.
                        // Execute the action.
                        say.run()
                    }
                    if (!Globals.ticketType.isNullOrEmpty()){
                        Navigation.findNavController(view).navigate(R.id.action_ticketTypeFragment_to_specialTicketSummaryFragment)
                    }


                }
                R.id.radio_button_2 ->{
                    Globals.ticketType=binding.radioButton2.text.toString()
                    binding.radioButton1.setTextColor(Color.BLACK)
                    binding.radioButton2.setTextColor(Color.WHITE)
                    binding.radioButton3.setTextColor(Color.BLACK)
                    GlobalScope.async {
                        var say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
                            .withText("You have Selected ${Globals.ticketType.toString()} ticket. Please Enter your Start and End location") // Set the text to say.
                            .build() // Build the say action.
                        // Execute the action.
                        say.run()
                    }
                    if (!Globals.ticketType.isNullOrEmpty()){
                        Navigation.findNavController(view).navigate(R.id.action_ticketTypeFragment_to_locationFragment)
                    }
                }
                R.id.radio_button_3 ->{
                    Globals.ticketType=binding.radioButton3.text.toString()
                    binding.radioButton1.setTextColor(Color.BLACK)
                    binding.radioButton2.setTextColor(Color.BLACK)
                    binding.radioButton3.setTextColor(Color.WHITE)
                    GlobalScope.async {
                        var say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
                            .withText("You have Selected ${Globals.ticketType.toString()} ticket. Please Enter your Start and End location") // Set the text to say.
                            .build() // Build the say action.
                        // Execute the action.
                        say.run()
                    }
                    if (!Globals.ticketType.isNullOrEmpty()){
                        Navigation.findNavController(view).navigate(R.id.action_ticketTypeFragment_to_locationFragment)
                    }
                }

            }
        }
//        binding.next.setOnClickListener {
//            if (!Globals.ticketType.isNullOrEmpty()){
//                Navigation.findNavController(view).navigate(R.id.action_ticketTypeFragment_to_locationFragment)
//            }
//            else
//            {
//                job = GlobalScope.async {
//                   val say: Say = SayBuilder.with(qiContext)
//                       .withText("Please choose your ticket type first")
//                       .build()
//                   say.run()
//                }
//                GlobalScope.launch {
//                    if(job.isActive)
//                        job.join()
//                }
//            }
//        }

        binding.btnBack.setOnClickListener {
            Globals.ticketType=""
            Navigation.findNavController(view).navigate(R.id.action_ticketTypeFragment_to_startFragment)

        }


    }

    override fun onRobotFocusGained(qiContext: QiContext?) {
        if (qiContext != null) {
            this.qiContext=qiContext
        }
//        val say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
//                .withText("") // Set the text to say.
//                .build() // Build the say action.
//        // Execute the action.
//        say.run()

    }

    override fun onRobotFocusLost() {

    }

    override fun onRobotFocusRefused(reason: String?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        QiSDK.unregister(activity)
    }

}