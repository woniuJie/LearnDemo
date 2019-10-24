package com.zsj.learndemo.zxingimage;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zsj.learndemo.R;

public class ZxingImageActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_image);

        editText = findViewById(R.id.edit_image);
        imageView = findViewById(R.id.iv_iamge);
        button = findViewById(R.id.bu_zxing_image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textContent = editText.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    return;
                }
                editText.setText("");
                Bitmap mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
                imageView.setImageBitmap(mBitmap);
            }
        });


    }
}
