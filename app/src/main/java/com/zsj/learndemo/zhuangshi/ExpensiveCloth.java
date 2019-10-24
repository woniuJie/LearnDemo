package com.zsj.learndemo.zhuangshi;

import android.util.Log;

public class ExpensiveCloth extends PersonCloth{
    private String TAG = "zsj-ExpensiveCloth";
    public ExpensiveCloth(Person mPerson) {
        super(mPerson);
    }

    private void dressShirt(){
        Log.d(TAG, "dressShirt: ");
    }

    @Override
    public void dressed() {
        super.dressed();
        dressShirt();
    }
}
