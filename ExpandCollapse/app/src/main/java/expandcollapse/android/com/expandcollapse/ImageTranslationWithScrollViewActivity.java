package expandcollapse.android.com.expandcollapse;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.*;

public class ImageTranslationWithScrollViewActivity extends AppCompatActivity {

    private ScrollView myScrollView;
    String LOGTAG=ImageTranslationWithScrollViewActivity.class.getName();
    private int scrollVieHeight;
    private ImageView contactPic;
    private int contactPicHeightOriginal;
    private int contactPicHeightDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_translation_with_scroll_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myScrollView = (ScrollView) findViewById(R.id.myScrollView);
        contactPic = (ImageView) findViewById(R.id.contactPic);

        scrollVieHeight=myScrollView.getHeight();
        contactPicHeightOriginal =contactPic.getHeight();
        myScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                float scrollY = myScrollView.getScrollY();
                contactPicHeightDynamic =contactPic.getHeight();

                float scrollYDP=convertPixkelToDp(scrollY);
                Log.d(LOGTAG, "scrollVieHeight=" + scrollVieHeight+"contactPicHeightOriginal=" + contactPicHeightOriginal
                        +"contactPicHeightDynamic= "+contactPicHeightDynamic+" scrollYDP="+scrollYDP);
                myScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, (int) (scrollVieHeight + (scrollY))));


                if(contactPicHeightDynamic>0) {
                    contactPic.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, (int) (contactPicHeightOriginal - (scrollYDP))));
                }

                Log.d(LOGTAG, "scrollY=" + scrollY);


            }
        });

    }

    private float convertPixkelToDp(float px) {
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

}
