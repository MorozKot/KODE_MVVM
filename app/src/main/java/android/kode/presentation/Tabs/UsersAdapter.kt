package android.kode.presentation.Tabs

import android.annotation.SuppressLint
import android.kode.R
import android.kode.data.models.UsersModel
import android.kode.databinding.UserItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import java.util.*
import kotlin.collections.ArrayList

/**
 *@author Moroz V.A. on 16.03.2022
 **/

class UsersAdapter() : RecyclerView.Adapter<UsersAdapter.UsersHolder>() {

    private val users = ArrayList<UsersModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = UserItemBinding.inflate(layoutInflater, parent, false)
        return UsersHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        holder.bind(users[position])
    }



    class UsersHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

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

            // idItem.text = user.id
            // lastName.text = user.lastName
            // positioni.text = user.position
            // birthday.text = user.birthday
            // phone.text = user.phone

        }
    }

    fun setList(usersList: List<UsersModel>) {
        users.clear()
        users.addAll(usersList)
    }

}