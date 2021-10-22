package com.wangqingyi.eventbus.ordinaryEvent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wangqingyi.eventbus.R
import com.wangqingyi.eventbus.message.MessageEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * EventBus练习
 * 描述：
 * 1.使用了事件总线框架，实现事件发布者与订阅者松耦合
 * 2.提供了透明线程间通信，隐藏了发布线程与订阅线程间的线程切换
 *
 * @author WangQingYi
 * @since  2021/10/21
 */
class MainActivity : AppCompatActivity() {

    private var mTextView: TextView? = null
    private var mButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //注册事件
        EventBus.getDefault().unregister(this)
        initView()
        initEvent()
        jumpActivity()
    }

    /**
     * 注册EventBus
     */
    private fun initEvent() {
        EventBus.getDefault().register(this)
    }

    /**
     * 初始化View
     */
    private fun initView() {
        mTextView = findViewById(R.id.vTextView)
        mButton = findViewById(R.id.vButton)
        mTextView?.text = "今天是星期天"
    }

    private fun jumpActivity() {
        mButton?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 在onDestroy()方法里进行解绑
     */
    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(messageEvent: MessageEvent) {
        mTextView?.text = messageEvent.message
    }

}
