package pt.smartconsulting.maytheforcebewith_anaaraujo.Repository

import android.content.Context
import androidx.room.Room
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople

class MainRepository {
    companion object{
        val sharedInstance = MainRepository()
    }

    fun getDetails(name: String, context: Context, haveDetails: (Int) -> Unit) {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()
        val peopleData = db.dataPeopleDao().getAll() as ArrayList<DataPeople>

        loop@ for (i in 0..9) {
            var matchName = peopleData[i].name
            if(matchName == name){
                haveDetails(i)
            }
        }
    }
}