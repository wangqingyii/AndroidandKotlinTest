package com.wangqingyi.handler

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Handler学习
 *
 * @author WangQingYi
 * @since  2021/10/25
 */
class MainActivity : AppCompatActivity() {

    private lateinit var vTextView: TextView

    private lateinit var vButton: Button

    companion object {
        const val TAG = "qweqwe"
    }

    // mUiHandler在主线程中创建，所以自动绑定主线程
    private val mUiHandler = Handler {
        when (it.what) {
            1 -> {
                Log.d(TAG, "handleMessage thread id ${Thread.currentThread().id}")
                Log.d(TAG, "msg.arg1 ${it.arg1}")
                Log.d(TAG, "msg.arg2 ${it.arg2}")
                vTextView.text = "文件下载完成"
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vTextView = findViewById(R.id.vTextView)
        vButton = findViewById(R.id.vButton)
        vButton.setOnClickListener {
            val downloadThread = DownloadThread()
            downloadThread.start()
        }
    }

    /**
     * 模拟下载文件
     */
    inner class DownloadThread : Thread() {
        override fun run() {
            try {
                Log.d(TAG, "DownloadThread id ${Thread.currentThread().id}")
                Log.d(TAG, "开始下载文件")
                // 此处让线程休眠5秒钟，模拟文件的耗时过程
                sleep(5000)
                Log.d(TAG, "文件下载完成")
                // 文件下载完成后更新UI
                val msg = Message()
                // 虽然Message的构造函数是public的，我们也可以通过下面两种凡是通过循环对象获取Message
//                msg = Message.obtain()
//                msg = mUiHandler.obtainMessage()
                // what是我们自定义的一个Message的识别码，以便在Handler的HandleMessage方法中根据不同的Message做不同的处理
                msg.what = 1

                // 我们可以通过arg1和arg2给Message传入简单的数据
                msg.arg1 = 123
                msg.arg2 = 789
                // 我们也可以通过给obj赋值Object类型传递向Message传入任意数据
//                msg.obj = null
                // 我们还可以通过setData方法和getData方法向Message中写入读取Bundle类型的数据
//                msg.data = null
//                val data = msg.data
                // 将该Message发送给对应的Handler
                mUiHandler.sendMessage(msg)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
    }

}