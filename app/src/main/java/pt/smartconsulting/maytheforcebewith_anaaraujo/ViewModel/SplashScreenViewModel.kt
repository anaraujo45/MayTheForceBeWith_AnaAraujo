package pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.smartconsulting.maytheforcebewith_anaaraujo.Repository.SplashScreenRepository

class SplashScreenViewModel : ViewModel(){
    private var repositorySplashScreenRepository : SplashScreenRepository = SplashScreenRepository.sharedInstance
    private var currentState: MutableLiveData<States> = MutableLiveData()

    enum class States{
        DONE, LOAD, FAIL
    }

    fun init(){
        repositorySplashScreenRepository.getDataInApi {
            if(it){
                currentState.postValue(States.DONE)
            }
            else{
                currentState.postValue(States.FAIL)
            }
        }
    }

    fun getIsUpdatingLiveDataLoaded(): LiveData<States>? {
        return currentState
    }
}