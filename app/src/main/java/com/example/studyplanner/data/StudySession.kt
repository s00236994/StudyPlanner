package com.example.studyplanner.data

data class StudySession(
    val id: Int,
    val assignmentId: Int,
    val startTime: Long,
    val durationMinutes: Int,
    val distractions: Int,
    val averageLux: Float
)
