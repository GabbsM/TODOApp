package com.gaby.todoappjpcompose.addtask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor():ViewModel() {

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog : LiveData<Boolean> = _showDialog
}