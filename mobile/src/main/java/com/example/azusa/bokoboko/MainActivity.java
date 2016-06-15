package com.example.azusa.bokoboko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
/**
 * Created by AZUSA on 2016/06/15.
 */
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnDisp = (Button)findViewById(R.id.btnDisp);
        btnDisp.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.azusa.bokoboko", "com.example.azusa.bokoboko.MyActivity");
                startActivity(intent);
            }
        });
        Button Button = (Button)findViewById(R.id.button);
        Button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent();
                intent.setClassName("com.example.azusa.bokoboko", "com.example.azusa.bokoboko.MyActivity1");
                startActivity(intent);
            }
        });
    }
}