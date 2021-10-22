package com.wangqingyi.eventbus.stickyEvent

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wangqingyi.eventbus.R
import com.wangqingyi.eventbus.message.MessageEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 接收粘性事件
 *
 * @author WangQingYi
 * @since  2021/10/21
 */
class StickyReceiveActivity : AppCompatActivity() {

private var vTextView:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticky_receive)
        vTextView= findViewById(R.id.vTextView1)
    }

    /**
     * 接收粘性事件，sticky = true表示是粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
     fun event(message: MessageEvent) {
        vTextView?.text = message.message
//        EventBus.getDefault().removeStickyEvent(this)
        Log.d("qweqe", "qweqe: ")
    }
}