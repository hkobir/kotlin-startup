package com.example.kotlin_startup.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_startup.model.User
import com.example.kotlin_startup.repository.UserRepository
import kotlinx.coroutines.*

class UserViewModel : ViewModel() {
    val userList = MutableLiveData<List<User>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null


    fun getAllUsers(id: Int) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = UserRepository.getAllUserList(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    userList.postValue(response.body())
                    loading.value = false
                } else {
                    Log.d("Error :", " ${response.message()} ")
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}