package com.gaby.todoappjpcompose.addtask.ui


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaby.todoappjpcompose.addtask.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor() : ViewModel() {

    //CREACION LIVE DATA PARA ENSEÑAR Y OCULTAR DIALOGO DE CREACION DE TAREA
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog


    private val _tasks = mutableStateListOf<TaskModel>()
    val task: List<TaskModel> = _tasks


    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false
        _tasks.add(TaskModel(task = task))
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBox(taskModel: TaskModel) {
        //Buscamos el index
        val index = _tasks.indexOf(taskModel)
        //Accedemos a la posicion y le indicamos que el elemento es igual pero a la inversa
        _tasks[index] = _tasks[index].let {
            it.copy(selected = !it.selected)
        }
    }

    fun onItemRemove(taskModel: TaskModel) {

        val task = _tasks.find { it.id == taskModel.id }
        _tasks.remove(task)

    }


}