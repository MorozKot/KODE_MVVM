package android.kode.presentation

import android.content.ContentValues
import android.kode.R
import android.kode.databinding.FragmentCriticalErrorBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class CriticalErrorFragment : Fragment() {

    private var _binding: FragmentCriticalErrorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCriticalErrorBinding.inflate(inflater, container, false)

        binding.tryAgainButton.setOnClickListener {

            Log.d(ContentValues.TAG, "CriticalErrorFragment setOnClickListener")

            findNavController().navigate(R.id.action_criticalErrorFragment_to_homeFragment)
        }

        return binding.root
    }
}