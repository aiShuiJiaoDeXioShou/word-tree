package com.wordtree.service.entity

data class wt_user_base(val user_id:Int,val user_name:String,val user_img_url:String,val user_autograph:String,val user_update_time:String,val user_create_time:String)

data class wt_user_data(val user_id:Int,val wt_user_experience:Int,val wt_number_words:Int,val wt_word_time:String,val upate_time:String,val create_time:String)
