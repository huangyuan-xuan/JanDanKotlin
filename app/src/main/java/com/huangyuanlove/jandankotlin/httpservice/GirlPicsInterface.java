package com.huangyuanlove.jandankotlin.httpservice;


import com.huangyuanlove.jandankotlin.domain.MeiZi;
import com.huangyuanlove.jandankotlin.domain.RequestResultBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by huangyuan on 2017/8/15.
 */

public interface GirlPicsInterface {
    @GET("?oxwlxojflwblxbsapi=jandan.get_ooxx_comments")
    Call<RequestResultBean<MeiZi>> getGirlPics(@Query("page") int pageNumber);
}
