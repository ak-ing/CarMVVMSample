package com.txznet.common.utils

import android.content.res.Resources
import android.graphics.Color
import android.graphics.PointF
import android.graphics.Rect
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.txznet.common.AppGlobal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Created by Rick on 2023-01-05  17:00.
 * Description: 顶层拓展函数
 * 顶层函数、拓展函数、拓展属性
 */

/*-------------------------------------顶层函数------------------------------------------------*/

/**
 * 颜色值色差计算
 */
fun calculateCIE76Distance(color1: Int, color2: Int): Double {
    val r1 = Color.red(color1)
    val g1 = Color.green(color1)
    val b1 = Color.blue(color1)
    val r2 = Color.red(color2)
    val g2 = Color.green(color2)
    val b2 = Color.blue(color2)
    return sqrt(
        (r1 - r2).toDouble().pow(2.0) + (g1 - g2).toDouble().pow(2.0) + (b1 - b2).toDouble().pow(2.0)
    )
}

fun <T> Any.typeInstance(position: Int): T? {
    val type = this::class.java.genericSuperclass
    if (type is ParameterizedType) {
        try {
            val clazz = type.actualTypeArguments.filterIsInstance<Class<T>>()[position]
            val instance = clazz.newInstance()
            return instance
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return null
}

/*-------------------------------------拓展属性------------------------------------------------*/

/**
 * TAG
 */
val Any.CLASS_TAG get() = this::class.java.simpleName + ":" + Integer.toHexString(hashCode())

/**
 * dp单位
 */
val Number.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

/**
 * @ColorRes Int
 * Color资源Id的颜色值
 */
val Int.color get() = ContextCompat.getColor(AppGlobal.context, this)


/*-------------------------------------拓展函数------------------------------------------------*/

/**
 * 解决Flow collect 协程多层嵌套
 */
fun <T> Flow<T>.collectWithLifecycle(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    collector: FlowCollector<T>
) = lifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState).collect(collector)
}


/**
 * 显示状态栏、导航栏、标题栏
 */
fun Window.showSystemUI() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        WindowCompat.getInsetsController(this, decorView).also {
            it.show(WindowInsetsCompat.Type.systemBars())
        }
    } else {
        clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}

/**
 * 隐藏状态栏、导航栏、标题栏
 */
fun Window.hideSystemUI() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        WindowCompat.getInsetsController(this, decorView).also {
            it.hide(WindowInsetsCompat.Type.systemBars())
            it.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}


/**
 * 点位是否在View里面
 */
fun View.isInside(pointF: PointF): Boolean {
    val rect = Rect()
    val locations = IntArray(2)
    getDrawingRect(rect)
    getLocationOnScreen(locations)
    rect.left = locations[0]
    rect.top = locations[1]
    rect.right = rect.right + locations[0]
    rect.bottom = rect.bottom + locations[1]
    return rect.contains(pointF.x.toInt(), pointF.y.toInt())
}


/**
 * 获取控件中心点颜色
 */
fun View.centerColor(): Int {
    val bitmap = background.toBitmap()
    return bitmap.getPixel(bitmap.width / 2, bitmap.height / 2)
}

