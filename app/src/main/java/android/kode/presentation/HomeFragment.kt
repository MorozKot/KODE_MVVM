package android.kode.presentation

import android.content.ContentValues
import android.content.res.ColorStateList
import android.kode.R
import android.kode.data.models.UsersModel
import android.kode.databinding.FragmentHomeBinding
import android.kode.presentation.Tabs.UsersFilterAdapter
import android.kode.presentation.viewModels.UsersViewModel
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var usersFilterAdapter: UsersFilterAdapter? = null
    private val usersViewModel: UsersViewModel by viewModel()
    private var searchView: SearchView? = null
    private val arrayList = ArrayList<UsersModel>()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        searchView = binding.searchView
        searchView?.imeOptions = EditorInfo.IME_ACTION_DONE

        context?.let { usersViewModel.getUsers(it) }

        observeViewModelFields(usersViewModel)

        binding.cancelButton.setOnClickListener {

            searchView?.setQuery("", false)
            searchView?.clearFocus()

            binding.searchButton.backgroundTintList = ColorStateList.valueOf(
                resources.getColor(
                    R.color.grayIcons,
                    context?.theme
                )
            )

            binding.filterButton.visibility = View.VISIBLE

            val paramsLinearLayout = binding.linearLayoutCancel.layoutParams

            paramsLinearLayout?.width = 0
        }

        initRecyclerUsers()
        loadUsers()

        return binding.root
    }

    private fun observeViewModelFields(usersViewModel: UsersViewModel) {

        lifecycleScope.launch {
            usersViewModel.screenLoadingState.collect { result ->
                when (result) {

                    ScreenStates.Success -> {
                        Log.d(ContentValues.TAG, "HomeFragment   ScreenStates.Success")
                    }

                    ScreenStates.CriticalError -> {
                        Log.d(ContentValues.TAG, "HomeFragment  ScreenStates.CriticalError")
                        findNavController()
                            .navigate(R.id.action_homeFragment_to_criticalErrorFragment)

                    }
                }
            }
        }
    }

    private fun loadUsers() {

        usersViewModel.loadUsers.observe(viewLifecycleOwner, Observer {

            usersFilterAdapter?.setList(it)

            searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                @RequiresApi(Build.VERSION_CODES.M)
                override fun onQueryTextChange(newText: String?): Boolean {
                    usersFilterAdapter?.filter?.filter(newText)

                    binding.searchButton.backgroundTintList = ColorStateList.valueOf(
                        resources.getColor(
                            R.color.darkText, context?.theme
                        )
                    )

                    binding.filterButton.visibility = View.GONE

                    val paramsLinearLayout = binding.linearLayoutCancel.layoutParams

                    paramsLinearLayout?.width = 240

                    return false
                }
            })

            usersFilterAdapter?.notifyDataSetChanged()
        })
    }

    private fun initRecyclerUsers() {

        binding.recyclerViewUsers.layoutManager =
            LinearLayoutManager(context)
        usersFilterAdapter = UsersFilterAdapter(arrayList)
        binding.recyclerViewUsers.adapter = usersFilterAdapter
    }
}