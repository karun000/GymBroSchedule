package com.example.gymbroschedule.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Gym-Schedule")
data class Gym(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "gym-day")
    val Day: String = "",
    @ColumnInfo(name = "gym-workout")
    val Workout: String = "",

    @ColumnInfo(name = "gym-exercise1")
    val Exercise1: String = "",
    @ColumnInfo(name = "gym-exercise1R2")
    val Exercise1R2: String = "",
    @ColumnInfo(name = "gym-exercise1R3")
    val Exercise1R3: String = "",

    @ColumnInfo(name = "gym-exercise2")
    val Exercise2: String = "",
    @ColumnInfo(name = "gym-exercise2R2")
    val Exercise2R2: String = "",
    @ColumnInfo(name = "gym-exercise2R3")
    val Exercise2R3: String = "",

    @ColumnInfo(name = "gym-exercise3")
    val Exercise3: String = "",
    @ColumnInfo(name = "gym-exercise3R2")
    val Exercise3R2: String = "",
    @ColumnInfo(name = "gym-exercise3R3")
    val Exercise3R3: String = "",

    @ColumnInfo(name = "gym-exercise4")
    val Exercise4: String = "",
    @ColumnInfo(name = "gym-exercise4R2")
    val Exercise4R2: String = "",
    @ColumnInfo(name = "gym-exercise4R3")
    val Exercise4R3: String = "",

    @ColumnInfo(name = "gym-exercise5")
    val Exercise5: String = "",
    @ColumnInfo(name = "gym-exercise5R2")
    val Exercise5R2: String = "",
    @ColumnInfo(name = "gym-exercise5R3")
    val Exercise5R3: String = "",

    @ColumnInfo(name = "gym-exercise6")
    val Exercise6: String = "",
    @ColumnInfo(name = "gym-exercise6R2")
    val Exercise6R2: String = "",
    @ColumnInfo(name = "gym-exercise6R3")
    val Exercise6R3: String = "",

    @ColumnInfo(name = "gym-exercise7")
    val Exercise7: String = "",
    @ColumnInfo(name = "gym-exercise7R2")
    val Exercise7R2: String = "",
    @ColumnInfo(name = "gym-exercise7R3")
    val Exercise7R3: String = "",

    @ColumnInfo(name = "gym-exercise8")
    val Exercise8: String = "",
    @ColumnInfo(name = "gym-exercise8R2")
    val Exercise8R2: String = "",
    @ColumnInfo(name = "gym-exercise8R3")
    val Exercise8R3: String = "",

    @ColumnInfo(name = "gym-exercise9")
    val Exercise9: String = "",
    @ColumnInfo(name = "gym-exercise9R2")
    val Exercise9R2: String = "",
    @ColumnInfo(name = "gym-exercise9R3")
    val Exercise9R3: String = "",

    @ColumnInfo(name = "gym-exercise10")
    val Exercise10: String = "",
    @ColumnInfo(name = "gym-exercise10R2")
    val Exercise10R2: String = "",
    @ColumnInfo(name = "gym-exercise10R3")
    val Exercise10R3: String = "",

    @ColumnInfo(name = "gym-exercise11")
    val Exercise11: String = "",
    @ColumnInfo(name = "gym-exercise11R2")
    val Exercise11R2: String = "",
    @ColumnInfo(name = "gym-exercise11R3")
    val Exercise11R3: String = "",

    @ColumnInfo(name = "gym-exercise12")
    val Exercise12: String = "",
    @ColumnInfo(name = "gym-exercise12R2")
    val Exercise12R2: String = "",
    @ColumnInfo(name = "gym-exercise12R3")
    val Exercise12R3: String = "",

    @ColumnInfo(name = "extra-exercise")
    val ExtraExercise: String = "",
    @ColumnInfo(name = "extra1")
    val Extra1: String = "",
    @ColumnInfo(name = "extra2")
    val Extra2: String = "",
    @ColumnInfo(name = "extra3")
    val Extra3: String = "",
    @ColumnInfo(name = "extra4")
    val Extra4: String = "",
    @ColumnInfo(name = "extra5")
    val Extra5: String = ""
)
