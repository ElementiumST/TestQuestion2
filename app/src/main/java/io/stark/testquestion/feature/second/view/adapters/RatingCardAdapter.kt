package io.stark.testquestion.feature.second.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import io.stark.testquestion.R
import io.stark.testquestion.databinding.ItemRatingCardBinding
import io.stark.testquestion.domain.models.PersonDomainBean
import io.stark.testquestion.feature.dimension.toPx

class RatingCardAdapter : Adapter<RatingCardAdapter.RatingCardViewHolder>() {

    private var currentList: List<PersonDomainBean> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =  ItemRatingCardBinding.inflate(inflater).root
        return RatingCardViewHolder(view)
    }

    fun updateRatingCardList(list :  List<PersonDomainBean>) {

        currentList = list
        notifyItemRangeChanged(0, list.size)
    }
    override fun onBindViewHolder(holder: RatingCardViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class RatingCardViewHolder(itemView: View) : ViewHolder(itemView) {
        private val binding = ItemRatingCardBinding.bind(itemView)

        fun bind(personDomainBean: PersonDomainBean) {
            binding.text.text = personDomainBean.title
            Glide.with(binding.root.context).load(personDomainBean.image).into(binding.image)

            binding.ratingContainer.removeAllViews()
            for (i in 0 until personDomainBean.rating) {
                binding.ratingContainer.apply {
                    addView(ImageView(context).apply {
                        setImageResource(R.drawable.ic_star)
                        layoutParams = LinearLayout.LayoutParams(24.toPx.toInt(), 24.toPx.toInt())
                    })
                }
            }
        }
    }
}