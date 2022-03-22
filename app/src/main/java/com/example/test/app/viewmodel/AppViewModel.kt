package com.example.test.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.repository.AppRepository
import com.example.test.domain.entity.response.LoginResponse
import com.example.test.domain.entity.response.ProductTable
import com.example.test.domain.entity.response.RegisterRequest
import com.example.test.domain.entity.response.RegisterResponse
import com.example.test.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AppViewModel(private val appRepository: AppRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _register = MutableLiveData<RegisterResponse>()
    val register: LiveData<RegisterResponse>
        get() = _register

    private val _login = MutableLiveData<LoginResponse>()
    val login: LiveData<LoginResponse>
        get() = _login

    private val _allProducts = MutableLiveData<List<ProductTable?>>()
    val allProducts: LiveData<List<ProductTable?>>
        get() = _allProducts


    fun register(registerRequest: RegisterRequest) = viewModelScope.launch {
        _loading.postValue(true)
        appRepository.register(registerRequest).collect { response ->
            when(response){
                is Resource.Success -> {
                    _register.postValue(response.data!!)
                }
                is Resource.Error -> {
                    _errorMessage.postValue(response.error.toString())
                }
            }
        }
    }

    fun login(email : String, password : String) = viewModelScope.launch {
        _loading.postValue(true)
        appRepository.login(email, password).collect { response ->
            when(response){
                is Resource.Success -> {
                    _login.postValue(response.data!!)
                }
                is Resource.Error -> {
                    _errorMessage.postValue(response.error.toString())
                }
            }
        }
    }

    fun getAllProducts() = viewModelScope.launch {
        _loading.postValue(true)
        appRepository.getAllProducts().collect { response ->
            when (response) {
                is Resource.Success -> {
                    _allProducts.postValue(response.data!!)
                }
                is Resource.Error -> {
                    _allProducts.postValue(response.data!!)
                    _errorMessage.postValue(response.error.toString())
                }
            }
        }
    }
}