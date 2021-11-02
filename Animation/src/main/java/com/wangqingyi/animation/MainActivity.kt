package com.wangqingyi.animation

import android.animation.AnimatorSet
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
        // 属性动画
        initPropertyAnimation()
        // AnimatorSet
        initAnimatorSet()
        // ViewPropertyAnimator
        initViewPropertyAnimator()
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

    /**
     * AnimatorSet
     * 如果想要一个动画结束后播放另一个动画，或者同时播放，可以通过AnimatorSet来编排
     */
    private fun initAnimatorSet() {
        val aAnimator = ObjectAnimator.ofInt(1)
        val bAnimator = ObjectAnimator.ofInt(1)
        val cAnimator = ObjectAnimator.ofInt(1)
        val dAnimator = ObjectAnimator.ofInt(1)
        AnimatorSet().apply {
            // a在b之前播放
            play(aAnimator).before(bAnimator)
            // b和c同时播放动画效果
            play(bAnimator).with(cAnimator)
            // d在c播放结束之后播放
            play(dAnimator).after(cAnimator)
            // 1秒后播放a动画
            play(aAnimator).after(1000)
            // 顺序播放
            playSequentially(aAnimator, bAnimator, cAnimator, dAnimator)
            // 同时播放
            playTogether(aAnimator, bAnimator, cAnimator, dAnimator)
            start()
        }
    }

    /**
     * ViewPropertyAnimator
     * 针对View对象的特定属性同时播放动画，我们也可以采用ViewPropertyAnimator
     */
    private fun initViewPropertyAnimator() {
        // translationX属性
        mBinding.vTranslationXTv.setOnClickListener {
            // 添加动画
            val animator = mBinding.vTranslationXTv.animate()
            animator.duration = 1000
            // 点击一次会向右偏移，再点击没效果
            animator.translationX(100f)
            animator.start()
        }
        // translationXBy属性
        mBinding.vTranslationXByTv.setOnClickListener {
            val animator = mBinding.vTranslationXByTv.animate()
            animator.duration = 1000
            // 每次点击都会向右偏移
            animator.translationXBy(100f)
            animator.start()
        }
    }

    /**
     * ValueAnimator与ObjectAnimator
     * ValueAnimator作为ObjectAnimator的父类，主要动态计算目标对象属性的值，然后设置给对象属性，达到动画效果，
     * 而ObjectAnimator则在ValueAnimator的基础上极大地简化对目标对象的属性值的计算和添加效果，融合了 ValueAnimator
     * 的计时引擎和值计算以及为目标对象的命名属性添加动画效果这一功能。
     */
private fun initValueAnimatorAndObjectAnimator(){

}

}