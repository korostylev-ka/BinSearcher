package ru.korostylev.binsearcher.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.korostylev.binsearcher.databinding.FragmentSearchHistoryBinding

@AndroidEntryPoint
class SearchHistoryFragment : Fragment() {

    private var _bindind: FragmentSearchHistoryBinding? = null
    private val binding: FragmentSearchHistoryBinding
        get() = _bindind ?: throw RuntimeException("FragmentSearchHistoryBinding is null")
    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: CardListAdapter
    private val viewModel: CardInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindind = FragmentSearchHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.listCardInfoLD.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindind = null
    }

    private fun setupRecyclerView() {
        recyclerView = binding.historyRv
        adapter = CardListAdapter()
        recyclerView?.adapter = adapter
    }

    companion object {

        fun newInstance() = SearchHistoryFragment()

    }
}