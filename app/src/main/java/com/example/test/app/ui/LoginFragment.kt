package com.example.test.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.test.app.viewmodel.AppViewModel
import com.example.test.databinding.FragmentLoginBinding
import com.example.test.utils.BaseFragment
import com.example.test.utils.Dialog
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var loading : android.app.Dialog
    private val appViewModel by viewModel<AppViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
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
        appViewModel.login.observe(viewLifecycleOwner, {
            if (it != null) {
                moveLoginToHome()
            }
            dismissLoading()
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
        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        binding.btnLoginToRegister.setOnClickListener {
            moveLoginToRegister()
        }
    }

    fun moveLoginToRegister() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }

    fun moveLoginToHome() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }

    fun doLogin() {
        appViewModel.login(binding.etMail.text.toString(), binding.etPassword.text.toString())
    }
}