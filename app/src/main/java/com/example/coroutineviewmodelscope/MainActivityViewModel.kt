package com.example.coroutineviewmodelscope

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val myJob = Job()
    private val myScope = CoroutineScope(IO + myJob)

    fun getUserData() {
        myScope.launch {
            // Some Code
        }
    }

    override fun onCleared() {
        super.onCleared()
        myJob.cancel()
    }
}