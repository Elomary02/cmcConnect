package com.example.cmcconnect.student.studentRequest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cmcconnect.databinding.FragmentRequestsBinding
import com.example.cmcconnect.model.UserInInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestsFragment : Fragment() {
    private val requestsViewModel: RequestViewModel by viewModels()
    private var _binding: FragmentRequestsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestsBinding.inflate(inflater, container, false)
        val view = binding.root

        val requestsSpinner: Spinner = binding.spinnerRequests
        val teachersSpinner: Spinner = binding.spinnerTeachers

        requestsViewModel.allRequestsLiveData.observe(viewLifecycleOwner) { requests ->
            val requestsNames = requests.map { it.name }
            val requestSpinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, requestsNames)
            requestSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            requestsSpinner.adapter = requestSpinnerAdapter

            requestsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (requests[position].id != 1) {
                        teachersSpinner.isEnabled = false
                    } else {
                        teachersSpinner.isEnabled = true
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
        }

        requestsViewModel.teachersForStudent.observe(viewLifecycleOwner) { teachers ->
            val teacherNames = teachers.map { it.name }
            val teacherSpinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, teacherNames)
            teacherSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            teachersSpinner.adapter = teacherSpinnerAdapter
        }

        val idStudent = UserInInfo.id
        requestsViewModel.loadTeachersForStudent(idStudent)
        requestsViewModel.getStudentAdmin(idStudent)
        requestsViewModel.loadRequests()

        binding.requestButton.setOnClickListener {
            val motif = binding.requestMotifEditText.text.toString()
            val selectedRequestType = requestsSpinner.selectedItemPosition + 1
            val selectedTeacherId = if (teachersSpinner.isEnabled) {
                teachersSpinner.selectedItemPosition + 1
            } else {
                null
            }

            var selectedAdminId: Int? = null
            requestsViewModel.adminForStudent.value?.let { admin ->
                selectedAdminId = admin.id
            }

            if (motif.isNotEmpty()) {
                requestsViewModel.sendRequest(motif, idStudent, selectedRequestType, selectedTeacherId, selectedAdminId)
            } else {
                Log.d("Empty motif", "Make sure to fill motif edit text")
            }
        }

        requestsViewModel.sendRequestStatus.observe(viewLifecycleOwner) { success ->
            if (success) {
                Log.d("Success","Posted request")
            } else {
                Log.d("Failure", "Didn't post request")
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
