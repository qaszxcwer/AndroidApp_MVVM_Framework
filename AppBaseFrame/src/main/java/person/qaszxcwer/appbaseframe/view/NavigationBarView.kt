package person.qaszxcwer.appbaseframe.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import person.qaszxcwer.appbaseframe.utils.DeviceUtils

/**
 *
 * date: 2023/5/22
 * 
 */
class NavigationBarView : View {
    private var mStatusBarHeight = 0

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        context?.let {
            mStatusBarHeight = DeviceUtils.getNavigationBarHeight(context)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), mStatusBarHeight)
    }
}