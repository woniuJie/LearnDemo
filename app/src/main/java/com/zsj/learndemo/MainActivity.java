package com.zsj.learndemo;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zsj.learndemo.aes.AESActivity;
import com.zsj.learndemo.animator.AnimatorActivity;
import com.zsj.learndemo.animator.ZoomActivity;
import com.zsj.learndemo.broadcastreceiver.BroadCastActivity;
import com.zsj.learndemo.cainiao.CainiaoActivity;
import com.zsj.learndemo.calandar.CalendarActivity;
import com.zsj.learndemo.calandarview.CalandarViewActivity;
import com.zsj.learndemo.contentpro.ContentProviderActivity;
import com.zsj.learndemo.download_video.A;
import com.zsj.learndemo.download_video.DownLoadVideoActivity;
import com.zsj.learndemo.duomeiti.DuoMeiTiActivity;
import com.zsj.learndemo.fanshe.FanSheActivity;
import com.zsj.learndemo.game2048.Game2048Activity;
import com.zsj.learndemo.handler.HandlerActivity;
import com.zsj.learndemo.hencode.HenCodeViewActivity;
import com.zsj.learndemo.image.ImageSelectActivity;
import com.zsj.learndemo.intentService.myintent.MyIntentServiceActivity;
import com.zsj.learndemo.intentService.service.MyServiceActivity;
import com.zsj.learndemo.location.LocationActivity;
import com.zsj.learndemo.markdown.MarkdownActivity;
import com.zsj.learndemo.nfc.NFCActivity;
import com.zsj.learndemo.oom.OOMActivity;
import com.zsj.learndemo.ping.PingActivity;
import com.zsj.learndemo.print.PrintActivity;
import com.zsj.learndemo.proxy.ProxyActivity;
import com.zsj.learndemo.share.ShareFileActivity;
import com.zsj.learndemo.shot.ShotActivity;
import com.zsj.learndemo.snap.SnapActivity;
import com.zsj.learndemo.suanfa.AUtil;
import com.zsj.learndemo.thread.ThreadActivity;
import com.zsj.learndemo.utils.ToastUtils;
import com.zsj.learndemo.view.ViewActivity;
import com.zsj.learndemo.web.WebActivity;
import com.zsj.learndemo.wuziqi.WuziqiActivity;
import com.zsj.learndemo.zhuangshi.ZhuangshiActivity;
import com.zsj.learndemo.zxing.ZxingActivity;
import com.zsj.learndemo.zxingimage.ZxingImageActivity;

import java.net.URISyntaxException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "zsjMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

