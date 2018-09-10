package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.R
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.local.model.ClubFavorite
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Bayu teguh pamuji on 9/9/18.
 * email : bayuteguhpamuji@gmail.com.
 */
class ClubFavoriteAdapter(private val favorites: List<ClubFavorite>, private val listener: (ClubFavorite) -> Unit) : RecyclerView.Adapter<ClubFavoriteAdapter.ViewHolder>() {
    class ClubFavoriteUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(matchParent, wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.img_club_fav
                    }.lparams(dip(50), dip(50))
                    textView {
                        id = R.id.tv_clubname_fav
                        textSize = 16f
                    }.lparams {
                        margin = dip(15)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ClubFavoriteUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favorites[position], listener)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNameClubFav = view.find<TextView>(R.id.tv_clubname_fav)
        private val imgClubFav = view.find<ImageView>(R.id.img_club_fav)

        fun bind(favorite: ClubFavorite, listener: (ClubFavorite) -> Unit) {
            Picasso.get().load(favorite.badgeClub).into(imgClubFav)
            tvNameClubFav.text = favorite.nameClub
            itemView.onClick { listener(favorite) }
        }
    }
}