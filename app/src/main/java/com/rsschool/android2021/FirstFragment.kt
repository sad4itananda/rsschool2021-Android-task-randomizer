package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import java.lang.NumberFormatException

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var min: EditText? = null
    private var max: EditText? = null
    private var routeToSecondFragment: RouteToSecondFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        routeToSecondFragment = context as RouteToSecondFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        min = view.findViewById(R.id.min_value)
        // TODO: val max = ...
        max = view.findViewById(R.id.max_value)

        generateButton?.setOnClickListener {
            // TODO: send min and max to the SecondFragment

            try {
                val minNum = min?.text.toString().toInt()
                val maxNum = max?.text.toString().toInt()

                if (minNum < maxNum) {
                    routeToSecondFragment?.routeToSecondFragment(minNum, maxNum)
                } else {
                    Toast.makeText(context, "Error: Check Numbers", Toast.LENGTH_LONG).show()
                }
            } catch (e:NumberFormatException) {
                Toast.makeText(context, "Error: Check Fields", Toast.LENGTH_LONG).show()
            }

        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}