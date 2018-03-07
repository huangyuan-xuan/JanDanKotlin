package com.huangyuanlove.jandankotlin.domain

import java.util.*

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */

data class Author(var id: Int, var name: String, var nickname: String)

data class customFields(var thumb_c: List<String>)


data class News(
        var id: Int,
        var url: String,
        var title: String,
        var excerpt: String,
        var date: Calendar,
        var comment_count: Int,
        var author: Author,
        var custom_fields: customFields
)

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
        var pics: List<String>
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
		var pics: List<String>
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

data class RequestResultBean<T>(var status: String, var count: Int, var comments:List<T>, var posts: List<T>)