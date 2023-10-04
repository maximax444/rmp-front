package com.example.mycrm

import RetrofitServices
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycrm.adapter.MyProjectsAdapter
import com.example.mycrm.common.Common
import com.example.mycrm.databinding.ActivityProjectsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectsActivity : AppCompatActivity() {
    private lateinit var adapter: MyProjectsAdapter
    lateinit var binding: ActivityProjectsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MyProjectsAdapter(this)
        binding.projectsList.layoutManager = LinearLayoutManager(this)
        binding.projectsList.adapter = adapter


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
//
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(MainApi::class.java)
//
        CoroutineScope(Dispatchers.IO).launch {
            val list = mainApi.getAllProducts()
            runOnUiThread {
                binding.apply {
                    adapter.submitList(list)
                }
            }
        }


    }
}