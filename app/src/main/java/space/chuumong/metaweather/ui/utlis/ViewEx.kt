package space.chuumong.metaweather.ui.utlis

import android.app.Activity
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide

fun Activity.showErrorDialog(message: String?) {
    AlertDialog.Builder(this).setMessage(message).setCancelable(false)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}