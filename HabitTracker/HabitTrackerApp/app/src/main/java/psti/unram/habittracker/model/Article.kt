package psti.unram.habittracker.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Article(
    var title: String,
    var pubDate: String,
    var description: String,
    var thumbnail : String
) : Parcelable