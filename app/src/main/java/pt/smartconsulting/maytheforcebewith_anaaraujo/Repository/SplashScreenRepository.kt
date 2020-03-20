package pt.smartconsulting.maytheforcebewith_anaaraujo.Repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.BuildConfig
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeopleDao
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.SerializeDataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.RemoteDataSource.Endpoint
import pt.smartconsulting.maytheforcebewith_anaaraujo.RemoteDataSource.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashScreenRepository {
    companion object{
        val sharedInstance = SplashScreenRepository()
    }

    //função responsável pelo serviço de obter dados da api
    fun getDataInApi(onFinnish : (ArrayList<DataPeople>) -> Unit) {
        var dataPeopleList = ArrayList<DataPeople>()
        lateinit var name : String
        lateinit var height : String
        lateinit var mass : String
        lateinit var hair_color : String
        lateinit var skin_color : String
        lateinit var eye_color : String
        lateinit var birth_year : String
        lateinit var gender : String

        val retrofitClient = NetworkUtils.getRetrofitInstance("https://swapi.co/api/")
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callDataOfPeople = endpoint.dataOfPeople()

        //lista para guardar a informação do user
        val list = ArrayList<DataPeople>()

        callDataOfPeople.enqueue(object : Callback<SerializeDataPeople?> {
            override fun onResponse(call: Call<SerializeDataPeople?>, response: Response<SerializeDataPeople?>) {
                response.body().let{
                    val note: SerializeDataPeople? = it

                    loop@ for (i in 0..9) {
                        name = note?.results?.get(i)?.name.toString()
                        height = note?.results?.get(i)?.height.toString()
                        mass = note?.results?.get(i)?.mass.toString()
                        hair_color = note?.results?.get(i)?.hair_color.toString()
                        skin_color = note?.results?.get(i)?.skin_color.toString()
                        eye_color = note?.results?.get(i)?.eye_color.toString()
                        birth_year = note?.results?.get(i)?.birth_year.toString()
                        gender = note?.results?.get(i)?.gender.toString()

                        list.add(DataPeople(i, name, height, mass, hair_color, skin_color, eye_color, birth_year, gender))
                    }
                    dataPeopleList = list
                    onFinnish(dataPeopleList)
                }
            }

            override fun onFailure(call: Call<SerializeDataPeople?>, t: Throwable) {
                Log.e("onFailure error", t.message)
            }
        })
    }

    fun importDataToDataBase(context : Context, listDataPeople : ArrayList<DataPeople>, onImport : (Boolean) -> Unit){
        val db = Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DATABASE_NAME).build()
        //execute this line on a background thread
        doAsync {
            db.dataPeopleDao().insertAll(listDataPeople)
            onImport(true)
        }
    }
}