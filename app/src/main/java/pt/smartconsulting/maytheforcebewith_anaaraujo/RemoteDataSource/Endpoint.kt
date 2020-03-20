package pt.smartconsulting.maytheforcebewith_anaaraujo.RemoteDataSource

import okhttp3.ResponseBody
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.PeopleDetails
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.SerializeDataPeople
import retrofit2.Call
import retrofit2.http.*

//endpoint do serviço
interface Endpoint {

    @GET("people")
    fun dataOfPeople(): Call<SerializeDataPeople>

    @POST("4758732a-c6df-428a-b7cf-378dc735249e") //https://webhook.site/4758732a-c6df-428a-b7cf-378dc735249e
    fun postDetailsAboutPeople(@Body peopleDetails: DataPeople) : Call<ResponseBody>
}