package pt.smartconsulting.maytheforcebewith_anaaraujo.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PeopleDetails {
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
}