package pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.Repository.SplashScreenRepository

class SplashScreenViewModel : ViewModel(){
    private var repositorySplashScreenRepository : SplashScreenRepository = SplashScreenRepository.sharedInstance
    private var mDataPeople: MutableLiveData<List<DataPeople>> = MutableLiveData()
    private var currentState: MutableLiveData<States> = MutableLiveData()

    enum class States{
        DONE, LOAD, FAIL
    }

    fun init(context : Context){
        //se a list com a informação da api for nula o currentState é colocado a load e é usado o serviço no repositorio para obter os dados
        if(mDataPeople.value ==null) {
            currentState.postValue(States.LOAD)
            repositorySplashScreenRepository.getDataInApi {
                //Se a lista (it) estiver a null significa que não há dados e algo correu mal
                if (it == null) {
                    currentState.postValue(States.FAIL)
                }
                //se não é pq há dados e se pode submete-los e atualizar a currentState
                else {
                    repositorySplashScreenRepository.importDataToDataBase(context, it){
                        if(it){
                            currentState.postValue(States.DONE)
                        }
                        else{
                            currentState.postValue(States.FAIL)
                        }
                    }
                }
            }
        }
    }

    fun getIsUpdatingLiveDataLoaded(): LiveData<States>? {
        return currentState
    }
}