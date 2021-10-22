package com.wangqingyi.eventbus.base

import androidx.viewbinding.ViewBinding

/**
 * View层基类抽象
 *
 * @author Qu Yunshuo
 * @since 10/13/20
 */
interface FrameView<VB : ViewBinding> {

    /**
     * 初始化View
     */
    fun VB.initView()
}