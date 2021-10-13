package eif.viko.lt.budget.best.practices

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eif.viko.lt.budget.best.practices.adapter.IncomeListAdapter
import eif.viko.lt.budget.best.practices.data.Income
import eif.viko.lt.budget.best.practices.databinding.FragmentHomeBinding
import eif.viko.lt.budget.best.practices.viewmodel.IncomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment(), IncomeListAdapter.Interaction {

    private val incomeViewModel: IncomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println()

        val incomeListAdapter = IncomeListAdapter(this)

        binding.apply {
            incomeRecycleView.apply {
                adapter = incomeListAdapter
                layoutManager = LinearLayoutManager(view.context)
            }
        }


        incomeViewModel.incomes.observe(viewLifecycleOwner){ incomes ->

            println(incomes)
            incomeListAdapter.swapData(incomes)
        }

    }

    override fun clickOnIncome(income: Income) {
        Toast.makeText(context, "PASPAUDZIAU LIST ITEMA ${income.amount}", Toast.LENGTH_SHORT).show()
    }

    override fun clickOnImage(income: Income) {
        Toast.makeText(context, "PASPAUSTA ANT PAVEIKSLELIO", Toast.LENGTH_SHORT).show()
    }


}
