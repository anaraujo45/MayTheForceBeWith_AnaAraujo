package pt.smartconsulting.maytheforcebewith_anaaraujo.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.Adapters.PeopleInStarWarsAdapter
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.R

class MainActivity : AppCompatActivity(), PeopleInStarWarsAdapter.OnNoteListener {
    private lateinit var dataPeopleAdapter: PeopleInStarWarsAdapter
    private var listDataPeople = ArrayList<DataPeople>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "database-name").build()
        doAsync {
            listDataPeople = db.dataPeopleDao().getAll() as ArrayList<DataPeople>
            dataPeopleAdapter.submitList(listDataPeople)
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        dataPeopleAdapter = PeopleInStarWarsAdapter(listDataPeople, this)
        recycler_view.adapter = dataPeopleAdapter
    }

    override fun onNoteClick(position: Int) {
        //i want the position in list here
        Log.d("Clicked!", "onNoteClick: $position")

        showProgressBar()
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("Position", position)
        startActivity(intent)
    }

    //surgir a progressBar
    private fun showProgressBar() {
        progressBar_waiting.visibility= View.VISIBLE
    }

    //remover a progressBar
    private fun hideProgressBar() {
        progressBar_waiting.visibility = View.GONE
    }
}