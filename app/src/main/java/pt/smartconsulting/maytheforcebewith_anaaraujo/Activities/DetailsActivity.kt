package pt.smartconsulting.maytheforcebewith_anaaraujo.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.layout_list_item.*
import kotlinx.android.synthetic.main.layout_list_item.textView_name
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.R

class DetailsActivity : AppCompatActivity() {
    private var listDataPeoplePosition = ArrayList<DataPeople>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val position = intent.extras?.getInt("Position");
        Toast.makeText(this, "Carregou em $position", Toast.LENGTH_LONG).show()

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "database-name").build()
        doAsync {
            listDataPeoplePosition = db.dataPeopleDao().getAll() as ArrayList<DataPeople>
        }

        textView_name.text = "Name: ".plus(position?.let { listDataPeoplePosition[it].name })
        textView_height.text = "Height: ".plus(position?.let { listDataPeoplePosition[it].height })
        textView_mass.text = "Mass: ".plus(position?.let { listDataPeoplePosition[it].mass })
        textView_hair_color.text = "Hair Color: ".plus(position?.let { listDataPeoplePosition[it].hair_color })
        textView_skinColor.text = "Skin Color: ".plus(position?.let { listDataPeoplePosition[it].skin_color })
        textView_eyeColor.text = "Eye Color: ".plus(position?.let { listDataPeoplePosition[it].eye_color })
        textView_birthYear.text = "Birth Year: ".plus(position?.let { listDataPeoplePosition[it].birth_year })
        textView_gender.text = "Gender: ".plus(position?.let { listDataPeoplePosition[it].gender })
    }
}
