package com.zsj.learndemo.proxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zsj.learndemo.R;
import com.zsj.learndemo.proxy.notification.Notify;
import com.zsj.learndemo.proxy.notification.NotifyProxy;

import java.lang.reflect.Proxy;

/**
 * 代理模式
 */
public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);



        ILawsuit xiaoming = new Xiaoming();
        ILawsuit layer = new Lawyer(xiaoming);

        layer.submit();
        layer.burden();
        layer.defend();
        layer.finish();

        View view = new View(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//
//        ILawsuit zhangsan = new Zhangsan();
//        ILawsuit layer1 = new Lawyer(zhangsan);
//        layer1.submit();
//        layer1.burden();
//        layer1.defend();
//        layer1.finish();


        ILawsuit xiaoming1 = new Xiaoming();
        DynamicProxy proxy = new DynamicProxy(xiaoming1);
        ClassLoader loader = xiaoming1.getClass().getClassLoader();
        ILawsuit layer2 = (ILawsuit) Proxy.newProxyInstance(loader,new Class[]{ILawsuit.class},proxy);
        layer2.submit();
        layer2.burden();
        layer2.defend();
        layer2.finish();

        NotifyProxy p = new NotifyProxy(this);
        p.send();
    }
}
