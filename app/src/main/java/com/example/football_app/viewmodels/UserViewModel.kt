package com.example.football_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.leagueclass.Data
import com.example.football_app.network.leagueclass.LeagueResult
import com.example.football_app.network.seasons.SeasonsResult
import com.example.football_app.network.standingsclass.Standing
import com.example.football_app.repositories.UserRepository
import com.example.football_app.utils.NetworkHelper
import com.example.football_app.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository,
                    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val liveData = MutableLiveData<Resource<List<Data>>>()
    private val liveStandingsData = MutableLiveData<Resource<List<Standing>>>()
    private val liveSeasonsData = MutableLiveData<Resource<com.example.football_app.network.seasons.Data>>()

    fun getWord(): LiveData<Resource<List<Data>>>{
        val apiService1 = RetrofitClient.apiService()

        viewModelScope.launch {
            liveData.postValue(Resource.loading(null))

            if (networkHelper.isNetworkConnected()){
                try {
                    coroutineScope {

                        val async1 = async { userRepository.getRemoteUsers() }

                        val await1 = async1.await()
                        userRepository.addUsers(await1.data)
                        liveData.postValue(Resource.success(await1.data))

                    }
                }catch (e:Exception){
                    liveData.postValue(Resource.error(e.message ?: "Error",null))
                }
            } else {
                liveData.postValue(Resource.success(userRepository.getLocalUsers()))
            }





        }
        return liveData
    }


    fun getStandings(id:String): LiveData<Resource<List<Standing>>> {

        viewModelScope.launch {
            liveStandingsData.postValue(Resource.loading(null))

            try {
                coroutineScope {

                    val async1 = async { userRepository.getStandings(id) }

                    val await1 = async1.await()
                    liveStandingsData.postValue(Resource.success(await1.data.standings))

                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message ?: "Error",null))
            }


        }

        return liveStandingsData
    }



    fun getSeasons(id:String): LiveData<Resource<com.example.football_app.network.seasons.Data>> {

        viewModelScope.launch {
            liveSeasonsData.postValue(Resource.loading(null))

            try {
                coroutineScope {

                    val async1 = async { userRepository.getSeasons(id) }

                    val await1 = async1.await()
                    liveSeasonsData.postValue(Resource.success(await1.data))

                }
            }catch (e:Exception){
                liveSeasonsData.postValue(Resource.error(e.message ?: "Error",null))
            }


        }

        return liveSeasonsData
    }

}