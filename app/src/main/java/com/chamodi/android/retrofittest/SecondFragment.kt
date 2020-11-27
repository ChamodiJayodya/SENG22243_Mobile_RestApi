package com.chamodi.android.retrofittest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.chamodi.android.retrofittest.Posts.Api.PostApi
import com.chamodi.android.retrofittest.Posts.Model.PostModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var rf = Retrofit.Builder()
            .baseUrl(PostApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API = rf.create(PostApi::class.java)
        var call = API.posts

        call?.enqueue(object: Callback<List<PostModel?>?> {
            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<PostModel?>?>, response: Response<List<PostModel?>?>) {

                var postlist: List<PostModel>? = response.body() as List<PostModel>
                var post = arrayOfNulls<String>(postlist!!.size)

                for (i in postlist!!.indices)
                    post[i] = postlist!![i]!!.title

                var adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,post)
                listview.adapter =adapter

            }
        })
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}