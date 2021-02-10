package com.example.meine_zitaten.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.meine_zitaten.R
import com.example.meine_zitaten.model.Zitate
import com.example.meine_zitaten.viewmodel.ZitatenViewModel
import com.mikepenz.materialdrawer.icons.MaterialDrawerFont.description
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter :
        RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var zitateList = mutableListOf<Zitate>()

    private lateinit var mZitatenViewModel: ZitatenViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return zitateList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = zitateList[position]
        holder.itemView.tv_count.text = currentItem.id.toString()
        holder.itemView.tv_Title.text = currentItem.text
        holder.itemView.tv_description.text = currentItem.author
        holder.itemView.tv_count.text = currentItem.count.toString()

        holder.itemView.setOnClickListener {

            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(zitate: List<Zitate>){
        this.zitateList = zitate as MutableList<Zitate>
        notifyDataSetChanged()

    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(zitate: Zitate){
            with(itemView) {


//                tv_Title.text = zitate.text
//                tv_description.text = zitate.author
////                iv_isDone.isDone = zitate.isDone

                if (zitate.isDone){
                    iv_isDone.setImageResource(R.drawable.ic_baseline_done_outline_24)
                }
                else{
                    iv_isDone.setImageResource(R.drawable.ic_baseline_done_outline_green)
                }

                iv_isDone.setOnClickListener {
                    mZitatenViewModel.updateZitate(zitate.copy(isDone = !zitate.isDone))
                    notifyDataSetChanged()
                }

//                itemView.setOnClickListener {
//                    tasksViewModel.showTaskDetails(task)
//                }

            }
        }

    }

}
