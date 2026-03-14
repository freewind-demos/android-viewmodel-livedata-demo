# android-viewmodel-livedata-demo

## 简介

本 demo 展示 Android ViewModel 和 LiveData 的基本用法，演示如何实现数据和 UI 的分离，以及数据的自动更新。

## 基本原理

ViewModel 和 LiveData 是 Android Architecture Components 的一部分，用于实现 MVVM 架构模式。

**ViewModel**：
- 管理 UI 相关的数据
- 生命周期比 Activity 长
- 在配置变化（如屏幕旋转）后仍然存在
- 不持有 UI 组件的引用

**LiveData**：
- 可观察的数据容器
- 感知生命周期，只在活动状态下更新 UI
- 数据变化时自动通知观察者

## 启动和使用

### 环境要求
- Android Studio 3.0+
- JDK 1.8+
- Android SDK 28

### 安装和运行
1. 用 Android Studio 打开此项目
2. 连接 Android 设备或启动模拟器
3. 点击 Run 运行项目

## 教程

### 什么是 ViewModel？

ViewModel 是 UI 相关数据的存储库，它在配置变化（如屏幕旋转）后仍然存在。这样可以确保数据不会因 Activity 重建而丢失。

```kotlin
class CounterViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()

    val count: LiveData<Int> = _count

    init {
        _count.value = 0
    }

    fun increment() {
        _count.value = (_count.value ?: 0) + 1
    }
}
```

### 什么是 LiveData？

LiveData 是一种可观察的数据持有者，与普通的观察者不同，LiveData 是生命周期感知的，只有在 Activity/Fragment 处于活跃状态时才会通知数据变化。

```kotlin
// 在 Activity 中观察 LiveData
viewModel.count.observe(this) { count ->
    textView.text = "计数: $count"
}
```

### 获取 ViewModel

```kotlin
// 方式1：ViewModelProviders
val viewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

// 方式2：Kotlin 扩展（需要 kotlin-lazy）
val viewModel: CounterViewModel by viewModels()
```

### LiveData 的类型

1. **MutableLiveData**：可变的 LiveData，可以设置值
2. **LiveData**：不可变的 LiveData，只能观察

```kotlin
private val _count = MutableLiveData<Int>()  // 可变
val count: LiveData<Int> = _count             // 不可变，对外暴露
```

### 数据更新

```kotlin
// 更新数据
_count.value = newValue

// 在后台线程更新（会自动切换到主线程）
_count.postValue(newValue)
```

### 观察者模式

```kotlin
// 观察数据变化
viewModel.count.observe(this) { value ->
    // UI 更新逻辑
}

// 只观察一次
viewModel.count.observeOnce(this) { value ->
    // 只执行一次
}
```

### 注意事项

1. **不要持有 Context**：ViewModel 不应该持有 Activity 或 View 的引用
2. **LiveData 自动切线程**：使用 postValue 可以在后台线程更新数据
3. **配置变化**：ViewModel 会在配置变化后继续存在，但 Activity 重建会导致新的 ViewModel 实例
4. **配合 Repository**：实际应用中，ViewModel 通常配合 Repository 使用，从数据库或网络获取数据
5. **替代方案**：对于更复杂的需求，可以使用 Kotlin 协程 + Flow 替代 LiveData
