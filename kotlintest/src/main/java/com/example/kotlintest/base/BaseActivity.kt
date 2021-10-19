package com.example.kotlintest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * BaseActivity基类
 *
 * @author WangQingYi
 * @since  2021/10/18
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(),FrameView<VB> {

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        BindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.initView()
    }
}