package expandcollapse.android.com.expandcollapse;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by qainfotech on 1/9/16.
 */
public class MyScrollView extends ScrollView {

    private static final String TAG = MyScrollView.class.getName();
    private float mTouchPosition;
    private float mReleasePosition;
    public static boolean IS_SCROLLING_UP;

    public MyScrollView(Context context) {
        super(context);
    }
    public MyScrollView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(ImageScalingWithScrollViewActivity.shallScroll) {
            Log.d(TAG, "onInterceptTouchEvent() true");
           return super.onInterceptTouchEvent(ev);
        }
        else {
            Log.d(TAG, "onInterceptTouchEvent() false");
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("LOGLOG", "inside onTouchEvent() ");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTouchPosition = event.getY();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            mReleasePosition = event.getY();

            if (mTouchPosition - mReleasePosition > 0) {
                // user scroll down
                IS_SCROLLING_UP=false;
            } else {
                //user scroll up
                IS_SCROLLING_UP=true;
            }
        }
        return super.onTouchEvent(event);
    }
}

