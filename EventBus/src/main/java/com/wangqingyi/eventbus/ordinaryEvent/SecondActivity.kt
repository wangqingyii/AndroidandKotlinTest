package com.wangqingyi.eventbus.ordinaryEvent

import android.widget.Button
import com.example.androidandkotlintest.base.BaseActivity
import com.wangqingyi.eventbus.R
import com.wangqingyi.eventbus.databinding.ActivitySecondBinding
import com.wangqingyi.eventbus.message.MessageEvent
import org.greenrobot.eventbus.EventBus

class SecondActivity : BaseActivity<ActivitySecondBinding>() {

    override fun ActivitySecondBinding.initView() {
        val vButton = findViewById<Button>(R.id.vButton)
        vButton.setOnClickListener {
            // 发送事件
            EventBus.getDefault().post(MessageEvent(
                "Hello EventBus"))
            finish()
        }
        vButton2.setOnClickListener {
            // 发送事件
            EventBus.getDefault().post(MessageEvent(
                "床前明月光"))
            finish()

        }
        vButton3.setOnClickListener {
            // 发送事件
            EventBus.getDefault().post(MessageEvent(
                "疑是地上霜"))
            finish()

        }
        vButton4.setOnClickListener {
            // 发送事件
            EventBus.getDefault().post(MessageEvent(
                "举头望明月"))
            finish()

        }
        vButton5.setOnClickListener {
            // 发送事件
            EventBus.getDefault().post(MessageEvent(
                "低头思故乡"))
            finish()

        }
    }
}