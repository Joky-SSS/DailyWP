package com.jokyxray.dailywp.uitls;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class DensityUtil {

    private DensityUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * dp转px
     */
    public static int dp2px(float dpVal) {
        return (int) (0.5f + dpVal * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics())+0.5F);
    }

    /**
     * px转dp
     */
    public static int px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxVal / scale + 0.5F);
    }

    /**
     * px转sp
     */
    public static int px2sp(Context context, float pxVal) {
        return (int)(pxVal / context.getResources().getDisplayMetrics().scaledDensity +0.5F);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}