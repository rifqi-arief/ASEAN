package com.example.test.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.test.app.viewmodel.AppViewModel
import com.example.test.databinding.FragmentRegisterBinding
import com.example.test.domain.entity.response.RegisterRequest
import com.example.test.utils.BaseFragment
import com.example.test.utils.Dialog
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    lateinit var loading : android.app.Dialog
    private val appViewModel by viewModel<AppViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun initView(savedInstanceState: Bundle?) {
        loading = Dialog.loading(requireContext())
        initAction()
    }

    override fun initObservable() {
        appViewModel.loading.observe(viewLifecycleOwner, {
            if(it){
                showLoading()
            }else{
                dismissLoading()
            }
        })
        appViewModel.errorMessage.observe(viewLifecycleOwner, {
            showError(it)
            dismissLoading()
        })
        appViewModel.register.observe(viewLifecycleOwner, {

        })
    }

    fun showLoading(){
        if (!loading.isShowing){
            loading.show()
        }
    }

    fun dismissLoading(){
        if(loading.isShowing){
            loading.dismiss()
        }
    }

    fun showError(message : String) {
        toast(message)
    }

    fun initAction() {
        binding.btnRegisterToLogin.setOnClickListener {
            moveRegisterToLogin()
        }
        binding.btnRegister.setOnClickListener {
            doRegister()
        }
    }

    fun moveRegisterToLogin() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }

    fun doRegister() {
        val registerRequest = RegisterRequest(
            binding.etName.text.toString(),
            binding.etMail.text.toString(),
            binding.etPassword.text.toString()
        )
        appViewModel.register(registerRequest)
    }
}