package com.example.demo_movehomepage

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.demo_movehomepage.Category.Category
import com.example.demo_movehomepage.Category.CategoryAdapter
import com.example.demo_movehomepage.Feature.FeatureFragment1
import com.example.demo_movehomepage.Feature.ViewPager2Adapter
import com.example.demo_movehomepage.Video.User
import com.example.demo_movehomepage.Video.Video
import com.example.demo_movehomepage.Video.VideoAdapter
import com.example.demo_movehomepage.databinding.ActivityMainBinding
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2Adapter: ViewPager2Adapter
    private lateinit var handler: Handler
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var videoAdapter: VideoAdapter

    var listVideo: MutableList<Video> = mutableListOf()
    val listCategory: MutableList<Category> = mutableListOf()
    var fragmentList: ArrayList<FeatureFragment1> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        hideSystemUI()
        setViewPagerAdapter()
        setUpTransformer()
        setUpCategory()
        setUpVideoMayLike()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)
            }
        })

    }

    private fun setUpVideoMayLike() {
        getVideoMaylikeData()

        videoAdapter = VideoAdapter(listVideo)
        binding.listVideoMaylike.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = videoAdapter
        }

    }

    private fun getVideoMaylikeData() {
        for (i in 1..6){
            listVideo.add(Video(1, R.drawable.img_feature1,
                "Leg days",
                "Just Move",
                User(1,R.drawable.avatar,"Nguyen Dung",true),
                "30 mins",
                "15:00",
                "2 days ago",
                "12.5k",
                3.5
            ))
        }


    }


    private fun setViewPagerAdapter() {

        for (i in 1..4) {
            fragmentList.add(FeatureFragment1())
        }

        handler = Handler(Looper.myLooper()!!)
        viewPager2Adapter = ViewPager2Adapter(fragmentList, binding.viewPager)

        binding.viewPager.apply {
            adapter = viewPager2Adapter
            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

    }

    private val runnable = Runnable {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))

        // do mình thực hiện xoay màn hình nên cần lấy height chứ ko phải width
        val screenHeight = resources.displayMetrics.widthPixels
        val nextItemTranslationX = 5f * screenHeight / 60

        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleX = 0.85f + r * 0.1f
            // translation X
            page.translationX = -position * nextItemTranslationX
        }
        binding.viewPager.setPageTransformer(transformer)
    }


    private fun setUpCategory() {
        getDataCategory()
        categoryAdapter = CategoryAdapter(listCategory)

        binding.listCategory.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }


    fun getDataCategory() {
        listCategory.add(Category(1, R.drawable.img_category1, "MMA", "10.2k views"))
        listCategory.add(Category(2, R.drawable.img_category2, "HIIT", "9.2k views"))
        listCategory.add(Category(3, R.drawable.img_category3, "Just Move", "8.2k views"))
        listCategory.add(Category(4, R.drawable.img_category1, "MMA", "10.2k views"))
        listCategory.add(Category(5, R.drawable.img_category2, "HIIT", "9.2k views"))
        listCategory.add(Category(6, R.drawable.img_category3, "Just Move", "8.2k views"))
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(
            window,
            window.decorView.findViewById(android.R.id.content)
        ).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())

            // When the screen is swiped up at the bottom
            // of the application, the navigationBar shall
            // appear for some time
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }


    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 3000)
    }

}