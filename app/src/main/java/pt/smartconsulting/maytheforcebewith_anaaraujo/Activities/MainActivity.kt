package pt.smartconsulting.maytheforcebewith_anaaraujo.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.Adapters.PeopleInStarWarsAdapter
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.R
import pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel.MainViewModel

class   MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var dataPeopleAdapter: PeopleInStarWarsAdapter
    private val list = ArrayList<DataPeople>()
    private var peopleData = ArrayList<DataPeople>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //criação do viewModel associado à activity
        mainViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(MainViewModel::class.java)

        //iniciar ViewModel (este mandará ir buscar dados através do repository)
        mainViewModel.init()

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "database-name").build()

        doAsync {
            //execute this line on a background thread
            peopleData = db.dataPeopleDao().getAll() as ArrayList<DataPeople>
        }

        println("************************** ${peopleData[9]}")

        dataPeopleAdapter.submitList(peopleData)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        dataPeopleAdapter = PeopleInStarWarsAdapter()
        recycler_view.adapter = dataPeopleAdapter
    }
}
