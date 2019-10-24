package com.zsj.learndemo.fanshe;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zsj.learndemo.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class FanSheActivity extends AppCompatActivity {

    private static final String TAG = "FanSheActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_she);

        Class classa = Son.class;

        Log.d(TAG, "onCreate: classa.getName()-" + classa.getName());

        //获取所有public 访问权限的变量 包括本类和父类
        Field[] fields = classa.getFields();
        //获取本类所有变量
        Field[] fields1 = classa.getDeclaredFields();
        for (Field field : fields1) {
            Log.d(TAG, "onCreate: " + field.getType() + "--" + field.getName());
        }

        Log.d(TAG, "onCreate: --------------");

        //获取所有 public 的方法，包括父类的方法
        Method[] methods = classa.getMethods();
        //获取本类的所有方法
        Method[] methods1 = classa.getDeclaredMethods();
        for (Method method : methods1) {
            Log.d(TAG, "onCreate: method.getName()" + method.getName());
            //方法的返回值类型
            Log.d(TAG, "onCreate: method.getReturnType()" + method.getReturnType());
            //方法的参数
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    Log.d(TAG, "onCreate: parameter.getName()"+parameter.getName()+"---"+parameter.getType());
                }
            }

            Log.d(TAG, "onCreate: ------");
        }

        sendMsg();
    }

    private void sendMsg(){
        Test test = new Test();
        Class mClass = test.getClass();

        try {
            Field a = mClass.getDeclaredField("a");
            a.setAccessible(true);
            a.setInt(test,3434);

            Field field1 = mClass.getDeclaredField("msg");
            field1.setAccessible(true);
            field1.set(test,"lhy");

            Method method = mClass.getDeclaredMethod("sendMsg", String.class, int.class);
            method.setAccessible(true);
            method.invoke(test,"hhh",333);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
