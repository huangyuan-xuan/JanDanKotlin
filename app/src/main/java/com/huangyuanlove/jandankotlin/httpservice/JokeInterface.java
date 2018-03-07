package com.huangyuanlove.jandankotlin.httpservice;



import com.huangyuanlove.jandankotlin.domain.Joke;
import com.huangyuanlove.jandankotlin.domain.RequestResultBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface JokeInterface {
    @GET("?oxwlxojflwblxbsapi=jandan.get_duan_comments")
    Call<RequestResultBean<Joke>> getJokes(@Query("page") int pageNumber);

}
