package com.example.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class BmiCommomActivity extends AppCompatActivity {
Button mbtnBack2,mbtnHome2;
ImageView mimgView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_bmi_commom);
        mbtnBack2 = findViewById (R.id.btnBack2);
        mbtnHome2 = findViewById (R.id.btnHome2);
        mimgView1 = (ImageView) findViewById (R.id.imgView1);

        mbtnBack2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (BmiCommomActivity.this,MainActivity.class);
                startActivity (intent);
            }
        });
        mbtnHome2.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( BmiCommomActivity.this,test1.class);
                startActivity (intent);
            }
        });
    }
}