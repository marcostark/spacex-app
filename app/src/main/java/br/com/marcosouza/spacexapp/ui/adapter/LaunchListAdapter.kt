package br.com.marcosouza.spacexapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import br.com.marcosouza.spacexapp.R
import br.com.marcosouza.spacexapp.model.Launch
import br.com.marcosouza.spacexapp.util.Utils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.launch_list_item.view.*

class LaunchListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Launch>() {

        override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
            return oldItem.flightNumber == newItem.flightNumber
        }

        override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return LaunchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.launch_list_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LaunchViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Launch>) {
        differ.submitList(list)
    }

    class LaunchViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Launch) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.text_launch_title.text = item.missionName
            itemView.text_launch_site.text = item.launchSite?.siteName
            itemView.text_launch_date.text = Utils.toSimpleString(item.launchDate)
            Glide.with(itemView.context)
                .load(item.links?.missionPatchSmall)
                .into(itemView.image_launch)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Launch)
    }
}

