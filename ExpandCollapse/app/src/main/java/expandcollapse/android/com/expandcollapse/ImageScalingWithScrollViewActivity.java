package expandcollapse.android.com.expandcollapse;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ImageScalingWithScrollViewActivity extends AppCompatActivity {

    private MyScrollView mScrollView;
    private ImageView mPhotoIV;
    private TextView nameView;
    private LinearLayout topcontainer;
    public static boolean shallScroll=true;
    private Toolbar toolbar;

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scaling_with_scroll_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        

        mScrollView = (MyScrollView) findViewById(R.id.scrollView);
        mPhotoIV = (ImageView) findViewById(R.id.contactPic);
        topcontainer= (LinearLayout) findViewById(R.id.topcontainer);
        nameView= (TextView) findViewById(R.id.nameView);
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ScrollPositionObserver());

        mPhotoIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ImageScalingWithScrollViewActivity.this, "OnClick", Toast.LENGTH_SHORT).show();
            }
        });

    }



    private class ScrollPositionObserver implements ViewTreeObserver.OnScrollChangedListener {

        private int topContainerHeight;
        private float translationYTopContainerDP;


        public ScrollPositionObserver() {
            topContainerHeight = getResources().getDimensionPixelSize(R.dimen.top_container_height);
        }

        @Override
        public void onScrollChanged() {
            int getScrollY= mScrollView.getScrollY();
            int scrollY = Math.min(Math.max(getScrollY, 0), topContainerHeight);
            float translationYTopContainerPx = topcontainer.getTranslationY();
            translationYTopContainerDP = convertPixelToDp(translationYTopContainerPx);
            int mPhotoIVBottomInPixel = mPhotoIV.getBottom();
            int topcontainerDynamicHeight = topcontainer.getHeight();
            int offSet = mPhotoIV.getHeight() + nameView.getHeight() + 5;

            Log.d("LOGLOG", "getScrollY= " + getScrollY + " topContainerHeight= " + topContainerHeight +
                    " scrollY=" + scrollY + " translationYTopContainerPx =" + translationYTopContainerPx + " translationYTopContainerDP="
                    + translationYTopContainerDP + " mPhotoIVBottomInPixel=" + mPhotoIVBottomInPixel +
                    "+ offSet=" + offSet + " topcontainerDynamicHeight=" + topcontainerDynamicHeight);

            if(MyScrollView.IS_SCROLLING_UP) {
                if (translationYTopContainerPx <= offSet) {
                    shallScroll=true;
                    topcontainer.setTranslationY(scrollY/2);
//                mPhotoIV.setTranslationX(-(scrollY + 3));
//                nameView.setTranslationY((scrollY-2));
                }else{
                    shallScroll=false;
                }
            }else{
                topcontainer.setTranslationY(scrollY/2);
            }


            /*if(translationYTopContainerPx <= offSet) {
//                mPhotoIV.setTranslationX(-(scrollY + 3));
//                nameView.setTranslationY((scrollY-2));
            }*/
        }



    }

    private float convertPixelToDp(float px) {
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }



}
