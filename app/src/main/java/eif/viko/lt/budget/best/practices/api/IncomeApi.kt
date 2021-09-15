package eif.viko.lt.budget.best.practices.api

import eif.viko.lt.budget.best.practices.data.Income
import retrofit2.http.GET

interface IncomeApi {

    companion object{
        const val BASE_URL = "https://budget-d3a7e-default-rtdb.firebaseio.com/";
    }

    @GET("incomes.json")
    suspend fun getIncomes(): List<Income>
}