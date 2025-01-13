package ru.korostylev.binsearcher.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.korostylev.binsearcher.R
import ru.korostylev.binsearcher.databinding.FragmentBinInputBinding

@AndroidEntryPoint
class BinInputFragment : Fragment() {

    private var _binding: FragmentBinInputBinding? = null

    private val binding: FragmentBinInputBinding
        get() = _binding ?: throw RuntimeException("FragmentBinInputBinding is null")

    private val viewModel: CardInfoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBinInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.binInput.addTextChangedListener(binInputWatcher)
        binding.searchButton.setOnClickListener {
            val validate = validateInput()
            if (validate) {
                viewLifecycleOwner.lifecycleScope.launch {
                    val bin = binding.binInput.text.toString().toInt()
                    viewModel.getCardInfo(bin)
                }
            } else {
                Toast.makeText(context, "66666", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        binding.historyButton.setOnClickListener {
            moveToHistoryFragment()
        }
        binding.backToSearchButton.setOnClickListener {
            moveToSearchFragment()
        }
    }

    private fun moveToHistoryFragment() {
        val fragment = SearchHistoryFragment.newInstance()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.card_info_fragment, fragment, null)
            .commit()
        binding.historyButton.visibility = View.GONE
        binding.binInput.visibility = View.GONE
        binding.searchButton.visibility = View.GONE
        binding.backToSearchButton.visibility = View.VISIBLE

    }
    private fun moveToSearchFragment() {
        val fragment = CardInfoDetailsFragment.newInstance()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.card_info_fragment, fragment, null)
            .commit()
        binding.historyButton.visibility = View.VISIBLE
        binding.binInput.visibility = View.VISIBLE
        binding.searchButton.visibility = View.VISIBLE
        binding.backToSearchButton.visibility = View.GONE
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val binInputWatcher = object: TextWatcher {
        var position = 0
        var text = EMPTY_TEXT
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            position = start
            if (position == MAX_BIN_LENGTH - 1) {
                text = s.toString()
            }
            if (position > MAX_BIN_LENGTH - 1) {
                binding.binInput.setText(text)
                binding.binInput.setSelection(text.length)
                Toast.makeText(context, R.string.max_size_bin, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    private fun validateInput(): Boolean {
        val text = binding.binInput.text.toString()
        return if (text.length < MIN_BIN_LENGTH) {
            false
        } else {
            true
        }
    }

    companion object {

        fun newInstance() = BinInputFragment()
        private const val MIN_BIN_LENGTH = 6
        private const val MAX_BIN_LENGTH = 8
        private const val EMPTY_TEXT = ""

    }
}