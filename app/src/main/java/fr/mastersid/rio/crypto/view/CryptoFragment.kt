package fr.mastersid.rio.crypto.view


import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.rio.crypto.R

import fr.mastersid.rio.crypto.databinding.CryptofragmentBinding
import fr.mastersid.rio.crypto.viewmodel.EstimationViewModel


var type_local = 0.0f

@AndroidEntryPoint
class CryptoFragment : Fragment() {

    private lateinit var binding: CryptofragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CryptofragmentBinding.inflate(inflater)
        return binding.root
    }
    // Create an OrtSession
    private fun createORTSession( ortEnvironment: OrtEnvironment ) : OrtSession {
        val modelBytes = resources.openRawResource( R.raw.model_immobiliere ).readBytes()
        return ortEnvironment.createSession( modelBytes )
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val estimationViewModel:EstimationViewModel by viewModels()

        binding.maison.setOnClickListener {
           type_local = 1.0f
        }
        binding.appart.setOnClickListener {
           type_local = 2.0f
        }
        // Initialize the views
        val input2 = view.findViewById<EditText>( R.id.surface_réelle )
        val input3 = view.findViewById<EditText>( R.id.nombre_piece )
        val input4 = view.findViewById<EditText>( R.id.surface_terrain )

        val outputTextView = view.findViewById<TextView>( R.id.output_textview )
        val button3 = view.findViewById<Button>( R.id.button_estimation )

        button3.setOnClickListener {
            if (type_local == 0f ){
                Toast.makeText( requireContext() , "Sélectionnez un type de logement" , Toast.LENGTH_LONG ).show()
            }
            // Parse input from inputEditText
            val inputs1 = type_local.toString().toFloatOrNull()
            val inputs2 = input2.text.toString().toFloatOrNull()
            val inputs3 = input3.text.toString().toFloatOrNull()
            val inputs4 = input4.text.toString().toFloatOrNull()

            if ( inputs1 != null && inputs2 != null && inputs3 != null && inputs4 != null) {
                val ortEnvironment = OrtEnvironment.getEnvironment()
                val ortSession = createORTSession( ortEnvironment )
                estimationViewModel.runPrediction( inputs1,inputs2,inputs3, inputs4 , ortSession , ortEnvironment )


            }
            else {
                Toast.makeText( requireContext() , "Remplissez bien tout les champs" , Toast.LENGTH_LONG ).show()
            }
        }
        estimationViewModel.estimation_result.observe(viewLifecycleOwner){
            value->
                if (type_local == 1.0f ){
                    outputTextView.text = "Estimation de la maison: ${value} €"
                }
                if (type_local == 2.0f ){
                    outputTextView.text = "Appartement estimé à: ${value} €"
            }
        }
    }

}

