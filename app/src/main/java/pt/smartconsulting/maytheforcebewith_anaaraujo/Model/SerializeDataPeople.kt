package pt.smartconsulting.maytheforcebewith_anaaraujo.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//responsável por receber os dados e serializa-los, utilização de Gson para isso
class SerializeDataPeople {
    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("next")
    @Expose
    var next: String? = null

    @SerializedName("results")
    @Expose
    var results: Array<Results?>? = null
}

class Results {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("height")
    @Expose
    var height: String? = null

    @SerializedName("mass")
    @Expose
    var mass: String? = null

    @SerializedName("hair_color")
    @Expose
    var hair_color: String? = null

    @SerializedName("skin_color")
    @Expose
    var skin_color: String? = null

    @SerializedName("eye_color")
    @Expose
    var eye_color: String? = null

    @SerializedName("birth_year")
    @Expose
    var birth_year: String? = null

    @SerializedName("gender")
    @Expose
    var gender: String? = null

    @SerializedName("homeworld")
    @Expose
    var homeworld: String? = null

    /*
    @SerializedName("films")
    @Expose
    var films: Array<Films?>? = null

    @SerializedName("species")
    @Expose
    var species: Array<Species?>? = null

    @SerializedName("vehicles")
    @Expose
    var vehicles: Array<Vehicles?>? = null

    @SerializedName("starships")
    @Expose
    var starships: Array<Starships?>? = null
    */
    @SerializedName("created")
    @Expose
    var created: String? = null

    @SerializedName("edited")
    @Expose
    var edited: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null
}
/*
class Films {}

class Species {}

class Vehicles {}

class Starships {}
 */