package com.example.tourism_app.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tourism_app.R

/**
 * A simple [Fragment] subclass.
 * Use the [MydetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MydetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mydetails, container, false)





        return view


    }


}