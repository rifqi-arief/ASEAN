package com.example.test.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.app.listener.DialogInfoListener
import com.example.test.app.viewmodel.AppViewModel
import com.example.test.databinding.FragmentRegisterBinding
import com.example.test.domain.entity.response.RegisterRequest
import com.example.test.utils.BaseFragment
import com.example.test.utils.Dialog
import com.example.test.utils.Validator.isEmail
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
            dismissLoading()
            if (it.message == "success") {
                Dialog.information(
                    requireContext(),
                    requireContext().resources.getString(R.string.information),
                    it.message,
                    R.drawable.ic_thumbs_up,
                    requireContext().resources.getString(R.string.ok),
                    object : DialogInfoListener{
                        override fun onButtonClickListener() {
                        }
                    }
                )
            }
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
        Dialog.information(
            requireContext(),
            requireContext().resources.getString(R.string.warning),
            message,
            R.drawable.cancel,
            requireContext().resources.getString(R.string.ok),
            object : DialogInfoListener{
                override fun onButtonClickListener() {
                }
            }
        )
        toast(message)
    }

    fun initAction() {
        binding.btnRegisterToLogin.setOnClickListener {
            moveRegisterToLogin()
        }
        binding.btnRegister.setOnClickListener {
            if (!binding.etMail.text.toString().isEmail()){
                binding.layoutEmail.isErrorEnabled = true
                binding.layoutEmail.error = "wrong format"
                return@setOnClickListener
            }

            if (binding.etPassword.text.toString().uppercase()!!.equals(binding.etConfirmPassword.text.toString().uppercase())){
                doRegister()
            } else {
                binding.layoutConfirmPassword.isErrorEnabled = true
                binding.etConfirmPassword.error = "wrong confirm password"
            }
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