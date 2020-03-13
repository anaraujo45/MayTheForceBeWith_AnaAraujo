package pt.smartconsulting.maytheforcebewith_anaaraujo.RemoteDataSource

import okhttp3.ResponseBody
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.SerializeDataPeople
import retrofit2.Call
import retrofit2.http.*

//endpoint do serviço
interface Endpoint {

    @GET("people")
    fun dataOfPeople(): Call<SerializeDataPeople>
}