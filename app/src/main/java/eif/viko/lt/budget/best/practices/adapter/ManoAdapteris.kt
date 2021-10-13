package eif.viko.lt.budget.best.practices.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import eif.viko.lt.budget.best.practices.R
import eif.viko.lt.budget.best.practices.data.Income

class ManoAdapteris(private val interaction: Interaction? = null) :
    ListAdapter<Income, ManoAdapteris.ManoViewHolder>(IncomeDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ManoViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.mano_itemas, parent, false), interaction
    )

    override fun onBindViewHolder(holder: ManoViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Income>) {
        submitList(data.toMutableList())
    }

    inner class ManoViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

           // Toast.makeText(v?.context, "", Toast.LENGTH_SHORT).show()


            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)
        }

        fun bind(item: Income) = with(itemView) {

            val tekstas = itemView.findViewById<TextView>(R.id.pavadinimas)
            val mygtukas = itemView.findViewById<Button>(R.id.mygtukas)
            tekstas.text = item.name
            mygtukas.text = "Labas"


            // TODO: Bind the data with View
        }
    }

    interface Interaction {

    }

    private class IncomeDC : DiffUtil.ItemCallback<Income>() {
        override fun areItemsTheSame(
            oldItem: Income,
            newItem: Income
        ): Boolean {
            TODO(
                "not implemented"
            )
        }

        override fun areContentsTheSame(
            oldItem: Income,
            newItem: Income
        ): Boolean {
            TODO(
                "not implemented"
            )
        }
    }
}