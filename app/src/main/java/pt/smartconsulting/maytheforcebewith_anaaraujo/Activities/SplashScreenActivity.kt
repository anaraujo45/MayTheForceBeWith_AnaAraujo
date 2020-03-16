package pt.smartconsulting.maytheforcebewith_anaaraujo.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.R
import pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel.SplashScreenViewModel


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var splashScreenViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        showProgressBar()
        SplashScreenViewModel.States.LOAD

        //criação do viewModel associado à activity
        splashScreenViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(SplashScreenViewModel::class.java)

        //iniciar ViewModel (este mandará ir buscar dados através do repository)
        splashScreenViewModel.init()

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
        //observar o estado da "busca" de dados
        splashScreenViewModel.getIsUpdatingLiveDataLoaded()?.observe(this, Observer{ it ->
            when(it){
                SplashScreenViewModel.States.DONE ->{
                    Toast.makeText(this, "Data is ready.", Toast.LENGTH_LONG).show()

                    splashScreenViewModel.getPeopleLiveData()?.observe(this, Observer {
                        //execute this line on a background thread
                        doAsync {
                            db.dataPeopleDao().insertAll(it)
                        }
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    })
                }
                SplashScreenViewModel.States.LOAD ->{
                    Toast.makeText(this, "Loading data.", Toast.LENGTH_LONG).show()
                }
                SplashScreenViewModel.States.FAIL ->{
                    hideProgressBar()
                    Toast.makeText(this, "Fail. Come back later", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    //surgir a progressBar
    private fun showProgressBar() {
        progressBar_waitingForData.visibility= View.VISIBLE
    }

    //remover a progressBar
    private fun hideProgressBar() {
        progressBar_waitingForData.visibility = View.GONE
    }
}
