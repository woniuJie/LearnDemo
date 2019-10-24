package com.zsj.learndemo.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by zhangshijie on 2019/6/19;
 */
public class ToastUtils {
    private static Field sField_TN ;
    private static Field sField_TN_Handler ;
    static {
        try {
            sField_TN = Toast.class.getDeclaredField("mTN");
            sField_TN.setAccessible(true);
            sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler.setAccessible(true);
        } catch (Exception e) {}
    }

    private static void hook(Toast toast) {
        try {
            Object tn = sField_TN.get(toast);
            Handler preHandler = (Handler)sField_TN_Handler.get(tn);
            sField_TN_Handler.set(tn,new SafelyHandlerWarpper(preHandler));
        } catch (Exception e) {}
    }

    public static void showToast(Context context, CharSequence cs, int length) {
        Toast toast = Toast.makeText(context,cs,length);
        hook(toast);
        toast.show();
    }
}
