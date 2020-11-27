package com.chamodi.android.retrofittest.Posts.Api

import com.chamodi.android.retrofittest.Posts.Model.PostModel
import retrofit2.http.GET
import retrofit2.Call

interface PostApi {
    @get:GET("posts")
    val posts : Call<List<PostModel?>?>?

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}