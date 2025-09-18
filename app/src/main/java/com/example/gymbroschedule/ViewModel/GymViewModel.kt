package com.example.gymbroschedule.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymbroschedule.data.Gym
import com.example.gymbroschedule.data.GymRepository
import com.example.gymbroschedule.Graph.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GymViewModel(
    private val gymRepository: GymRepository = Graph.gymRepository
) : ViewModel() {

    var gymDayState by mutableStateOf("")
    var gymWorkoutState by mutableStateOf("")
    var Exercise1 by mutableStateOf("")
    var Exercise1R2 by mutableStateOf("")
    var Exercise1R3 by mutableStateOf("")
    var Exercise2 by mutableStateOf("")
    var Exercise2R2 by mutableStateOf("")
    var Exercise2R3 by mutableStateOf("")
    var Exercise3 by mutableStateOf("")
    var Exercise3R2 by mutableStateOf("")
    var Exercise3R3 by mutableStateOf("")
    var Exercise4 by mutableStateOf("")
    var Exercise4R2 by mutableStateOf("")
    var Exercise4R3 by mutableStateOf("")
    var Exercise5 by mutableStateOf("")
    var Exercise5R2 by mutableStateOf("")
    var Exercise5R3 by mutableStateOf("")
    var Exercise6 by mutableStateOf("")
    var Exercise6R2 by mutableStateOf("")
    var Exercise6R3 by mutableStateOf("")
    var Exercise7 by mutableStateOf("")
    var Exercise7R2 by mutableStateOf("")
    var Exercise7R3 by mutableStateOf("")
    var Exercise8 by mutableStateOf("")
    var Exercise8R2 by mutableStateOf("")
    var Exercise8R3 by mutableStateOf("")
    var Exercise9 by mutableStateOf("")
    var Exercise9R2 by mutableStateOf("")
    var Exercise9R3 by mutableStateOf("")
    var Exercise10 by mutableStateOf("")
    var Exercise10R2 by mutableStateOf("")
    var Exercise10R3 by mutableStateOf("")
    var Exercise11 by mutableStateOf("")
    var Exercise11R2 by mutableStateOf("")
    var Exercise11R3 by mutableStateOf("")
    var Exercise12 by mutableStateOf("")
    var Exercise12R2 by mutableStateOf("")
    var Exercise12R3 by mutableStateOf("")
    var ExtraExercise by mutableStateOf("")
    var Extra1 by mutableStateOf("")
    var Extra2 by mutableStateOf("")
    var Extra3 by mutableStateOf("")
    var Extra4 by mutableStateOf("")
    var Extra5 by mutableStateOf("")

    lateinit var getAllInfo: Flow<List<Gym>>

    init {
        viewModelScope.launch {
            getAllInfo = gymRepository.getAnInfo()
        }
    }

    fun onGymDayChanged(newString: String) {
        gymDayState = newString
    }

    fun onGymWorkoutChanged(newString: String) {
        gymWorkoutState = newString
    }

    fun onExercise1Changed(newString: String) {
        Exercise1 = newString
    }

    fun onExercise1R2Changed(newString: String) {
        Exercise1R2 = newString
    }

    fun onExercise1R3Changed(newString: String) {
        Exercise1R3 = newString
    }

    fun onExercise2Changed(newString: String) {
        Exercise2 = newString
    }

    fun onExercise2R2Changed(newString: String) {
        Exercise2R2 = newString
    }

    fun onExercise2R3Changed(newString: String) {
        Exercise2R3 = newString
    }

    fun onExercise3Changed(newString: String) {
        Exercise3 = newString
    }

    fun onExercise3R2Changed(newString: String) {
        Exercise3R2 = newString
    }

    fun onExercise3R3Changed(newString: String) {
        Exercise3R3 = newString
    }

    fun onExercise4Changed(newString: String) {
        Exercise4 = newString
    }

    fun onExercise4R2Changed(newString: String) {
        Exercise4R2 = newString
    }

    fun onExercise4R3Changed(newString: String) {
        Exercise4R3 = newString
    }

    fun onExercise5Changed(newString: String) {
        Exercise5 = newString
    }

    fun onExercise5R2Changed(newString: String) {
        Exercise5R2 = newString
    }

    fun onExercise5R3Changed(newString: String) {
        Exercise5R3 = newString
    }

    fun onExercise6Changed(newString: String) {
        Exercise6 = newString
    }

    fun onExercise6R2Changed(newString: String) {
        Exercise6R2 = newString
    }

    fun onExercise6R3Changed(newString: String) {
        Exercise6R3 = newString
    }

    fun onExercise7Changed(newString: String) {
        Exercise7 = newString
    }

    fun onExercise7R2Changed(newString: String) {
        Exercise7R2 = newString
    }

    fun onExercise7R3Changed(newString: String) {
        Exercise7R3 = newString
    }

    fun onExercise8Changed(newString: String) {
        Exercise8 = newString
    }

    fun onExercise8R2Changed(newString: String) {
        Exercise8R2 = newString
    }

    fun onExercise8R3Changed(newString: String) {
        Exercise8R3 = newString
    }

    fun onExercise9Changed(newString: String) {
        Exercise9 = newString
    }

    fun onExercise9R2Changed(newString: String) {
        Exercise9R2 = newString
    }

    fun onExercise9R3Changed(newString: String) {
        Exercise9R3 = newString
    }

    fun onExercise10Changed(newString: String) {
        Exercise10 = newString
    }

    fun onExercise10R2Changed(newString: String) {
        Exercise10R2 = newString
    }

    fun onExercise10R3Changed(newString: String) {
        Exercise10R3 = newString
    }

    fun onExercise11Changed(newString: String) {
        Exercise11 = newString
    }

    fun onExercise11R2Changed(newString: String) {
        Exercise11R2 = newString
    }

    fun onExercise11R3Changed(newString: String) {
        Exercise11R3 = newString
    }

    fun onExercise12Changed(newString: String) {
        Exercise12 = newString
    }

    fun onExercise12R2Changed(newString: String) {
        Exercise12R2 = newString
    }

    fun onExercise12R3Changed(newString: String) {
        Exercise12R3 = newString
    }

    fun onExtraExerciseChanged(newString: String) {
        ExtraExercise = newString
    }

    fun onExtra1Changed(newString: String) {
        Extra1 = newString
    }

    fun onExtra2Changed(newString: String) {
        Extra2 = newString
    }

    fun onExtra3Changed(newString: String) {
        Extra3 = newString
    }

    fun onExtra4Changed(newString: String) {
        Extra4 = newString
    }

    fun onExtra5Changed(newString: String) {
        Extra5 = newString
    }

    fun addAnInfo(gym: Gym) {
        viewModelScope.launch(Dispatchers.IO) {
            gymRepository.addAnInfo(gym = gym)
        }
    }

    fun updateAnInfo(gym: Gym) {
        viewModelScope.launch(Dispatchers.IO) {
            gymRepository.updateANInfo(gym = gym)
        }
    }

    fun getAnInfoById(id: Long): Flow<Gym> {
        return gymRepository.getAnInfoById(id)
    }

    fun deleteAnInfo(gym: Gym) {
        viewModelScope.launch(Dispatchers.IO) {
            gymRepository.deleteAnInfo(gym = gym)
        }
    }
}
