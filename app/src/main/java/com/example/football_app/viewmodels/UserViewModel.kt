package com.example.football_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football_app.network.RetrofitClient
import com.example.football_app.network.leagueclass.LeagueResult
import com.example.football_app.repositories.UserRepository
import com.example.football_app.utils.NetworkHelper
import com.example.football_app.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository,
                    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val liveData = MutableLiveData<Resource<LeagueResult>>()

    fun getWord(): LiveData<Resource<LeagueResult>>{
        val apiService1 = RetrofitClient.apiService()

        viewModelScope.launch {
            liveData.postValue(Resource.loading(null))


            try {
                coroutineScope {

                    val async1 = async { userRepository.getRemoteUsers() }

                    val await1 = async1.await()
                    userRepository.addUsers(await1.data)
                    liveData.postValue(Resource.success(await1))

                }
            }catch (e:Exception){
                liveData.postValue(Resource.error(e.message ?: "Error",null))
            }



        }
        return liveData
    }

}