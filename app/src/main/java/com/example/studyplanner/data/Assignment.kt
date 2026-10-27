package com.example.studyplanner.data

data class Assignment(
    val id: Int,
    val moduleId: Int,
    val title: String,
    val dueDate: Long,
    val priority: String,
    val status: String,
    val notes: String,
    val photoPath: String?
)
