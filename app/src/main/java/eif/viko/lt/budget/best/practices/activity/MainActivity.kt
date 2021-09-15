package eif.viko.lt.budget.best.practices.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import eif.viko.lt.budget.best.practices.adapter.IncomeListAdapter
import eif.viko.lt.budget.best.practices.databinding.ActivityMainBinding
import eif.viko.lt.budget.best.practices.viewmodel.IncomeViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val incomeViewModel: IncomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val incomeListAdapter = IncomeListAdapter()

        binding.apply {
            incomeRecycleView.apply {
                adapter = incomeListAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
        incomeViewModel.incomes.observe(this@MainActivity){ incomes ->
            incomeListAdapter.swapData(incomes)
        }

    }
}