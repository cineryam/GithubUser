package com.example.githubuser.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.response.ItemsItem
import com.example.githubuser.adapter.UserAdapter
import com.example.githubuser.database.FavoriteUser
import com.example.githubuser.databinding.ActivityFavoriteBinding
import com.example.githubuser.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemsItem) {
                Intent(this@FavoriteActivity, UserDetailActivity::class.java).also {
                    it.putExtra(UserDetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(UserDetailActivity.EXTRA_ID, data.id)
                    it.putExtra(UserDetailActivity.EXTRA_URL, data.avatarUrl)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvFavUser.setHasFixedSize(true)
            rvFavUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFavUser.adapter = adapter
        }

        viewModel.getFavUser()?.observe(this, {
            if (it != null) {

                val list = favList(it)

                adapter.setList(list)
            }
        })
    }

    private fun favList(users: List<FavoriteUser>): ArrayList<ItemsItem> {
        val listUsers = ArrayList<ItemsItem>()
        for (user in users){
            val userFav = ItemsItem (
                user.login,
                user.id,
                user.avatar_url
            )
            listUsers.add(userFav)
        }
        return listUsers
    }
}