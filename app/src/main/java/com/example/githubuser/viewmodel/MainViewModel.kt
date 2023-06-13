package com.example.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubuser.api.ApiConfig
import com.example.githubuser.response.ItemsItem
import com.example.githubuser.response.UserResponse
import com.example.githubuser.setting.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: SettingPreferences) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean>
        get() = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val list = MutableLiveData<ArrayList<ItemsItem>>()

    fun setSearchUsers(query: String){
        ApiConfig.getApiService()
            .getUsers(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    _isLoading.value = true
                    if (response.isSuccessful) {
                        list.postValue(response.body()?.items as ArrayList<ItemsItem>?)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.d("Failure", t.message.toString())
                }
            })
    }

    fun getUsers() : LiveData<ArrayList<ItemsItem>> {
        return list
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }
}