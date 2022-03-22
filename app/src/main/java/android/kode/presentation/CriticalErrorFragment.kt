package android.kode.presentation

import android.kode.databinding.FragmentCriticalErrorBinding
import android.kode.presentation.viewModels.UsersViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class CriticalErrorFragment : Fragment() {

    private var _binding: FragmentCriticalErrorBinding? = null
    private val binding get() = _binding!!
    val usersViewModel: UsersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCriticalErrorBinding.inflate(inflater, container, false)

        val context = requireContext()

        binding.tryAgainButton.setOnClickListener {

            usersViewModel.migration(context)
        }

        return binding.root
    }
}