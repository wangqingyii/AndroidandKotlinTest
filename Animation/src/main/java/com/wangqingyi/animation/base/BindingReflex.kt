package com.wangqingyi.animation.base

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.Exception
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType
import java.util.*

/**
 * 反射获取ViewBinding
 *
 * @author WangQingYi
 * @since  2021/10/18
 */
object BindingReflex {

    /**
     * 反射获取ViewBinding
     *
     * @param <V>    ViewBinding 实现类
     * @param aClass 当前类
     * @param from   layouinflater
     * @return viewBinding实例
     */
    fun <V : ViewBinding> reflexViewBinding(aClass: Class<*>, from: LayoutInflater?): V {
        try {
            val actualTypeArguments =
                (Objects.requireNonNull(aClass.genericSuperclass) as ParameterizedType).actualTypeArguments
            for (i in actualTypeArguments.indices) {
                val tClass: Class<Any>
                try {
                    tClass = actualTypeArguments[i] as Class<Any>
                } catch (e: Exception) {
                    continue
                }
                if (ViewBinding::class.java.isAssignableFrom(tClass)) {
                    val inflater = tClass.getMethod("inflate", LayoutInflater::class.java)
                    return inflater.invoke(null, from) as V
                }
            }
            return reflexViewBinding<V>(aClass.superclass, from)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        throw RuntimeException("ViewBinding初始化失败")
    }
}