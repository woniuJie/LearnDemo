package com.zsj.learndemo.zhuangshi;

import android.util.Log;

public class CheapCloth extends PersonCloth {
    private String TAG = "zsj-cheapCloth";

    public CheapCloth(Person mPerson) {
        super(mPerson);
    }

    private void dressShort() {
        Log.d(TAG, "dressShort: ");
    }

    @Override
    public void dressed() {
        super.dressed();
        dressShort();
    }
}
