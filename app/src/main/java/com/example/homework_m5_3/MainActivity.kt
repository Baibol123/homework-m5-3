package com.example.homework_m5_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Callback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_m5_3.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val pixabayService = PixabayService()
    private var currentPage = 1
    private val imageAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.rvImages.adapter = imageAdapter

        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnSearch.setOnClickListener {
            currentPage = 1
            initClickers(binding.etSearch.text.toString())
        }

        binding.btnNext.setOnClickListener {
            currentPage++
            initClickers(binding.etSearch.text.toString())
        }

        binding.rvImages.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    initClickers(binding.etSearch.text.toString())
                }
            }
        })
    }
    private fun initClickers(query: String) {
        pixabayService.api.getImages(query = query, page = currentPage)
        .enqueue(object : Callback<PixabayModel> {
            override fun onResponse(call: Call<PixabayModel>, response: Response<PixabayModel>) {
                if (response.isSuccessful) {
                    val images = response.body()?.hits ?: emptyList()
                    if (currentPage == 1) {
                        imageAdapter.updateList(images)
                    } else {
                        imageAdapter.listImage.addAll(images)
                        imageAdapter.notifyItemRangeInserted(
                            imageAdapter.listImage.size - images.size,
                            images.size
                        )
                    }
                } else {
                    Log.e("MainActivity", "Error fetching images")
                }
            }

            override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                Log.e("MainActivity", "Error fetching images", t)
            }
        })
    }



}