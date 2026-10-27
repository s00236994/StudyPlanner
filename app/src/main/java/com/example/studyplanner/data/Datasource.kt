package com.example.studyplanner.data

class Datasource {

    private val now = System.currentTimeMillis()
    private val oneDay = 86_400_000L

    fun loadModules(): List<Module> {
        return listOf(
            Module(id = 1, code = "MAD", name = "Mobile App Development"),
            Module(id = 2, code = "WP2", name = "Web Programming 2"),
            Module(id = 3, code = "P300", name = "Project 300"),
            Module(id = 4, code = "PD", name = "Professional Development"),
            Module(id = 5, code = "SPM", name = "Software Project Management"),
            Module(id = 6, code = "RAD", name = "Rich App Development")
        )
    }

    fun loadAssignments(): List<Assignment> {
        return listOf(
            Assignment(
                id = 1,
                moduleId = 2,
                title = "Lab 3 worksheet",
                dueDate = now - 2 * oneDay,
                priority = "High",
                status = "To do",
                notes = "",
                photoPath = null
            ),
            Assignment(
                id = 2,
                moduleId = 4,
                title = "Research report",
                dueDate = now + 2 * oneDay,
                priority = "Medium",
                status = "In progress",
                notes = "Group report, my section is the intro",
                photoPath = null
            ),
            Assignment(
                id = 3,
                moduleId = 3,
                title = "Sprint 2 demo",
                dueDate = now + 3 * oneDay,
                priority = "High",
                status = "To do",
                notes = "",
                photoPath = null
            ),
            Assignment(
                id = 4,
                moduleId = 1,
                title = "Kotlin practice",
                dueDate = now + 5 * oneDay,
                priority = "Low",
                status = "To do",
                notes = "",
                photoPath = null
            ),
            Assignment(
                id = 5,
                moduleId = 5,
                title = "Project charter",
                dueDate = now + 10 * oneDay,
                priority = "Medium",
                status = "To do",
                notes = "",
                photoPath = null
            ),
            Assignment(
                id = 6,
                moduleId = 1,
                title = "Mobile App Project",
                dueDate = now + 37 * oneDay,
                priority = "High",
                status = "In progress",
                notes = "Report 25 to 30 pages, video max 8 mins",
                photoPath = null
            )
        )
    }
}