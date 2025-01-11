package ru.korostylev.binsearcher.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.korostylev.binsearcher.R
import ru.korostylev.binsearcher.databinding.FragmentBinInputBinding
import ru.korostylev.binsearcher.domain.CardInfo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BinInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BinInputFragment : Fragment() {

    private var _binding: FragmentBinInputBinding? = null

    private val binding: FragmentBinInputBinding
        get() = _binding ?: throw RuntimeException("FragmentBinInputBinding is null")

    private val viewModel: CardInfoViewModel by activityViewModels()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBinInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val bin = binding.binInput.text.toString().toInt()
                viewModel.getCardInfo(bin)
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = BinInputFragment()

    }
}