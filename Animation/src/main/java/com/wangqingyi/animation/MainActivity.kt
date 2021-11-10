package com.wangqingyi.animation

import android.animation.*
import android.util.Log
import com.wangqingyi.animation.base.BaseActivity
import com.wangqingyi.animation.databinding.ActivityMainBinding
import java.util.concurrent.Flow

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

        initValueAnimatorAndObjectAnimator()
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
     *
     * 动画可调用start()方法开始，也可调用cancel()取消
     */
    private fun initValueAnimatorAndObjectAnimator() {
        ObjectAnimator.ofFloat(mBinding.vTextView, "translationX", 0F, 780F, 0f).apply {
            // 完成动画需要的时间
            duration = 3000
            // 重复次数：无限循环
            repeatCount = ValueAnimator.INFINITE
            // 重复次数：从头开始
//            repeatCount = ValueAnimator.RESTART
            // 监听值变化
            addUpdateListener {
                mBinding.vTextView.translationX = it.animatedValue as Float
            }
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {
                    // 动画开始
                }

                override fun onAnimationEnd(p0: Animator?) {
                    // 动画结束
                }

                override fun onAnimationCancel(p0: Animator?) {
                    // 动画取消
                }

                override fun onAnimationRepeat(p0: Animator?) {
                    // 动画重复
                }
            })
        }
    }

    /**
     * 估值器与插值器
     * 属性动画是由TypeEvaluator(估值器)与TimeInterpolator插值器来完成的
     * 插值器：根据时间流逝的百分比计算当前属性值改变的百分比
     * 估值器：根据当前属性改变的百分比来计算改变后的属性值
     * 先由插值器根据时间六十的百分比计算出目标对象的属性改变的百分比，再由估值器计算出来的属性改变的百分比计算出
     * 目标对象属性对应类型的值
     *
     * 插值器：
     * TypeEvaluator对象的evaluate()方法的fraction参数就是插值器计算得来，SDK中默认的时间插值器有：
     * LinearInterpolator 线性（匀速）
     * AccelerateInterpolator 持续加速
     * DecelerateInterpolator 持续减速
     * AccelerateDecelerateInterpolator 先加速后减速
     * OvershootInterpolator 结束时回弹一下
     * AnticipateInterpolator 开始回拉一下
     * BounceInterpolator 结束时Q弹一下
     * CycleInterpolator 来回循环
     */
    private fun estimatorAndInterpolator() {
// 使用自定义估值器
        val animator = ValueAnimator.ofObject(
            PointEvaluator(),
            // 动画开始值
            Point(0f, 0f),
            // 动画结束值，这个地方应该计算出手机屏幕的右下角
            Point(0f, 0f)
        )
        // 手动更新TextView的x和y值
        animator.addUpdateListener {
            val point = it.animatedValue as Point
            mBinding.vTextView.x - point.x
            mBinding.vTextView.y - point.y
            Log.d("qweqwe", "point $point")
        }
        animator.duration = 5000
        mBinding.vTextView.setOnClickListener {
            animator.start()
        }
    }
    /**************************************************************************************************/
    /**
     * 自定义估值器，从屏幕左上角下线滑到右下角
     * SDK中默认带有的估值器有：IntEvaluator、FloatEvaluator、ArgbEvaluator，他们分别对应前面我们调用ValueAnimator
     * 对象所有对应的ofInt、ofFloat、ofArgb函数的估值器，分别用在Int类型，Float，颜色值类型之间计算。而ofObject函数
     * 则对应我们自定义类型的属性计算。
     */
    data class Point(var x: Float, var y: Float)

    /**
     * 定义PointEvaluator估值器，继承自TypeEvaluator，泛型参数为Point类型,通过实现evaluate方法，可以实现很多
     * 复制的动画效果。
     */
    class PointEvaluator : TypeEvaluator<Point> {
        /**
         * 根据估值器计算出但前对象的属性的百分比fraction，估算去属性当前具体的值
         * @param fraction 属性改变的百分比
         * @param startValue 对象开始的位置，例如这里点坐标开始位置：屏幕左上角的位置
         * @param endValue 对象结束的位置，例如这里点坐标结束的位置：屏幕右下角位置
         */
        override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
            if (startValue == null || endValue == null) {
                return Point(0f, 0f)
            }
            return Point(
                fraction * (endValue.x - startValue.x),
                fraction * (endValue.y - startValue.y)
            )
        }

    }
}