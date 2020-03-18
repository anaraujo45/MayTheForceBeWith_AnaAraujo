package pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.Repository.MainRepository
import pt.smartconsulting.maytheforcebewith_anaaraujo.Repository.SplashScreenRepository

class MainViewModel : ViewModel() {
    private var repositoryMainRepository : MainRepository = MainRepository.sharedInstance
    private var mDataPeople: MutableLiveData<List<DataPeople>> = MutableLiveData()
    private var currentState: MutableLiveData<SplashScreenViewModel.States> = MutableLiveData()

    /*fun init(context : Context){
        repositoryMainRepository.getDetails {

        }
    }*/
}