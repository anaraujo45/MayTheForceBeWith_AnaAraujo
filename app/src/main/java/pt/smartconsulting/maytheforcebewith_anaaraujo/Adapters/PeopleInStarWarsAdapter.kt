package pt.smartconsulting.maytheforcebewith_anaaraujo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_list_item.view.*
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.R

class PeopleInStarWarsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var listDataPeople = ArrayList<DataPeople>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PeopleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_list_item, parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return listDataPeople.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PeopleViewHolder -> {
                holder.bind(listDataPeople[position])
            }
        }
    }

    fun submitList(dataPeopleList: ArrayList<DataPeople>) {
        this.listDataPeople = dataPeopleList
        notifyDataSetChanged()
    }

    class PeopleViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewName : TextView = itemView.textView_name

        fun bind(dataPeople: DataPeople) {
            val name = dataPeople.name
            this.textViewName.text = name
        }
    }
}

