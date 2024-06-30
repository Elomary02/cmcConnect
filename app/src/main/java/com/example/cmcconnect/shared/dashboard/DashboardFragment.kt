package com.example.cmcconnect.shared.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cmcconnect.R
import com.example.cmcconnect.adapters.sharedAdapters.DashboardEventsAdapter
import com.example.cmcconnect.adapters.sharedAdapters.DashboardResourcesAdapter
import com.example.cmcconnect.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val dashboardViewModel: DashboardViewModel by viewModels()

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root

        val seeAllResources: TextView = binding.tvVoirToutRessources
        seeAllResources.setOnClickListener {
            findNavController().navigate(R.id.id_resourcesFragment)
        }

        val resourcesRecyclerView: RecyclerView = binding.recyclerViewRessources
        resourcesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val resourcesAdapter = DashboardResourcesAdapter()
        resourcesRecyclerView.adapter = resourcesAdapter

        val intent: Intent = Intent()
        val userId = intent.getIntExtra("user_id", -1)

        dashboardViewModel.studentResources.observe(viewLifecycleOwner) {
            recentResource -> if (recentResource != null) {
                resourcesAdapter.submitList(recentResource)
            }
            Log.d("recent resources","$recentResource")
        }

        dashboardViewModel.getStudentResources(userId)

        val seeAllEvents: TextView = binding.tvVoirToutEvents
        seeAllEvents.setOnClickListener {
            findNavController().navigate(R.id.id_eventsFragment)
        }

        val eventsRecyclerView: RecyclerView = binding.recyclerViewEvents
        eventsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val eventsAdapter = DashboardEventsAdapter()
        eventsRecyclerView.adapter = eventsAdapter

        dashboardViewModel.recentEventsLiveData.observe(viewLifecycleOwner) {
            recentEvents ->
                if (recentEvents != null) {
                    eventsAdapter.submitList(recentEvents)
                }
            Log.d("recent events","$recentEvents")
        }

        dashboardViewModel.getRecentEvents()

        return view
    }

}