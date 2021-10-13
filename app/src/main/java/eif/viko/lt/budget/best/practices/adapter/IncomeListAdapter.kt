package eif.viko.lt.budget.best.practices.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import eif.viko.lt.budget.best.practices.R
import eif.viko.lt.budget.best.practices.data.Income
import eif.viko.lt.budget.best.practices.databinding.ActivityMainBinding
import eif.viko.lt.budget.best.practices.databinding.IncomeItemBinding

class IncomeListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Income, IncomeListAdapter.IncomeViewHolder>(IncomeDC()) {
    lateinit var binding: IncomeItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : IncomeViewHolder{
        binding = IncomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IncomeViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Income>) {
        submitList(data.toMutableList())
    }

    inner class IncomeViewHolder(
        private val binding: IncomeItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root), OnClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.image.setOnClickListener(this)
        //itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (adapterPosition == RecyclerView.NO_POSITION) return
            val clicked:Income = getItem(adapterPosition)
            when(v){
                binding.image -> interaction?.clickOnImage(clicked)
                else -> interaction?.clickOnIncome(clicked)
            }
        }

        fun bind(item: Income) = with(binding) {
            // TODO: Bind the data with View

            // TODO BIND IMAGE USING GLIDE
            Glide.with(itemView)
                .load(item.image)
                .into(image)
            name.text = item.name
            amount.text = item.amount.toString()

        }
    }

    interface Interaction {
        fun clickOnIncome(income: Income)
        fun clickOnImage(income: Income)
    }

    private class IncomeDC : DiffUtil.ItemCallback<Income>() {
        override fun areItemsTheSame(
            oldItem: Income,
            newItem: Income
        ) = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Income,
            newItem: Income
        ) = oldItem == newItem
    }
}