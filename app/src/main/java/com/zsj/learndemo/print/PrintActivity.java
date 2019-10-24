package com.zsj.learndemo.print;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintJob;
import android.print.PrintJobInfo;
import android.print.PrintManager;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSON;
import com.zsj.learndemo.R;
import com.zsj.learndemo.print.aa.EscPos;
import com.zsj.learndemo.print.bb.MyPrintAdapter;
import com.zsj.learndemo.print.cc.Cups;
import com.zsj.learndemo.print.dd.WebSocketClientManager;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft_6455;
import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PrintActivity extends AppCompatActivity {

    String filePath = Environment.getExternalStorageDirectory().toString() + File.separator;
    String TAG = "zsj";
    private WebView mWebView;
    static WebSocketClientManager webSocket = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        String[] ss = Cups.getPrinters(PrintActivity.this);
        Log.d(TAG, "onCreate: ss.length-" + ss.length);
    }

    public void onPrintManagerClick(View view) {
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(PrintAttributes.COLOR_MODE_COLOR);
        printManager.print("test pdf print", new MyPrintAdapter(this, "/storage/emulated/0/documents/share/张士杰.pdf"), builder.build());

    }

    public void onPrintImage(View view) {
//        PrintHelper photoPrinter = new PrintHelper(PrintActivity.this);
//        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_gift);
//        photoPrinter.printBitmap("droids.jpg - test print", bitmap);


        final PrintHelper printHelper = new PrintHelper(PrintActivity.this);
        printHelper.setColorMode(PrintHelper.COLOR_MODE_COLOR);
        printHelper.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        printHelper.printBitmap("job-" + SystemClock.uptimeMillis(), Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888));
    }

    public void onCainiao(View view) {
        String uri = "ws://192.168.1.7:13528";
        try {
            webSocket = new WebSocketClientManager(new URI(uri), new Draft_6455());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void onWifiPrint(View view) {

        MyAsyneTask task = new MyAsyneTask();
        task.execute("sss");

    }

    public class MyAsyneTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            NetPrinter printer = new NetPrinter();
            printer.Open("192.168.0.20",9100);
            Log.d(TAG, "doInBackground: "+printer.IFOpen);
            return "s";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }


    public void onPrintHtml(View view) {
        // Create a WebView object specifically for printing
        WebView webView = new WebView(PrintActivity.this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "page finished loading " + url);
                createWebPrintJob(view);
                mWebView = null;
            }
        });

        // Generate an HTML document on the fly:
        String htmlDocument = "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                "  <p>Test Print</p >\n" +
                "  <p><img src=\"https://m.cumart.net/img/index-icon2.png\"></p>\n" +
                " </body>\n" +
                "</html>";
        webView.loadDataWithBaseURL(null, htmlDocument, "text/HTML", "UTF-8", null);

//        webView.loadUrl("http://cloudprint.cainiao.com/template/standard/287067/4");
        // Keep a reference to WebView object until you pass the PrintDocumentAdapter
        // to the PrintManager
        mWebView = webView;

    }

    private void createWebPrintJob(WebView webView) {

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) PrintActivity.this
                .getSystemService(Context.PRINT_SERVICE);

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        // Create a print job with name and adapter instance
        String jobName = getString(R.string.app_name) + " Document";
        PrintJob printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());

        // Save the job object for later status checking
//        mPrintJobs.add(printJob);
        printManager.getPrintJobs().add(printJob);

        Log.d(TAG, "createWebPrintJob: isCancelled" + printJob.isCancelled());
        Log.d(TAG, "createWebPrintJob: isCancelled" + printJob.isCancelled());
        Log.d(TAG, "createWebPrintJob: isCompleted" + printJob.isCompleted());
        Log.d(TAG, "createWebPrintJob: isFailed" + printJob.isFailed());
        Log.d(TAG, "createWebPrintJob: isStarted" + printJob.isStarted());
    }


    /****
     * 递归算法获取本地文件
     * @param path
     */
    private void doSearch(String path) {
        File file = new File(path);

        if (file.exists()) {
            if (file.isDirectory()) {
                File[] fileArray = file.listFiles();
                for (File f : fileArray) {

                    if (f.isDirectory()) {
                        doSearch(f.getPath());
                    } else {
                        if (f.getName().endsWith(".ppt") || f.getName().endsWith(".pptx") || f.getName().endsWith(".docx")
                                || f.getName().endsWith(".xls") || f.getName().endsWith(".doc") || f.getName().endsWith(".pdf")) {
                            try {
                                Log.d(TAG, "doSearch: " + f.getName() + "--" + f.getPath() + "--" + f.getAbsolutePath());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

}
