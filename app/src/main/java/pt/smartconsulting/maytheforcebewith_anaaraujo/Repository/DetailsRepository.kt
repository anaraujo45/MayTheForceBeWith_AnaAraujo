package pt.smartconsulting.maytheforcebewith_anaaraujo.Repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import okhttp3.ResponseBody
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.BuildConfig
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.RemoteDataSource.Endpoint
import pt.smartconsulting.maytheforcebewith_anaaraujo.RemoteDataSource.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRepository{
    lateinit var db : AppDatabase
    var totalList = ArrayList<DataPeople>()
    companion object{
        val sharedInstance = DetailsRepository()
    }

    fun getDetails(context : Context, onDetails : (ArrayList<DataPeople>) -> Unit){
        db = Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DATABASE_NAME).build()
        doAsync {
            totalList = db.dataPeopleDao().getAll() as ArrayList<DataPeople>
            onDetails(totalList)
        }
    }

    fun postDetails(position : Int?, state : (Boolean) -> Unit) {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://webhook.site/")
        val endpoint = retrofitClient.create(Endpoint::class.java)
        //enviar apenas os dados da pessoa em questão
        if (position != null) {
            println("*************************    ${totalList[position]}")
            val callDataOfPeople = endpoint.postDetailsAboutPeople(totalList[position])

            callDataOfPeople.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    response.code().let{
                        if(it==200){
                            state(true)
                        }
                        else{
                            state(false)
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.e("onFailure error", t.message)
                    state(false)
                }
            })
        }

    }
}