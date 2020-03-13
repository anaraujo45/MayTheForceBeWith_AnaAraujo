package pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.Repository.SplashScreenRepository

class SplashScreenViewModel : ViewModel(){
    private var repositorySplashScreenRepository : SplashScreenRepository = SplashScreenRepository.sharedInstance
    private var mDataPeople: MutableLiveData<List<DataPeople>> = MutableLiveData()
    private var mIsUpdating: MutableLiveData<Boolean> = MutableLiveData()
    private var currentState: MutableLiveData<States> = MutableLiveData()

    enum class States{
        DONE, LOAD, FAIL
    }

    fun init(){
        if(mDataPeople.value ==null) {
            currentState.postValue(States.LOAD)
            repositorySplashScreenRepository.getDataInApi {
                if (it != null) {
                    mDataPeople.postValue(it)
                    currentState.postValue(States.DONE)
                } else {
                    currentState.postValue(States.FAIL)
                }
            }
        }
    }

    fun getPeopleLiveData(): LiveData<List<DataPeople>>? {
        return mDataPeople
    }

    fun getIsUpdatingLiveDataLoaded(): LiveData<States>? {
        return currentState
    }
}