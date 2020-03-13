package pt.smartconsulting.maytheforcebewith_anaaraujo.RemoteDataSource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class responsável pelo pedido à API e comurl base do serviço
class NetworkUtils {
    companion object {  //simple singleton
        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://swapi.co/api/") //https://swapi.co/api/people
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}