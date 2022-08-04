package com.coink.features.phone.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.coink.R
import com.coink.databinding.FragmentPhoneNumberBinding
import com.coink.features.account.ui.safeValue
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PhoneNumberFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PhoneNumberViewModel

    private var _binding: FragmentPhoneNumberBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        binding.headerView.onBackPressed { findNavController().popBackStack() }
        binding.numberPad.setListener(
            onValidate = {
                val value = binding.editTextNumber.text?.toString().orEmpty()
                if (value.isNotEmpty()) {
                    val action = PhoneNumberFragmentDirections.toAccountDetailFragment(value)
                    findNavController().navigate(action)
                } else {
                    binding.editTextNumber.error = getString(R.string.account_detail_screen_empty_error)
                }
            },
            onTextValue = {
                binding.editTextNumber.setText(it)
            },
            initValue = viewModel.phoneNumber.safeValue()
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[PhoneNumberViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}