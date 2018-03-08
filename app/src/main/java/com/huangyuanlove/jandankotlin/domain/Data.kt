package com.huangyuanlove.jandankotlin.domain

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */

data class Author(var id: Int, var name: String, var nickname: String) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(name)
        writeString(nickname)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Author> = object : Parcelable.Creator<Author> {
            override fun createFromParcel(source: Parcel): Author = Author(source)
            override fun newArray(size: Int): Array<Author?> = arrayOfNulls(size)
        }
    }
}

data class customFields(var thumb_c: List<String>) : Parcelable {
    constructor(source: Parcel) : this(
            source.createStringArrayList()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeStringList(thumb_c)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<customFields> = object : Parcelable.Creator<customFields> {
            override fun createFromParcel(source: Parcel): customFields = customFields(source)
            override fun newArray(size: Int): Array<customFields?> = arrayOfNulls(size)
        }
    }
}


data class News(
        var id: Int,
        var url: String,
        var title: String,
        var excerpt: String,
        var date: Calendar,
        var comment_count: Int,
        var author: Author,
        var custom_fields: customFields


) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readSerializable() as Calendar,
            source.readInt(),
            source.readParcelable<Author>(Author::class.java.classLoader),
            source.readParcelable<customFields>(customFields::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(url)
        writeString(title)
        writeString(excerpt)
        writeSerializable(date)
        writeInt(comment_count)
        writeParcelable(author, 0)
        writeParcelable(custom_fields, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<News> = object : Parcelable.Creator<News> {
            override fun createFromParcel(source: Parcel): News = News(source)
            override fun newArray(size: Int): Array<News?> = arrayOfNulls(size)
        }
    }
}

data class NewsDetail(var status:String,var post:Post,var previous_url:String){
    data class Post(var id:Int,var content:String,var date:Calendar,var modified:Calendar)
}


data class Popular(
        var comment_ID: Int,
        var comment_author: String,
        var comment_date: Calendar,
        var vote_positive: Int,
        var vote_negative: Int,
        var sub_comment_count: String,
        var text_content: String,
        var pics: List<String>
)

data class BoredPic(
        var comment_ID: String,
        var comment_post_ID: String,
        var comment_author: String,
        var comment_date: Calendar,
        var vote_positive: String,
        var vote_negative: String,
        var sub_comment_count: String,
        var text_content: String,
        var pics: ArrayList<String>
)


data class MeiZi(
        var comment_ID: String,
        var comment_post_ID: String,
        var comment_author: String,
        var comment_date: Calendar,
        var vote_positive: String,
        var vote_negative: String,
        var sub_comment_count: String,
        var text_content: String,
        var pics: ArrayList<String>
)


data class Joke(
        var comment_ID: String,
        var comment_post_ID: String,
        var comment_author: String,
        var comment_date: Calendar,
        var vote_positive: String,
        var vote_negative: String,
        var sub_comment_count: String,
        var text_content: String
)

data class RequestResultBean<T>(var status: String, var count: Int, var comments: List<T>, var posts: List<T>)