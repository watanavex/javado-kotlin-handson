package jp.watanave.githubsample.util

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.setImageSource(url: String?) {
    Picasso.get().load(url).into(this)
}