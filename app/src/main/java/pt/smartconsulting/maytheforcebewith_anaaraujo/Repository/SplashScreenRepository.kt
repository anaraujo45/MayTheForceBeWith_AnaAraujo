package pt.smartconsulting.maytheforcebewith_anaaraujo.Repository

import android.util.Log
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

    fun getDataInApi(onFinnish : (Boolean) -> Unit) {
        val retrofitClient = NetworkUtils.getRetrofitInstance()
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callDataOfPeople = endpoint.dataOfPeople()

        callDataOfPeople.enqueue(object : Callback<SerializeDataPeople?> {
            override fun onResponse(call: Call<SerializeDataPeople?>, response: Response<SerializeDataPeople?>) {
                response.body().let{
                    val note: SerializeDataPeople? = it
                    println("***************NAME: ${note?.results?.get(9)?.name}")
                    println("***************GENDER: ${note?.results?.get(9)?.gender}")

                    println("***************NAME: ${note?.results?.get(7)?.name}")
                    println("***************GENDER: ${note?.results?.get(7)?.gender}")
                }
                onFinnish(true)
            }

            override fun onFailure(call: Call<SerializeDataPeople?>, t: Throwable) {
                Log.e("onFailure error", t?.message)
                onFinnish(false)
            }
        })
    }
}