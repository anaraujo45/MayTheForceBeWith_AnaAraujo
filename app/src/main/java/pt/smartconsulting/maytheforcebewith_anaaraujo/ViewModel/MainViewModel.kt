package pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.Repository.MainRepository
import pt.smartconsulting.maytheforcebewith_anaaraujo.Repository.SplashScreenRepository

class MainViewModel : ViewModel() {
    private var repositoryMainRepository : MainRepository = MainRepository.sharedInstance
    private var mDataPeopleList: MutableLiveData<ArrayList<DataPeople>> = MutableLiveData()

    fun init(context : Context){
        repositoryMainRepository.getList(context) {
            if(it!=null){
                mDataPeopleList.postValue(it)
            }
            else{
                mDataPeopleList.postValue(null)
            }
        }
    }

    fun getList(): LiveData<ArrayList<DataPeople>> {
        return mDataPeopleList
    }
}