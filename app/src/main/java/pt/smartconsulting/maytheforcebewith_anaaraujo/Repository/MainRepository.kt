package pt.smartconsulting.maytheforcebewith_anaaraujo.Repository

import android.content.Context
import androidx.room.Room
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.BuildConfig
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople

class MainRepository {
    private var listDataPeople = ArrayList<DataPeople>()
    companion object{
        val sharedInstance = MainRepository()
    }

    //obter a list da bd
    fun getList(context: Context, haveList : (ArrayList<DataPeople>) -> Unit) {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DATABASE_NAME).build()
        doAsync {
            listDataPeople = db.dataPeopleDao().getAll() as ArrayList<DataPeople>
            haveList(listDataPeople)
        }
    }

    //se existir match
    /*fun getMatch(name: String, context: Context, haveDetails: (Int) -> Unit) {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DATABASE_NAME).build()
        val peopleData = db.dataPeopleDao().getAll() as ArrayList<DataPeople>

        loop@ for (i in 0..9) {
            var matchName = peopleData[i].name
            if(matchName == name){
                haveDetails(i)
            }
        }
    }*/


}