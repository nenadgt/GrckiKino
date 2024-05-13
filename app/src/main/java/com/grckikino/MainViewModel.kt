package com.grckikino

import androidx.lifecycle.ViewModel
import com.grckikino.helpers.RetrofitHelper
import com.grckikino.data.remote.RetrofitApi

open class MainViewModel() : ViewModel() {

    val retrofitApi: RetrofitApi = RetrofitHelper.getInstance().create(RetrofitApi::class.java)
}