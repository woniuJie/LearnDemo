package com.zsj.learndemo.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

public class AWebview extends WebView {
    public AWebview(Context context) {
        super(context);
    }

    public AWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AWebview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AWebview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d("zsj", "onScrollChanged: "+l+"--"+t+"--"+oldl+"--"+oldt);
    }
}
