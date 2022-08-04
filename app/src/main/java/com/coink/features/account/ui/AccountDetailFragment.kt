package com.coink.features.account.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.coink.databinding.FragmentAccountDetailsBinding
import dagger.android.support.AndroidSupportInjection
import java.util.Calendar
import javax.inject.Inject

class AccountDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AccountDetailViewModel

    private var _binding: FragmentAccountDetailsBinding? = null
    private val binding get() = _binding!!

    private val calendar = Calendar.getInstance()
    private val calendarDay = calendar.get(Calendar.DAY_OF_MONTH)
    private val calendarYear = calendar.get(Calendar.YEAR)
    private val calendarMonth = calendar.get(Calendar.MONTH)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        binding.headerView.onBackPressed { findNavController().popBackStack() }
        binding.birthDateEditText.setOnClickListener {
            showCalendar { date ->
                binding.birthDateEditText.setText(date)
            }
        }
        binding.documentDateEditText.setOnClickListener {
            showCalendar { date ->
                binding.documentDateEditText.setText(date)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[AccountDetailViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.loader.visibility = if (loading) View.VISIBLE else View.GONE
        }
        viewModel.genders.observe(viewLifecycleOwner) { items ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                items.map { it.name }
            )
            binding.genderDropDown.setAdapter(adapter)
        }
        viewModel.documentTypes.observe(viewLifecycleOwner) { items ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                items.map { it.description }
            )
            binding.documentTypeDropDown.setAdapter(adapter)
        }
        viewModel.success.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                val action = AccountDetailFragmentDirections.toEndFragment(data)
                findNavController().navigate(action)
            }
        }
    }

    private fun showCalendar(onValue: (data: String) -> Unit) {
        val datePickerDialog =
            DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                onValue.invoke("$dayOfMonth/$month/$year")
            }, calendarYear, calendarMonth, calendarDay)
        datePickerDialog.show()
    }
}