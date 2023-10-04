package com.example.mycrm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycrm.adapter.MyProjectsAdapter
import com.example.mycrm.databinding.ActivityTasksBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TasksActivity : AppCompatActivity() {
    private lateinit var adapter: MyProjectsAdapter
    lateinit var binding: ActivityTasksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MyProjectsAdapter(this)
        binding.tasks.layoutManager = LinearLayoutManager(this)
        binding.tasks.adapter = adapter

        binding.textView.text = intent.getStringExtra("itemTitle")
        binding.textView2.text = intent.getStringExtra("itemDescr")
        binding.textView3.text = intent.getStringExtra("itemStatus")

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
            val list = mainApi.getTasks(ProjectIdDTO(intent.getStringExtra("itemId").toString()))
            runOnUiThread {
                binding.apply {
                    adapter.submitList(list)
                }
            }
        }


    }
}