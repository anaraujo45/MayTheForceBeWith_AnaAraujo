package pt.smartconsulting.maytheforcebewith_anaaraujo.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import pt.smartconsulting.maytheforcebewith_anaaraujo.BuildConfig
import pt.smartconsulting.maytheforcebewith_anaaraujo.R
import pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var detailsViewModel : DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //showProgressBar()
        //criação do viewModel associado à activity
        detailsViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(DetailsViewModel::class.java)

        val position = intent.extras?.getInt(BuildConfig.POSITION)
        //iniciar ViewModel
        detailsViewModel.init(this)

        detailsViewModel.getDetails()?.observe(this, Observer{ it ->
            if(it != null){
                if (position != null) {
                    //hideProgressBar()
                    textView_name.text = "Name: ".plus(it[position].name)
                    textView_height.text = "Height: ".plus(it[position].height)
                    textView_mass.text = "Mass: ".plus(it[position].mass)
                    textView_hair_color.text = "Hair Color: ".plus(it[position].hair_color)
                    textView_skinColor.text = "Skin Color: ".plus(it[position].skin_color)
                    textView_eyeColor.text = "Eye Color: ".plus(it[position].eye_color)
                    textView_birthYear.text = "Birth Year: ".plus(it[position].birth_year)
                    textView_gender.text = "Gender: ".plus(it[position].gender)
                }
            }
            else{
                Toast.makeText(this, "Somethings is wrong!", Toast.LENGTH_SHORT).show()
            }
        })

        detailsViewModel.getStateOfPost()?.observe(this, Observer{ it ->
            if(it){
                Toast.makeText(this, "Done, the Data was add in WebHook.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "Something is wrong, the Data wasn't add in WebHook.", Toast.LENGTH_LONG).show()
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

    fun clickAddFavorite(view: View) {
        detailsViewModel.addFavorite(intent.extras?.getInt(BuildConfig.POSITION))
    }
}
