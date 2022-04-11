package android.kode.presentation

import android.kode.R
import android.kode.domain.models.UserModel
import android.kode.databinding.UserItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import java.util.*

/**
 *@author Moroz V.A. on 17.03.2022
 **/

class UsersFilterAdapter(private var list: ArrayList<UserModel>) :
    RecyclerView.Adapter<UsersFilterAdapter.UsersFilterHolder>(), Filterable {

    var filterList: ArrayList<UserModel>

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

    override fun onBindViewHolder(holder: UsersFilterHolder, position: Int) {
        holder.bind(filterList[position])
    }

    class UsersFilterHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userModel: UserModel) {

            val getImage = binding.avatarUrlItem

            getImage.load(userModel.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_goose)
                transformations(CircleCropTransformation())
            }

            binding.nameItem.text = userModel.firstName + " " + userModel.lastName
            binding.userTagItem.text = userModel.userTag.lowercase()
            binding.departmentItem.text = userModel.department
        }
    }

    fun setList(userList: List<UserModel>) {
        filterList.clear()
        filterList.addAll(userList)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                if (charSearch.isBlank() or charSearch.isEmpty()) {
                    filterList = list
                } else {
                    val resultList = ArrayList<UserModel>()

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
                filterList = results!!.values as ArrayList<UserModel>
                notifyDataSetChanged()
            }
        }
    }
}