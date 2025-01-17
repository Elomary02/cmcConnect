package com.example.cmcconnect.shared.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cmcconnect.databinding.FragmentProfileBinding
import com.example.cmcconnect.model.UserInInfo

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.nameTv.text = UserInInfo.name
        binding.emailTv.text = UserInInfo.email
        binding.phoneTv.text = UserInInfo.phone

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
