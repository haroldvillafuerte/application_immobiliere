package fr.mastersid.rio.crypto.viewmodel

import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.mastersid.rio.crypto.backend.EstimationBackEnd
import javax.inject.Inject

private const val STATE_KEY_RESULT = "state_key_result"
@HiltViewModel
class EstimationViewModel @Inject constructor(state:SavedStateHandle,
                                              private val estimationBackEnd: EstimationBackEnd): ViewModel(){
    private val _estimation_result : MutableLiveData<Float?> = state.getLiveData(STATE_KEY_RESULT,0f)
    val estimation_result : LiveData<Float?> = _estimation_result

    fun runPrediction(input1: Float, input2: Float, input3: Float, input4: Float,
                      ortSession: OrtSession, ortEnvironment: OrtEnvironment
    ) {
        _estimation_result.value = estimationBackEnd.runPrediction( input1,input2,input3, input4 , ortSession , ortEnvironment)
    }
}