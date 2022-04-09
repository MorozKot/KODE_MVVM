package android.kode.presentation

import android.content.ContentValues
import android.kode.R
import android.kode.databinding.FragmentCriticalErrorBinding
import android.kode.presentation.viewModels.UsersViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


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