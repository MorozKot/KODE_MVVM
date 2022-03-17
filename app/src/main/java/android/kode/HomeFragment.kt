package android.kode

import android.kode.data.models.UsersModel
import android.kode.databinding.FragmentHomeBinding
import android.kode.presentation.Tabs.UsersAdapter
import android.kode.presentation.Tabs.UsersFilterAdapter
import android.kode.presentation.viewModels.UsersViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var usersAdapter: UsersAdapter? = null
    private var usersFilterAdapter: UsersFilterAdapter? = null
    private val usersViewModel: UsersViewModel by viewModel()
    private var searchView: SearchView? = null

    private val arrayList = ArrayList<UsersModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        searchView = binding.searchView
        searchView?.imeOptions = EditorInfo.IME_ACTION_DONE

        initRecyclerUsers()
        loadUsers()

        return binding.root
    }

    private fun initRecyclerUsers() {

        binding.recyclerViewUsers.layoutManager =
            LinearLayoutManager(context)
        usersFilterAdapter = UsersFilterAdapter(arrayList)
        binding.recyclerViewUsers.adapter = usersFilterAdapter
    }

    private fun loadUsers() {

        usersViewModel.loadUsers.observe(viewLifecycleOwner, Observer {
            usersFilterAdapter?.setList(it)

            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    usersFilterAdapter?.filter?.filter(newText)
                    return false
                }
            })

            usersFilterAdapter?.notifyDataSetChanged()
        })
    }
}