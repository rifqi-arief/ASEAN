package com.example.test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.test.databinding.FragmentRegisterBinding
import com.example.test.utils.BaseFragment

class RegisterFragment : BaseFragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!

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
        initAction()
    }

    override fun initObservable() {
    }

    fun initAction() {
        binding.btnRegisterToLogin.setOnClickListener {
            moveRegisterToLogin()
        }
    }

    fun moveRegisterToLogin() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }
}