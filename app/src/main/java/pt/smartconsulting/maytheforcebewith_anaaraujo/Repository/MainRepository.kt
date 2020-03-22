package pt.smartconsulting.maytheforcebewith_anaaraujo.Repository

import android.content.Context
import androidx.room.Room
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.BuildConfig
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople

class MainRepository {
    private var listDataPeople = ArrayList<DataPeople>()
    lateinit var db : AppDatabase

    companion object{
        val sharedInstance = MainRepository()
    }

    //obter a list da bd
    fun getList(context: Context, haveList : (ArrayList<DataPeople>) -> Unit) {
        db = Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DATABASE_NAME).build()
        doAsync {
            listDataPeople = db.dataPeopleDao().getAll() as ArrayList<DataPeople>
            haveList(listDataPeople)
        }
    }

    //se existir match
    fun getMatch(word: String, context: Context, listMatch : (ArrayList<DataPeople>) -> Unit) {
        db = Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DATABASE_NAME).build()
        doAsync {
            val listPeopleWithMatch : ArrayList<DataPeople> = db.dataPeopleDao().searchWord(word) as ArrayList<DataPeople>
            if (listPeopleWithMatch.size != 0) {
                listMatch(listPeopleWithMatch)
            }
        }
    }
}