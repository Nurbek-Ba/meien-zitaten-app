package com.example.meine_zitaten.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.meine_zitaten.R
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class StartFragment : Fragment(R.layout.fragment_start) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener{

            findNavController().navigate(R.id.action_startFragment_to_listFragment, null)

        }

//        lifecycleScope.launchWhenCreated {
//            withContext(Dispatchers.Main) {
//                delay(1000)
//                btnLogin.setOnClickListener{
//
//                    findNavController().navigate(R.id.action_startFragment_to_listFragment)
//
//                }
//            }
//        }

//        requireActivity().findViewById<BottomNavigationView>(R.id.fragment)?.visibility = View.INVISIBLE
//    }
//
//    override fun onPause() {
//        super.onPause()
//        requireActivity().findViewById<BottomNavigationView>(R.id.fragment)?.visibility = View.VISIBLE
    }
}