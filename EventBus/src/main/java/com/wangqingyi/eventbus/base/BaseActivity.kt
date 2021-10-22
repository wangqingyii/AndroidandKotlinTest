package com.example.androidandkotlintest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.wangqingyi.eventbus.base.BindingReflex
import com.wangqingyi.eventbus.base.FrameView

/**
 * BaseActivity基类
 *
 * @author WangQingYi
 * @since  2021/10/18
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), FrameView<VB> {

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        BindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.initView()
    }
}