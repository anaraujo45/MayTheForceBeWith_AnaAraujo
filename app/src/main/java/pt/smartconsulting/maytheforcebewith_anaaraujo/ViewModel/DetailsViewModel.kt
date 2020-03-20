package pt.smartconsulting.maytheforcebewith_anaaraujo.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import org.jetbrains.anko.doAsync
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.AppDatabase
import pt.smartconsulting.maytheforcebewith_anaaraujo.Model.Room.DataPeople
import pt.smartconsulting.maytheforcebewith_anaaraujo.Repository.DetailsRepository
import pt.smartconsulting.maytheforcebewith_anaaraujo.Repository.SplashScreenRepository

class DetailsViewModel : ViewModel() {
    private var repositoryDetailsRepository : DetailsRepository = DetailsRepository .sharedInstance
    private var mDetailsDataPeople: MutableLiveData<ArrayList<DataPeople>> = MutableLiveData()
    private var mStateOfPost: MutableLiveData<Boolean> = MutableLiveData()

    fun init(context : Context){
        if (mDetailsDataPeople.value == null) {
            repositoryDetailsRepository.getDetails(context) { it ->
                mDetailsDataPeople.postValue(it)
            }
        }
    }

    fun addFavorite(position : Int?){
        repositoryDetailsRepository.postDetails(position) {
            mStateOfPost.postValue(it)
        }
    }

    fun getDetails(): LiveData<ArrayList<DataPeople>>? {
        return mDetailsDataPeople
    }

    fun getStateOfPost(): LiveData<Boolean> {
        return mStateOfPost
    }
}