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
        currentState.postValue(States.LOAD)

        //se a list com a informação da api for nula o currentState é colocado a load e é usado o serviço no repositorio para obter os dados
        if(mDataPeople.value ==null) {
            repositorySplashScreenRepository.getDataInApi {
                //se é true, é pq há dados e se pode submete-los e atualizar a currentState
                if (it) {
                    repositorySplashScreenRepository.importDataToDataBase(context){note ->
                        if(note){
                            currentState.postValue(States.DONE)
                        }
                        else{
                            currentState.postValue(States.FAIL)
                        }
                    }
                }
                //Se for false, significa que não há dados e algo correu mal
                else {
                    currentState.postValue(States.FAIL)
                }
            }
        }
    }

    fun getIsUpdatingLiveDataLoaded(): LiveData<States>? {
        return currentState
    }
}