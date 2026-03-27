package com.example.ledgefy.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ledgefy.R
import com.example.ledgefy.adapters.ReportAdapter
import com.example.ledgefy.viewmodel.TransactionViewModel

class TransactionListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReportAdapter
    private val transactionViewModel: TransactionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transaction_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_quick_view)
        adapter = ReportAdapter(mutableListOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        transactionViewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            val latestTransactions = transactions.takeLast(3).reversed()
            adapter.updateList(latestTransactions)
        }

        return view
    }
}
