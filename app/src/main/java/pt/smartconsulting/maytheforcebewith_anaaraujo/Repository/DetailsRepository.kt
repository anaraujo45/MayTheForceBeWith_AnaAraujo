package pt.smartconsulting.maytheforcebewith_anaaraujo.Repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.PeopleDetails
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.SerializeDataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.RemoteDataSource.Endpoint
import pt.smartconsulting.maytheforcebewith_anaaraujo.RemoteDataSource.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRepository
{
    var totalList = ArrayList<DataPeople>()
    companion object{
        val sharedInstance = DetailsRepository()
    }

    fun getDetails(context : Context, onDetails : (ArrayList<DataPeople>) -> Unit){
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()
        doAsync {
            totalList = db.dataPeopleDao().getAll() as ArrayList<DataPeople>
            onDetails(totalList)
        }
    }

    fun postDetails(onFinnish : (ArrayList<DataPeople>) -> Unit) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://webhook.site./")
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callDataOfPeople = endpoint.postDetailsAboutPeople(PeopleDetails())
    }
}