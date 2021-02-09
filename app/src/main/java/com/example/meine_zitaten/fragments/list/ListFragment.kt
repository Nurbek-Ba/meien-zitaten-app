package com.example.meine_zitaten.fragments.list

import android.app.AlertDialog
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

import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mZitatenViewModel: ZitatenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //RecyclerView

        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel

        mZitatenViewModel = ViewModelProvider(this).get(ZitatenViewModel::class.java)
        mZitatenViewModel.readAllData.observe(viewLifecycleOwner, Observer { zitate ->
            adapter.setData(zitate)
        })

        view.increase.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        //Add menu

        setHasOptionsMenu(true)

        return view
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