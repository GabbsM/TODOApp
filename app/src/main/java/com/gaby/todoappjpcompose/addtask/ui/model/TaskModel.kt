package com.gaby.todoappjpcompose.addtask.ui.model

import com.google.gson.annotations.SerializedName

data class TaskModel(
    val id: Long = System.currentTimeMillis(),
    @SerializedName ("task") val task:String,
    @SerializedName ("selected") var selected:Boolean = false,

)
