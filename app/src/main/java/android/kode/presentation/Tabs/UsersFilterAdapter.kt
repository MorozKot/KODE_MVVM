package android.kode.presentation.Tabs

import android.kode.R
import android.kode.data.models.UsersModel
import android.kode.databinding.UserItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation

import java.util.*
import kotlin.collections.ArrayList

/**
 *@author Moroz V.A. on 17.03.2022
 **/

class UsersFilterAdapter(private var list: ArrayList<UsersModel>) :
    RecyclerView.Adapter<UsersFilterAdapter.UsersFilterHolder>(), Filterable {

    var filterList: ArrayList<UsersModel>

    init {
        this.filterList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersFilterHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserItemBinding.inflate(layoutInflater, parent, false)
        return UsersFilterHolder(binding)
    }

    override fun getItemCount(): Int {
        return filterList.size
    }

    override fun onBindViewHolder(holder: UsersFilterAdapter.UsersFilterHolder, position: Int) {
        holder.bind(filterList[position])
    }

    class UsersFilterHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(usersModel: UsersModel) {

            val getImage = binding.avatarUrlItem

            getImage.load(usersModel.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_goose)
                transformations(CircleCropTransformation())
            }

            binding.nameItem.text = usersModel.firstName + " " + usersModel.lastName
            binding.userTagItem.text = usersModel.userTag.lowercase()
            binding.departmentItem.text = usersModel.department
        }
    }

    fun setList(usersList: List<UsersModel>) {
        filterList.clear()
        filterList.addAll(usersList)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isBlank() or charSearch.isEmpty()) {
                    filterList = list
                } else {
                    val resultList = ArrayList<UsersModel>()

                    for (row in list) {

                        val firstNameFromList = row.firstName.lowercase(Locale.ROOT)
                        val lastNameFromList = row.lastName.lowercase(Locale.ROOT)
                        val userTagFromList = row.userTag.lowercase(Locale.ROOT)

                        if (firstNameFromList.contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        } else if (lastNameFromList.contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        } else if (userTagFromList.contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results!!.values as ArrayList<UsersModel>
                notifyDataSetChanged()
            }
        }
    }
}