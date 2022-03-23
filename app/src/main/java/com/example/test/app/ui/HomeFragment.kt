package com.example.test.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.app.adapter.ProductAdapter
import com.example.test.app.viewmodel.AppViewModel
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.domain.entity.response.ProductTable
import com.example.test.utils.BaseFragment
import com.example.test.utils.Dialog
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var loading : android.app.Dialog
    private val appViewModel by viewModel<AppViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun initView(savedInstanceState: Bundle?) {
        loading = Dialog.loading(requireContext())
        getAllProduct()
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
        appViewModel.allProducts.observe(viewLifecycleOwner, {
            if (it != null && !it.isNullOrEmpty()){
                setProductRecyclerView(it as List<ProductTable>)
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

    fun getAllProduct() {
        appViewModel.getAllProducts()
    }

    fun setProductRecyclerView(product : List<ProductTable>){
        val productAdapter = ProductAdapter(product){
            if (it != null) {
                toast(it.name)
            }
        }

        with(binding.rvProduct){
            setHasFixedSize(true)
            adapter = productAdapter
        }
    }
}