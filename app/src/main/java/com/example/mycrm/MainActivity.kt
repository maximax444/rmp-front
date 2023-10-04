package com.example.mycrm

import RetrofitServices
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycrm.adapter.MyProjectsAdapter
import com.example.mycrm.common.Common
import com.example.mycrm.databinding.ActivityMainBinding
import com.example.mycrm.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userPassword: EditText = findViewById(R.id.user_pass)
        val userTitle: TextView = findViewById(R.id.user_title)

//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(interceptor)
//            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val mainApi = retrofit.create(MainApi::class.java)

        binding.button.setOnClickListener {
            val readyLogin = userLogin.text.toString().trim()
            val readyPassword = userPassword.text.toString().trim()
            val readyUser = User(readyLogin, readyPassword)
            if (readyLogin == "" || readyPassword == "") {
                System.out.println("Заполните все пол2я")
                Toast.makeText(this, "Заполните все по2ля", Toast.LENGTH_LONG).show()
            } else {
                startActivity(Intent(this@MainActivity, ProjectsActivity::class.java))
//                CoroutineScope(Dispatchers.IO).launch {
//                    val user = mainApi.login(readyUser)
//                    runOnUiThread {
//                        userTitle.text = user
//                    }
//                }
//                startActivity(Intent(this@MainActivity, ProjectsActivity::class.java))
//                CoroutineScope(Dispatchers.IO).launch {
//                    val readyUser = User(readyLogin, readyPassword)
//                    val project = mainApi.login(readyUser)
//
//                    if (project != "") {
//                        startActivity(Intent(this@MainActivity, ProjectsActivity::class.java))
//
//                    }
//
//                }
            }
        }
    }
}