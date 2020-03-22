package pt.smartconsulting.maytheforcebewith_anaaraujo.Activities

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.Adapters.PeopleInStarWarsAdapter
import pt.smartconsulting.maytheforcebewith_anaaraujo.BuildConfig
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.R
import pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel.MainViewModel
import pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel.SplashScreenViewModel

class MainActivity : AppCompatActivity(), PeopleInStarWarsAdapter.OnNoteListener {
    private lateinit var mainViewModel: MainViewModel
    private var listDataPeople = ArrayList<DataPeople>()
    private lateinit var dataPeopleAdapter: PeopleInStarWarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showProgressBar()
        //criação do viewModel associado à activity
        mainViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(MainViewModel::class.java)

        //iniciar ViewModel
        mainViewModel.init(this)

        mainViewModel.getList().observe(this, Observer{ it ->
            if(it != null){
                hideProgressBar()
                listDataPeople = it
                dataPeopleAdapter.submitList(listDataPeople)
            }
            else{
                Toast.makeText(this, "Something is wrong!", Toast.LENGTH_SHORT).show()
            }
        })

        initRecyclerView(listDataPeople)
    }

    private fun initRecyclerView(listDataPeople : ArrayList<DataPeople>) {
        recycler_view.layoutManager = LinearLayoutManager(this)
        dataPeopleAdapter = PeopleInStarWarsAdapter(listDataPeople, this)
        recycler_view.adapter = dataPeopleAdapter
    }

    override fun onNoteClick(position: Int){
        Log.d("Clicked!", "onNoteClick: $position")

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(BuildConfig.POSITION, position)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val manager =  getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()

                Log.v("Information", "Looking for $query")
                mainViewModel.checkMatch(query, this@MainActivity)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                Log.v("Information", "$newText")
                return false
            }
        })

        return true
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