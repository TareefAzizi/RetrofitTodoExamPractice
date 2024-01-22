package com.project.retrofittodoexampractice.data.model


data class Todo(
    val _id: String? = null,
    val title: String,
    val desc: String,
    val isFinish: Boolean? = false,
    val createdAt: String? = null,
    val addedBy: String = "tareef"
)