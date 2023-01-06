package fr.mastersid.rio.crypto.backend

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import java.nio.FloatBuffer
import javax.inject.Inject

class EstimationBackEndImpl @Inject constructor() : EstimationBackEnd {
    // Make predictions with given inputs
    override fun runPrediction(input1: Float, input2: Float, input3: Float, input4: Float,
                              ortSession: OrtSession, ortEnvironment: OrtEnvironment
    ) : Float {
        // Get the name of the input node
        val inputName = ortSession.inputNames?.iterator()?.next()
        // Make a FloatBuffer of the inputs
        val floatBufferInputs = FloatBuffer.wrap( floatArrayOf( input1,input2,input3,input4 ) )
        // Create input tensor with floatBufferInputs of shape ( 1 , 4 )
        val inputTensor = OnnxTensor.createTensor( ortEnvironment , floatBufferInputs , longArrayOf( 1, 4 ) )
        // Run the model
        val results = ortSession.run( mapOf( inputName to inputTensor ) )
        // Fetch and return the results
        val output = results[0].value as Array<FloatArray>
        return output[0][0]
    }
}