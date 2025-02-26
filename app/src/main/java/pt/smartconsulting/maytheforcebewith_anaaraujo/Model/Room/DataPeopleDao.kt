package pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataPeopleDao {
    @Query("SELECT * FROM DataPeople")
    fun getAll(): List<DataPeople>

    @Insert
    fun insertAll(dataPeople:List<DataPeople>)

    //para encontrar o match
    @Query("SELECT * FROM DataPeople WHERE name = :search")
    fun searchWord(search: String): List<DataPeople>
}
