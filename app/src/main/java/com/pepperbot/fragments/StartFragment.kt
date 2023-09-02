package com.pepperbot.fragments
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
import com.pepperbot.R
import com.pepperbot.databinding.FragmentStartBinding
import kotlinx.coroutines.*


class StartFragment : Fragment(), RobotLifecycleCallbacks {
    lateinit var binding: FragmentStartBinding
    var qiContext : QiContext?= null
    lateinit var job : Deferred<Unit>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentStartBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        QiSDK.register(activity,this)
        binding.btnStart.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_startFragment_to_ticketTypeFragment)
            job = GlobalScope.async {
                delay(500)
                var say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
                    .withText("Welcome to Deutsche Bahn! How may I help you today?") // Set the text to say.
                    .build() // Build the say action.
                // Execute the action.
                say.run()
                delay(3000)
                 say= SayBuilder.with(qiContext) // Create the builder with the context.
                    .withText("I have 3 types of tickets") // Set the text to say.
                    .build() // Build the say action.
                // Execute the action.
                say.run()
                delay(500)
                say= SayBuilder.with(qiContext) // Create the builder with the context.
                    .withText("49 euro monthly ticket") // Set the text to say.
                    .build() // Build the say action.
                // Execute the action.
                say.run()
                delay(500)
                say= SayBuilder.with(qiContext) // Create the builder with the context.
                    .withText("single ride ticket") // Set the text to say.
                    .build() // Build the say action.
                // Execute the action.
                say.run()
                delay(500)
                say= SayBuilder.with(qiContext) // Create the builder with the context.
                    .withText("whole day ticket") // Set the text to say.
                    .build() // Build the say action.
                // Execute the action.
                say.run()

                delay(500)
                say= SayBuilder.with(qiContext) // Create the builder with the context.
                    .withText("which ticket would you like to buy. Please select the ticket.") // Set the text to say.
                    .build() // Build the say action.
                // Execute the action.
                say.run()

            }

        }
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {
        this.qiContext=qiContext

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