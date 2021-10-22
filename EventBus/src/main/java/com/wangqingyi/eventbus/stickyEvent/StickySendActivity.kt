package com.wangqingyi.eventbus.stickyEvent

import android.content.Intent
import com.example.androidandkotlintest.base.BaseActivity
import com.wangqingyi.eventbus.databinding.ActivityStickySendBinding
import com.wangqingyi.eventbus.message.MessageEvent
import org.greenrobot.eventbus.EventBus

/**
 * 粘性事件练习
 * 粘性事件可以在发送事件之后再订阅该事件也能接收到该事件。与普通事件不同，普通事件是先注册后发布，
 * 粘性事件可以先发布后注册。
 *
 * @author WangQingYi
 * @since  2021/10/21
 */
class StickySendActivity : BaseActivity<ActivityStickySendBinding>() {
    override fun ActivityStickySendBinding.initView() {
        vButton.setOnClickListener {
            EventBus.getDefault().postSticky(MessageEvent("StickySendActivity 发送粘性事件"))
            startActivity(Intent(this@StickySendActivity, StickyReceiveActivity::class.java))
        }
    }
}