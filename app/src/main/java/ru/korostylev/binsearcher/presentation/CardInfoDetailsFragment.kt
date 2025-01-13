package ru.korostylev.binsearcher.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.korostylev.binsearcher.R
import ru.korostylev.binsearcher.databinding.FragmentCardInfoDetailsBinding
import ru.korostylev.binsearcher.domain.CardInfo

@AndroidEntryPoint
class CardInfoDetailsFragment : Fragment() {

    private var _binding: FragmentCardInfoDetailsBinding? = null
    private val binding: FragmentCardInfoDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentCardInfoDetailsBinding is null")
    private val viewModel: CardInfoViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardInfoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cardInfo.observe(viewLifecycleOwner) {
            bindViews(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindViews(cardInfo: CardInfo) {
        with (binding) {
            lengthValueTv.text = cardInfo.number.length?.toString() ?: resources.getString(R.string.no_data)
            luhnValueTv.text = cardInfo.number.luhn?.toString() ?: resources.getString(R.string.no_data)
            schemeValueTv.text = cardInfo.scheme ?: resources.getString(R.string.no_data)
            typeValueTv.text = cardInfo.type ?: resources.getString(R.string.no_data)
            brandValueTv.text = cardInfo.brand ?: resources.getString(R.string.no_data)
            prepaidValueTv.text = cardInfo.prepaid?.toString() ?: resources.getString(R.string.no_data)
            numericValueTv.text = cardInfo.country.numeric?.toString() ?: resources.getString(R.string.no_data)
            alpha2ValueTv.text = cardInfo.country.alpha2 ?: resources.getString(R.string.no_data)
            nameValueTv.text = cardInfo.country.name ?: resources.getString(R.string.no_data)
            emojiValueTv.text = cardInfo.country.emoji ?: resources.getString(R.string.no_data)
            currencyValueTv.text = cardInfo.country.currency ?: resources.getString(R.string.no_data)
            latitudeValueTv.text = cardInfo.country.latitude?.toString() ?: resources.getString(R.string.no_data)
            longtitudeValueTv.text = cardInfo.country.longitude?.toString() ?: resources.getString(R.string.no_data)
            bankNameValueTv.text = cardInfo.bank.name ?: resources.getString(R.string.no_data)
            urlValueTv.text = cardInfo.bank.url ?: resources.getString(R.string.no_data)
            phoneValueTv.text = cardInfo.bank.phone ?: resources.getString(R.string.no_data)
            cityValueTv.text = cardInfo.bank.city ?: resources.getString(R.string.no_data)
        }
    }

    companion object {

        fun newInstance() = CardInfoDetailsFragment()

    }
}