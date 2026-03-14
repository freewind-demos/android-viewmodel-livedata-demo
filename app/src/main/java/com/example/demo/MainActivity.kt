package com.example.demo

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // ViewModel 实例
    private lateinit var viewModel: CounterViewModel

    private lateinit var textViewCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 获取 ViewModel
        viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        textViewCount = findViewById(R.id.textViewCount)

        // 观察 LiveData 数据变化
        // 当数据变化时，会自动更新 UI
        viewModel.count.observe(this) { count ->
            textViewCount.text = "计数: $count"
        }

        // 增加按钮
        findViewById<Button>(R.id.buttonIncrement).setOnClickListener {
            viewModel.increment()
        }

        // 减少按钮
        findViewById<Button>(R.id.buttonDecrement).setOnClickListener {
            viewModel.decrement()
        }
    }
}
