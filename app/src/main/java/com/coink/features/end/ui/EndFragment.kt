package com.coink.features.end.ui

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.coink.R
import com.coink.databinding.FragmentEndBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection

class EndFragment : Fragment() {

    private var _binding: FragmentEndBinding? = null
    private val binding get() = _binding!!

    private val gifDialog: AlertDialog by lazy { AlertDialog.Builder(requireActivity()).create() }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEndBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.headerView.onBackPressed { findNavController().popBackStack() }
        binding.buttonAccept.setOnClickListener {
            if (binding.checkboxTerms.isChecked) {
                val args: EndFragmentArgs by navArgs()
                print(args.accountData.toString())
                showDialog()
            } else {
                Snackbar.make(
                    binding.container,
                    getString(R.string.end_screen_terms_and_conditions_error),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showDialog() {
        val dialogView = layoutInflater.inflate(R.layout.item_gif, null)
        dialogView.findViewById<MaterialButton>(R.id.button_continue).setOnClickListener {
            val action = EndFragmentDirections.actionEndFragmentToOnboadingFragment()
            findNavController().navigate(action)
            gifDialog.dismiss()
        }
        gifDialog.setCancelable(false)
        gifDialog.setView(dialogView)
        gifDialog.show()
    }
}