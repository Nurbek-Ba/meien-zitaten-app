package com.example.meine_zitaten.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.meine_zitaten.R
import com.example.meine_zitaten.fragments.update.UpdateFragmentArgs
import com.example.meine_zitaten.model.Zitate

import com.example.meine_zitaten.viewmodel.ZitatenViewModel
import com.mikepenz.materialdrawer.icons.MaterialDrawerFont.author
import kotlinx.android.synthetic.main.custom_row.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mZitatenViewModel: ZitatenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mZitatenViewModel = ViewModelProvider(this).get(ZitatenViewModel::class.java)

        view.updateText_et.setText(args.currentUser.text)
        view.updateAuthor_et.setText(args.currentUser.author)
        view.updateAge_et.setText(args.currentUser.age.toString())

        view.update_btn.setOnClickListener{
            updateItem()
        }
        //Add manu
        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem(){
        val text = updateText_et.text.toString()
        val author = updateAuthor_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())


        if (inputCheck(text, author, updateAge_et.text)) {
            //Create Zitate Object
            val updateZitate = Zitate(args.currentUser.id, text, author, age)
            //Update Current Zitate
            mZitatenViewModel.updateZitate(updateZitate)
            Toast.makeText(requireContext(), "Update Successfully!", Toast.LENGTH_LONG).show()

            //Navigate BackF
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_ ->

            mZitatenViewModel.deleteZitate(args.currentUser)
            Toast.makeText(requireContext(),
                "Succesfully removed: ${args.currentUser.text}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No"){ _,_ ->}
        builder.setTitle("Delete ${args.currentUser.text}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.text}?")
        builder.create().show()
    }
}
