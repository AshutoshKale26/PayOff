package com.example.payoff

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.payoff.databinding.FragmentHistoryBinding
import com.example.payoff.databinding.FragmentHomeBinding


class fragment_history : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val view = binding.root

        val transactionList = listOf(
            Transaction( R.drawable.img1, "John Doe", "23 Oct 2024", "500", isSent = true),
            Transaction( R.drawable.img2, "Jane Smith", "22 Oct 2024", "1000", isSent = false),
            Transaction( R.drawable.img1, "John Doe", "23 Oct 2024", "500", isSent = true),
            Transaction( R.drawable.img2, "Jane Smith", "22 Oct 2024", "1000", isSent = false),
            Transaction( R.drawable.img1, "John Doe", "23 Oct 2024", "500", isSent = true),
            Transaction( R.drawable.img2, "Jane Smith", "22 Oct 2024", "1000", isSent = false),
            Transaction( R.drawable.img1, "John Doe", "23 Oct 2024", "500", isSent = true),
            Transaction( R.drawable.img2, "Jane Smith", "22 Oct 2024", "1000", isSent = false),
            Transaction( R.drawable.img1, "John Doe", "23 Oct 2024", "500", isSent = true),
            Transaction( R.drawable.img2, "Jane Smith", "22 Oct 2024", "1000", isSent = false),
            Transaction( R.drawable.img1, "John Doe", "23 Oct 2024", "500", isSent = true),
            Transaction( R.drawable.img2, "Jane Smith", "22 Oct 2024", "1000", isSent = false)
        )

        // Set up RecyclerView
        val adapter = TransactionAdapter(transactionList)
        binding.transactionRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.transactionRecyclerView.adapter = adapter

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}