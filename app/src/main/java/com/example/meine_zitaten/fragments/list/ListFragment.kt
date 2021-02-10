package com.example.meine_zitaten.fragments.list

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meine_zitaten.R
import com.example.meine_zitaten.viewmodel.ZitatenViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment: Fragment(R.layout.fragment_list) {

    lateinit var mZitatenViewModel: ZitatenViewModel

    lateinit var adapter: ListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

    }

    fun initRecyclerView(){

        adapter = ListAdapter()
        val recyclerView = recyclerview
//
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel

        mZitatenViewModel = ViewModelProvider(this).get(ZitatenViewModel::class.java)
        mZitatenViewModel.readAllData.observe(viewLifecycleOwner, Observer { zitate ->
            adapter.setData(zitate)
        })

        increase.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }

        recyclerView.adapter = adapter
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete){

            deleteAllUsers()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_ ->

            mZitatenViewModel.deleteAllZitaten()
            Toast.makeText(requireContext(),
                    "Succesfully removed everything",
                    Toast.LENGTH_LONG).show()

        }
        builder.setNegativeButton("No"){ _,_ ->}
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everithing?")
        builder.create().show()
    }
}