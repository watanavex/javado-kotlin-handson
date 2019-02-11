package jp.watanave.githubsample.util

import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.watanave.githubsample.BuildConfig
import jp.watanave.githubsample.R

fun ImageView.setImageSource(url: String?) {
    when {
        BuildConfig.FLAVOR.startsWith("stub") -> {
            this.setImageResource(R.drawable.ic_launcher_background)
        }
        else -> {
            Picasso.get().load(url).into(this)
        }
    }
}