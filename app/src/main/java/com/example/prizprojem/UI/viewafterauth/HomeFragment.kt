package com.example.prizprojem.UI.viewafterauth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prizprojem.R
import com.example.prizprojem.UI.adapter.RulesAdapter
import com.example.prizprojem.UI.viewmodel.SharedHomeViewModel
import com.example.prizprojem.data.model.Rule
import com.example.prizprojem.data.network.PrizApi
import com.example.prizprojem.data.repository.PrizRepository
import com.example.prizprojem.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // kurallar listelenecek
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var  binding : FragmentHomeBinding
    private lateinit var factory : RulesViewModelFactory
    private lateinit var  viewModel: SharedHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val api = PrizApi()
        val repository = PrizRepository(api)
        factory = RulesViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(SharedHomeViewModel::class.java)

        viewModel.getRulesViewModel()

        viewModel.rules.observe(viewLifecycleOwner, Observer { rulesList->
            Log.d("deneme","calisti")
            binding.recyclerViewRules.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = RulesAdapter(rulesList)
            }
        })

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}


/*
  val rules = listOf(
            Rule(
                id = 1,
                name = "No Running in the Hallways"
            ),
            Rule(
                id = 2,
                name = "Complete Homework on Time"
            ),
            Rule(
                id = 3,
                name = "Respect Others"
            ),
            Rule(
                id = 4,
                name = "Keep Classroom Clean"
            ),
            Rule(
                id = 5,
                name = "Raise Hand Before Speaking"
            )
        )
        binding.recyclerViewRules.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = RulesAdapter(rules)
        }
 */