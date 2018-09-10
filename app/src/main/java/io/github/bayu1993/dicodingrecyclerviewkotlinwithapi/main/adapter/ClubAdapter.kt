package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.repository.model.Club
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Bayu teguh pamuji on 9/1/18.
 * email : bayuteguhpamuji@gmail.com.
 */
class ClubAdapter(private val clubs: List<Club>, private val listener: (Club) -> Unit) : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {
    class UIClub : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(matchParent, wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.club_badge
                    }.lparams(50, 50)
                    textView {
                        id = R.id.tv_name
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubAdapter.ViewHolder {
        return ViewHolder(UIClub().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return clubs.size
    }

    override fun onBindViewHolder(holder: ClubAdapter.ViewHolder, position: Int) {
        holder.bind(clubs[position], listener)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val clubBadge = view.find<ImageView>(R.id.club_badge)
        private val clubName = view.find<TextView>(R.id.tv_name)
        fun bind(club: Club, listener: (Club) -> Unit) {
            Picasso.get().load(club.badgeClub).into(clubBadge)
            clubName.text = club.nameClub
            itemView.onClick {
                listener(club)
            }
        }
    }
}