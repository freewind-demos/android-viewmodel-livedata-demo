package com.example.demo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * ViewModel - 管理 UI 相关的数据
 *
 * ViewModel 的特点：
 * 1. 生命周期比 Activity 长，可以在配置变化（如屏幕旋转）后继续存在
 * 2. 不持有 UI 组件的引用，避免内存泄漏
 * 3. 在后台线程处理数据，LiveData 自动将数据切换到主线程
 */
class CounterViewModel : ViewModel() {

    // MutableLiveData - 可变的 LiveData
    // LiveData - 可观察的数据容器，当数据变化时会通知观察者
    private val _count = MutableLiveData<Int>()

    // 对外暴露不可变的 LiveData
    val count: LiveData<Int> = _count

    // 初始化数据
    init {
        _count.value = 0
    }

    // 增加计数
    fun increment() {
        val current = _count.value ?: 0
        _count.value = current + 1
    }

    // 减少计数
    fun decrement() {
        val current = _count.value ?: 0
        _count.value = current - 1
    }
}
