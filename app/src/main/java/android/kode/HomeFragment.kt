package android.kode

import android.kode.databinding.FragmentHomeBinding
import android.kode.databinding.UserItemBinding
import android.kode.presentation.Tabs.UsersAdapter
import android.kode.presentation.viewModels.UsersViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var usersAdapter: UsersAdapter? = null
    private val usersViewModel: UsersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initRecyclerUsers()
        loadUsers()

        return binding.root

    }


    private fun initRecyclerUsers() {

        binding.recyclerViewUsers.layoutManager =
            LinearLayoutManager(context)
        usersAdapter = UsersAdapter()
        binding.recyclerViewUsers.adapter = usersAdapter

    }


    private fun loadUsers() {
        usersViewModel.loadUsers.observe(viewLifecycleOwner, Observer {
            usersAdapter?.setList(it)
            usersAdapter?.notifyDataSetChanged()
        })
    }
}