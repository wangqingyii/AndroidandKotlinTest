package com.example.kotlintest.coroutine

import android.util.Log
import com.example.kotlintest.base.BaseActivity
import com.example.kotlintest.databinding.ActivityCoroutinelnDepthBinding
import kotlinx.coroutines.*
import java.lang.RuntimeException

/**
 * 协程的深入学习
 *
 * @author WangQingYi
 * @since  2021/10/19
 */
class CoroutineInDepth : BaseActivity<ActivityCoroutinelnDepthBinding>() {

    /**
     * 使用官方库的MainScope()获取一个协程作用域用于创建
     */
    private val mScope = MainScope()

    override fun ActivityCoroutinelnDepthBinding.initView() {
        main()
        // 使用SupervisorJob
        supervisorJobTest()
    }

    private fun main() {
        val coroutineContext = Job() + Dispatchers.Default + CoroutineName("myContext")
        Log.d("qweqwe", "$coroutineContext,${coroutineContext[CoroutineName]}")
        val newCoroutineContext = coroutineContext.minusKey(CoroutineName)
        Log.d("qweqwe", "$newCoroutineContext")
    }

    /**
     * 使用SupervisorJob
     */
    private fun supervisorJobTest() {
        // 执行结果 Child2抛出异常后，Child3正常执行了，但是程序崩溃了，因为我们没有处理这个异常
//        mScope.run {
//            launch {
//                delay(5000)
//                Log.d("qweqwe", "Child 1")
//            }
//            launch {
//                delay(1000)
//                Log.d("qweqwe", "Child 2")
//                throw RuntimeException("--> RuntimeException")
//            }
//            launch {
//             delay(1500)
//                Log.d("qweqwe", "Child 3")
//            }
//        }
        // 优化代码
        /**
         * 这一次，程序没有崩溃，并且异常处理的打印也输出了，这就达到了我们想要的效果。但是要注意一个事情，这几个
         * 子协程的父级是SupervisorJob，但是他们再有子协程的话，他们的子协程的父级就不是SupervisorJob了，所以当
         * 它们产生异常时，就不是我们演示的效果了。
         * 新的协程被创建时，会生成新的 Job 实例替代 SupervisorJob。
         */
        mScope.run {
            launch {
                delay(500)
                Log.d("qweqwe", "Child 1")
            }
            launch(Dispatchers.Default + CoroutineExceptionHandler { coroutineContext, throwable ->
                Log.e("qweqwe", "CoroutineExceptionHandler: $throwable")
            }) {
                delay(1000)
                Log.e("qweqwe", "Child 2")
                throw RuntimeException("--> RuntimeException <--")
            }
            launch {
                delay(1500)
                Log.d("qweqwe", "Child 3")
            }
        }
    }

    /**
     * supervisorScope的使用
     * supervisorScope可以达到我们想要的效果
     */
    private fun supervisorScopeTest() {
        val scope = CoroutineScope(Job() + Dispatchers.Default)
        scope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e("qweqwe", "CoroutineExceptionHandler: $throwable")
        }) {
            supervisorScope {
                launch {
                    delay(500)
                    Log.e("qweqwe", "Child 1 ")
                }
                launch {
                    delay(1000)
                    Log.e("qweqwe", "Child 2 ")
                    throw  RuntimeException("--> RuntimeException <--")
                }
                launch {
                    delay(1500)
                    Log.e("qweqwe", "Child 3 ")
                }
            }
        }

    }
}