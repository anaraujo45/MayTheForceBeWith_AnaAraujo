package pt.smartconsulting.maytheforcebewith_anaaraujo.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_splash_screen.*
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

        //observar o estado da "busca" de dados
        splashScreenViewModel.getIsUpdatingLiveDataLoaded()?.observe(this, Observer{
            when(it){
                SplashScreenViewModel.States.DONE ->{
                    Toast.makeText(this, "Data is ready.", Toast.LENGTH_LONG).show()

                    Handler().postDelayed({
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }, 8000)
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

    private fun showProgressBar() {
        progressBar_waitingForData.visibility= View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar_waitingForData.visibility = View.GONE
    }
}
