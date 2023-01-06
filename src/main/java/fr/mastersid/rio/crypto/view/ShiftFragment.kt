package fr.mastersid.rio.crypto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.rio.crypto.R
import fr.mastersid.rio.crypto.databinding.ShiftfragmentBinding

@AndroidEntryPoint
class ShiftFragment :Fragment() {
    private lateinit var binding:ShiftfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShiftfragmentBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated (view: View, savedInstanceState : Bundle?) {
        super.onViewCreated(view,savedInstanceState )
        binding.ShiftButton.setOnClickListener {
            findNavController().navigate(R.id.action_shiftFragment_to_cryptoFragment)
        }
    }
}