package com.example.cmcconnect.formateur.formateurSeeRequests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmcconnect.adapters.teacherAdapters.SeeRequestsAdapter
import com.example.cmcconnect.databinding.FormateurFragmentSeeRequestsBinding
import com.example.cmcconnect.model.UserInInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeRequestsFragment : Fragment() {
    private val seeRequestsViewModel: SeeRequestsViewModel by viewModels()
    private var _binding: FormateurFragmentSeeRequestsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FormateurFragmentSeeRequestsBinding.inflate(inflater, container, false)
        val view = binding.root

        val requestsRecyclerView: RecyclerView = binding.rvRequests
        requestsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val requestsAdapter = SeeRequestsAdapter(findNavController())
        requestsRecyclerView.adapter = requestsAdapter

        seeRequestsViewModel.requestsForTeacherLiveData.observe(viewLifecycleOwner) { requestWithStudents ->
            requestWithStudents?.let {
                requestsAdapter.submitList(it)
            }
        }

        // Load data
        seeRequestsViewModel.loadRequestsForTeacher(UserInInfo.id)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


