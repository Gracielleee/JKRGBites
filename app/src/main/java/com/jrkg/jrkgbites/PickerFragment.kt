package com.jrkg.jrkgbites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jrkg.jrkgbites.databinding.FragmentPickerBinding

class PickerFragment : Fragment() {

    private var _binding: FragmentPickerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This is where your teammates will eventually add
        // the Popular Restaurants and Near You logic.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}