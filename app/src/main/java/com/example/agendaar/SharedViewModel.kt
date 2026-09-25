package com.example.agendaar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    // Variable compartida para pasar datos o estado entre fragments
    val selectedTask = MutableLiveData<String>()
}