package com.example.meine_zitaten
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.meine_zitaten.Zitate
import com.example.meine_zitaten.ZitatenViewModel
import com.example.meine_zitaten.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.dialog_new.*


class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: ZitatenViewModel

    private lateinit var dialog: AlertDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding  = DataBindingUtil.setContentView(this,R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ZitatenViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        val viewPager = binding.zitateViewPager
        val adapter = ZitateViewPagerAdapter()
        //adapter.setQuotes(viewModel.quotes)
        viewPager.adapter = adapter




        viewModel.zitaten.observe(this, Observer { zitaten ->
            adapter.setZitate(zitaten)

            if (viewModel.newZitateAdded){
                viewPager.setCurrentItem(zitaten.size -1, true)
                viewModel.newZitateAdded = false

            }
        })

    }

    fun addZitate(view:View){
        val builder = AlertDialog.Builder(this)
        builder.apply {

            builder.setTitle("New Field")
                .setView(R.layout.dialog_new)
                .setPositiveButton("Ok"){dialog, id ->
                    //Zitat erstellen und an die Liste anhängen
                    createZitate()
                }
            builder.setNegativeButton("Cancel", null)
        }

        dialog = builder.create()
        dialog.show()

    }


    private fun createZitate() {
        val text = dialog.new_citate.text.toString()
        val author = dialog.new_author.text.toString()
        val year = dialog.new_year.text.toString()
        //neue Zitat erstellen
        viewModel.createZitate(text, author, year)

    }
}