package com.zsj.learndemo.markdown;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.zsj.learndemo.R;
import com.zsj.learndemo.markdown.richedit.RichEditor;

public class MarkdownActivity extends AppCompatActivity {

    private RichEditor richEditor;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdown);

        richEditor = findViewById(R.id.rich_write_article_content);
        imageView = findViewById(R.id.iv_write_article_bold);

        richEditor.setEditorFontSize(ScreenUtils.dpToPxInt(5));
        richEditor.setPlaceholder("输入内容");
        richEditor.setTextColor(getResources().getColor(R.color.colorPrimary));
        richEditor.setPadding(ScreenUtils.dpToPxInt(8), ScreenUtils.dpToPxInt(8), ScreenUtils.dpToPxInt(8), ScreenUtils.dpToPxInt(8));
        richEditor.focusEditor();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加粗
                if (imageView.isSelected()) {
                    imageView.setSelected(false);
                } else {
                    imageView.setSelected(true);
                }
                richEditor.setBold();
            }
        });

        richEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                Log.d("zsj", "onTextChange: "+text);
            }
        });
    }
}
