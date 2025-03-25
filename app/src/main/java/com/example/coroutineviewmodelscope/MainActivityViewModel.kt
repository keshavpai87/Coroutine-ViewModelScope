package com.example.coroutineviewmodelscope

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutineviewmodelscope.models.User
import com.example.coroutineviewmodelscope.models.UserRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {

    private var userRepository = UserRepository()
    var users : MutableLiveData<List<User>?> = MutableLiveData()

    fun getUserData() {
        viewModelScope.launch {
            var result : List<User>? = null
            withContext(IO) {
                result = userRepository.getUsers()
            }
            users.value = result
        }
    }
}