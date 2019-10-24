package com.zsj.learndemo.wuziqi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zsj.learndemo.R;
import com.zsj.learndemo.utils.ToastUtils;

public class WuziqiActivity extends AppCompatActivity {
    WuziqiPanel wuziqiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuziqi);
        wuziqiPanel = findViewById(R.id.id_wuziqi);
        wuziqiPanel.restartGame();

        wuziqiPanel.setOnGameStatusChangeListener(new OnGameStatusChangeListener() {
            @Override
            public void onGameOver(int gameWinResult) {
                switch (gameWinResult) {
                    case WuziqiPanel.BLACK_WIN:
                        ToastUtils.showToast(WuziqiActivity.this, "黑棋胜", Toast.LENGTH_LONG);
//                        wuziqiPanel.restartGame();

                        break;
                    case WuziqiPanel.WHITE_WIN:
                        ToastUtils.showToast(WuziqiActivity.this, "白棋胜", Toast.LENGTH_LONG);
//                        wuziqiPanel.restartGame();

                        break;
                    case WuziqiPanel.NO_WIN:
                        ToastUtils.showToast(WuziqiActivity.this, "和棋", Toast.LENGTH_LONG);
                        wuziqiPanel.restartGame();

                        break;

                }
            }
        });
    }
}
