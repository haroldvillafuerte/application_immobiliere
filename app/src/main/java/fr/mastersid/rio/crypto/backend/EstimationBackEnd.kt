package fr.mastersid.rio.crypto.backend

import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession

interface EstimationBackEnd {
    fun runPrediction( input1: Float, input2: Float, input3: Float, input4: Float,
                      ortSession: OrtSession, ortEnvironment: OrtEnvironment ) : Float}