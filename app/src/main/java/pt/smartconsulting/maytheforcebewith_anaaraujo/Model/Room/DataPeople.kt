package pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataPeople(
    @PrimaryKey val id : Int,
    val name : String,
    val height : String,
    val mass : String,
    val hair_color : String,
    val skin_color : String,
    val eye_color : String,
    val birth_year : String,
    val gender : String
)