//        Log.d(TAG, "onCreate: " + AUtil.translate(182838));
//
//        int a[] = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
//
//        int b[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 15, 16};
//        Log.d(TAG, "onCreate: " + AUtil.find(b, 8, 0, b.length - 1));
//        Log.d(TAG, "onCreate: " + AUtil.find1(b, 8));
//        A.AAA = 2;
//        Log.d(TAG, "onCreate:A.AAA = "+A.AAA);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("1111");
        threadLocal.set("2222");
        threadLocal.set("3333");


        Log.d(TAG, "onCreate:threadLocal.get()- " + threadLocal.get());
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    public void onClickView(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

    public void onClickPing(View view) {
//        Intent intent = new Intent(this, PingActivity.class);
//        startActivity(intent);

//        Toast.makeText(this,"test",Toast.LENGTH_SHORT).show();
        ToastUtils.showToast(this, "toast", Toast.LENGTH_SHORT);

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void onClickNotification(View view) {
        NotificationUtils notificationUtils = new NotificationUtils(this);

        Intent intent = new Intent(this, PingActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        notificationUtils.sendNotification(1, "title", "content", pendingIntent);
    }

    public void onClickSnap(View view) {
        Intent intent = new Intent(this, SnapActivity.class);
        startActivity(intent);
    }

    public void onClickWebView(View view) {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }

    public void onshot(View view) {
        Intent intent = new Intent(this, ShotActivity.class);
        startActivity(intent);
    }

    public void onCalendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    public void onZxing(View view) {
        Intent intent = new Intent(this, ZxingActivity.class);
        startActivity(intent);
    }

    public void onZxingImage(View view) {
        Intent intent = new Intent(this, ZxingImageActivity.class);
        startActivity(intent);
    }

    public void onPrint(View view) {
        Intent intent = new Intent(this, PrintActivity.class);
        startActivity(intent);
    }

    public void onCainiao1(View view) {
        Intent intent = new Intent(this, CainiaoActivity.class);
        startActivity(intent);
    }

    public void onNFC(View view) {
        Intent intent = new Intent(this, NFCActivity.class);
        startActivity(intent);
    }

    public void onShare(View view) {
        Intent intent = new Intent(this, ShareFileActivity.class);
        startActivity(intent);
    }

    public void onDuoMeiTi(View view) {
        Intent intent = new Intent(this, DuoMeiTiActivity.class);
        startActivity(intent);
    }

    public void onCalandarView(View view) {
        Intent intent = new Intent(this, CalandarViewActivity.class);
        startActivity(intent);
    }

    public void onAnimator(View view) {
        Intent intent = new Intent(this, AnimatorActivity.class);
        startActivity(intent);
    }

    public void onZoomAnimator(View view) {
        Intent intent = new Intent(this, ZoomActivity.class);
        startActivity(intent);
    }

    public void onLocation(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void onIntentService(View view) {
        Intent intent = new Intent(this, MyIntentServiceActivity.class);
        startActivity(intent);
    }

    public void onMarkdown(View view) {
        Intent intent = new Intent(this, MarkdownActivity.class);
        startActivity(intent);
    }

    public void onWuziqi(View view) {
        Intent intent = new Intent(this, WuziqiActivity.class);
        startActivity(intent);
    }

    public void onGameA(View view) {
        Intent intent = new Intent(this, Game2048Activity.class);
        startActivity(intent);
    }

    public void onImageSeclect(View view) {
        Intent intent = new Intent(this, ImageSelectActivity.class);
        startActivity(intent);
    }

    public void onOOM(View view) {
        Intent intent = new Intent(this, OOMActivity.class);
        startActivity(intent);
    }

    public void onFanshe(View view) {
        Intent intent = new Intent(this, FanSheActivity.class);
        startActivity(intent);
    }

    public void onService(View view) {
        Intent intent = new Intent(this, MyServiceActivity.class);
        startActivity(intent);
    }

    public void onContentProvider(View view) {
        Intent intent = new Intent(this, ContentProviderActivity.class);
        startActivity(intent);
    }

    public void onProxy(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        startActivity(intent);
    }

    public void onBroadcast(View view) {
        Intent intent = new Intent(this, BroadCastActivity.class);
        startActivity(intent);
    }

    public void onThread(View view) {
        Intent intent = new Intent(this, ThreadActivity.class);
        startActivity(intent);
    }

    public void onZhuangshi(View view) {
        Intent intent = new Intent(this, ZhuangshiActivity.class);
        startActivity(intent);
    }

    public void onDownload(View view) {
        Intent intent = new Intent(this, DownLoadVideoActivity.class);
        startActivity(intent);
    }

    public void onAES(View view) {
        Intent intent = new Intent(this, AESActivity.class);
        startActivity(intent);
    }

    public void onMyHandler(View view) {
        Intent intent = new Intent(this, HandlerActivity.class);
        startActivity(intent);
    }
    public void onHenCodeTest(View view) {
        Intent intent = new Intent(this, HenCodeViewActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState: 2");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: 1");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    public void onGoJek(View view) {
        //openOrtherApp("com.gojek.app");
        openOrtherAppByUri("cumart://m.cumart.net/shop/detail?productId=100120");

    }

    public void openOrtherApp(String name) {
        // 获取包管理器
        PackageManager manager = getPackageManager();
        // 指定入口,启动类型,包名
        Intent intent = new Intent(Intent.ACTION_MAIN);//入口Main
        intent.addCategory(Intent.CATEGORY_LAUNCHER);// 启动LAUNCHER,跟MainActivity里面的配置类似
        intent.setPackage(name);//包名
        //查询要启动的Activity
        List<ResolveInfo> apps = manager.queryIntentActivities(intent, 0);
        if (apps.size() > 0) {//如果包名存在
            ResolveInfo ri = apps.get(0);
            // //获取包名
            String packageName = ri.activityInfo.packageName;
            //获取app启动类型
            String className = ri.activityInfo.name;
            //组装包名和类名
            ComponentName cn = new ComponentName(packageName, className);
            //设置给Intent
            intent.setComponent(cn);
            //根据包名类型打开Activity
            startActivity(intent);
        } else {
            Toast.makeText(this, "找不到包名;", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean openOrtherAppByUri(String url) {
        Intent intent;
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
        } catch (URISyntaxException e) {
            return true;
        }

//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //未安装APP则不作跳转
        if (getPackageManager().resolveActivity(intent, 0) == null) {
            return true;
        }

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            return true;
        }

        return true;

    }


}
