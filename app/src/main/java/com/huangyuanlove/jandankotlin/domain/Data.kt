package com.huangyuanlove.jandankotlin.domain

/**
 * @Describe
 * Created by huangyuan on 2018/3/6.
 */

data class Author(var id:Int,var name:String,var nickname:String)
data class customFields(var thumb_c:List<String>)

data class News(var id:Int,var url :String, var title:String,var excerpt:String,var date:String,var comment_count:Int,var author:Author,var custom_fields: customFields)