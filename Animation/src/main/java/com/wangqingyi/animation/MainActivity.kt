package com.wangqingyi.animation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import com.wangqingyi.animation.base.BaseActivity
import com.wangqingyi.animation.databinding.ActivityMainBinding

/**
 * 动画学习
 *
 * @author WangQingYi
 * @since  2021/10/26
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun ActivityMainBinding.initView() {
        initPropertyAnimation()
    }

    /**
     * 属性动画
     */
    private fun initPropertyAnimation() {
        // 位移动画
        mBinding.mLayout.setOnClickListener {
            // ofFloat方法第一个参数是要实现动画效果的View；第二个参数为属性名；
            // 第三个参数为可变长参数第一个值为动画开始的位置，第二个值为结束值的位置，如果数组大于3位数，那么前者将是后者的起始位置
            val objectAnimation =
                ObjectAnimator.ofFloat(mBinding.mLayout, "translationX", 0f, -170f)
            objectAnimation.start()
        }
        // 透明动画
        mBinding.vTransparencyBtn.setOnClickListener {
            // 将属性名换为透明度alpha
            val objectAnimation =
                ObjectAnimator.ofFloat(mBinding.vTransparencyBtn, "alpha", 1f, 0f, 1f)
            // 设置动画展示时间为3秒
            objectAnimation.duration = 3000
            objectAnimation.start()
        }
        // 缩放动画
        mBinding.vZoomBtn.setOnClickListener {
            // ofFloat()方法传入参数属性为scaleX和scaleY时，动态参数表示缩放的倍数。
            val objectAnimation = ObjectAnimator.ofFloat(mBinding.vZoomBtn, "scaleX", 1f, 2f)
            // 设置动画展示时间为3秒
            objectAnimation.duration = 3000
            // 设置动画重复次数
            objectAnimation.repeatCount = 3
            // 设置动画重复模式
            objectAnimation.repeatMode = ValueAnimator.REVERSE
            objectAnimation.start()
        }
        // 旋转动画
        mBinding.vWhirlingBtn.setOnClickListener {
            // ofFloat()方法的可变长参数，如果后者的值大于前者，那么顺时针旋转，小于前者，则逆时针旋转。
            val objectAnimation =
                ObjectAnimator.ofFloat(mBinding.vWhirlingBtn, "rotation", 0f, 180f, 0f)
            objectAnimation.duration = 3000
            objectAnimation.start()
        }
    }
}