package pt.smartconsulting.maytheforcebewith_anaaraujo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_list_item.view.*
import pt.smartconsulting.maytheforcebewith_anaaraujo.Activities.MainActivity
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.R

class PeopleInStarWarsAdapter(listDataPeople: ArrayList<DataPeople>, onNoteListener: OnNoteListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var listDataPeople = ArrayList<DataPeople>()
    private lateinit var mOnNoteListener : OnNoteListener

    fun PeopleInStarWarsAdapter(listDataPeople: ArrayList<DataPeople>, onNoteListener: OnNoteListener) {
        this.listDataPeople = listDataPeople
        mOnNoteListener = onNoteListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
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
            is ViewHolder -> {
                holder.ViewHolder(listDataPeople[position], mOnNoteListener)
            }
        }
    }

    fun submitList(dataPeopleList: ArrayList<DataPeople>) {
        this.listDataPeople = dataPeopleList
        notifyDataSetChanged()
    }

    class ViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val textViewName : TextView = itemView.textView_name
        var onNoteListener: OnNoteListener? = null

        fun ViewHolder(dataPeople: DataPeople, onNoteListener: OnNoteListener) {
            val name = dataPeople.name
            this.textViewName.text = name
            this.onNoteListener = onNoteListener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onNoteListener?.onNoteClick(adapterPosition)
        }
    }

    interface OnNoteListener {
        fun onNoteClick(position: Int)
    }

    init {
        this.listDataPeople = listDataPeople
        mOnNoteListener = onNoteListener
    }
}