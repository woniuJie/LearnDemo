package com.zsj.learndemo.duanxin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.zsj.learndemo.utils.ToastUtils;

/**
 * Created by zhangshijie on 2019/8/22;
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pduses = (Object[]) intent.getExtras().get("pdus");
        for (Object pduse : pduses) {
            SmsMessage fromPdu = SmsMessage.createFromPdu((byte[]) pduse);
            String originatingAddress = fromPdu.getOriginatingAddress();
            String messageBody = fromPdu.getMessageBody();

            ToastUtils.showToast(context,originatingAddress+"-"+messageBody, Toast.LENGTH_LONG);
        }
    }

}
