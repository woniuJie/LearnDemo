package com.zsj.learndemo.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.google.zxing.client.result.VINParsedResult;
import com.zsj.learndemo.R;
import com.zsj.learndemo.print.aa.Text;

import java.io.File;
import java.util.ArrayList;

public class ShareActivity extends AppCompatActivity {
    ArrayList<Uri> imageUris = new ArrayList<>();
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        textView = findViewById(R.id.tv_share_image);



    }

    public void onShareText(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "张士杰");
        intent.setType("text/plain");
        startActivity(intent);
    }

    public void onShareText1(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "张士杰");
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "hhh"));
    }

    public void onShareImage(View view) {
        //从相册选取的情况
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pickIntent, 1);
    }

    public void onShareImageMultiple(View view) {

        //从相册选取的情况
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pickIntent, 2);

    }

    public void onShareImageMore(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,imageUris);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent,"zsj"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.main, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
//        mShareActionProvider = MenuItemCompat.getActionProvider(item);

        // Return true to display menu
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item_share) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1){
                Uri uri = data.getData();
                Log.d("zsj", "onActivityResult: "+uri);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM,uri);
                intent.setType("image/jpeg");
                startActivity(Intent.createChooser(intent,"hhh"));
            }else if(requestCode == 2){
                imageUris.add(data.getData());
                textView.append(data.getDataString());
                textView.append("\n");
            }
        }
    }
}
