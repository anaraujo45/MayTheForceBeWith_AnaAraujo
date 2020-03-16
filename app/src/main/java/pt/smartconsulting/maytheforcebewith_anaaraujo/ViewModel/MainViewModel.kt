package pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople

class MainViewModel : ViewModel() {
    private var mDataPeople: MutableLiveData<List<DataPeople>> = MutableLiveData()

    fun init(){

    }
}