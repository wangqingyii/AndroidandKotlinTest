package com.example.kotlintest.coroutine

import android.util.Log
import com.example.kotlintest.base.BaseActivity
import com.example.kotlintest.databinding.ActivityCoroutineExerciseBinding
import kotlinx.coroutines.*

/**
 * 协程练习
 *
 * @author WangQingYi
 * @since  2021/10/19
 */
class CoroutineExercise : BaseActivity<ActivityCoroutineExerciseBinding>() {
    /**
     * 创建协程
     * 创建协程的两种方式
     * CoroutineScope.launch()
     * CoroutineScope.async()
     * launch()构建器适合执行“一劳永逸”的工作，也就是说他可以启动新协程而不将结果返回给调用方；
     * async()构建器可启动新协程并允许使用一个名为await的花旗函数 返回result。
     *
     * launch与async之间的差异：
     * 很大的差异就是它们对异常的处理方式不同。如果使用async作为最外层携程的开启方式，它期望最终是通过调用
     * await来获取结果(或者异常)，所以默认情况下它不会抛出异常。这意味着如果使用 async 启动新的最外层协程，
     * 而不使用await，它会静默地将异常丢弃。
     */

    /**
     * 使用官网库的MainScope()获取一个协程作用域用于创建协程
     */
    private val mScope = MainScope()

    override fun ActivityCoroutineExerciseBinding.initView() {
        // launch学习
        launchLearning()
        // async学习
        asyncLearning()
        // async并发学习
        asyncLearning2()
    }

    /**
     * launch构建器学习
     */
    private fun launchLearning() {
        // 创建一个默认参数的协程，其默认调度模式为Main
        val job1 = mScope.launch {
            // 这里是协程体

            // 延迟1000毫秒 delay是一个挂起函数
            // 在这1000毫秒内协程所处的线程不会堵塞
            // 协程将线程的执行权交出去 ，该线程继续原来的工作，到时见后会恢复至此继续向下执行
            delay(1000)
        }

        // 创建一个指定了调度模式的协程，该协程的运行线程为IO线程
        val job2 = mScope.launch(Dispatchers.IO) {
            // 此处是IO线程

            // 切线程 江西诚所处的线程环境切至指定的调度模式Main
            withContext(Dispatchers.Main) {
                // 现在就是Main线程了 可以再此进行UI操作
            }

            // 示例从网络中获取数据，并更新UI
            // 该例子不会阻塞主线程
            val userInfo = getUserInfo()
            withContext(Dispatchers.Main) {
                // 更新UI
            }
        }
    }

    /**
     * async构建器学习
     */
    private fun asyncLearning() {
        mScope.launch {
            // 开启一个IO模式的线协程，并返回一个Deferred，Deferred可以用来获取返回值
            // 代码执行到此处是会开一个协程，然后去执行协程体，父协程的代码会接着走下去
            val deferred = async(Dispatchers.IO) {
                // 模拟耗时
                delay(2000)
                // 返回一个值
                "async"
            }
            // 等到async执行完成后获取返回值，此处并不会阻塞线程，而是挂起，将线程的执行权交出去
            // 等到async的协程体执行完毕后，会恢复协程继续往下执行
            val date = deferred.await() // 调用await函数获取结果
        }
    }

    /**
     * async并发学习
     */
    private fun asyncLearning2() {
        mScope.launch {
            val job1 = async {
                delay(5000)
                "1"
            }
            val job2 = async {
                delay(5000)
                "2"
            }
            val job3 = async {
                delay(5000)
                "3"
            }
            val job4 = async {
                delay(5000)
                "4"
            }
            val job5 = async {
                delay(5000)
                "5"
            }
            // 代码执行到此处时，5个请求已经同时在执行了
            // 等待各job执行完，将结果合并
            Log.d(
                "qweqwe",
                "asyncLearning2: ${job1.await()}${job2.await()}${job3.await()}${job4.await()}${job5.await()}"
                // 因为我们设置的模拟时间都是5000毫秒所以当job1执行完时，其他job也均执行完成
            )
        }
    }


    /**
     * 获取用户信息 该函数模拟IO获取数据
     * @return String
     */
    private suspend fun getUserInfo(): String {
        return withContext(Dispatchers.IO) {
            delay(2000)
            "Kotlin"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 取消协程 防止协程泄漏 如果使用lifecycleScope则不需要手动取消
        mScope.cancel()
    }
}