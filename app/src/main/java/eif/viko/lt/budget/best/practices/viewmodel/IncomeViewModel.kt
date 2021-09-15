package eif.viko.lt.budget.best.practices.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eif.viko.lt.budget.best.practices.api.IncomeApi
import eif.viko.lt.budget.best.practices.data.Income
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(api: IncomeApi) : ViewModel() {

    private val incomesLiveData = MutableLiveData<List<Income>>()
    val incomes = incomesLiveData

    init {
        viewModelScope.launch {
            val incomes = api.getIncomes()
            incomesLiveData.value = incomes
        }
    }

}