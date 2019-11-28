package com.example.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReeCommomActivity extends AppCompatActivity {
    Button mbtnBack4,mbtnHome4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_ree_commom);
        mbtnBack4 = findViewById (R.id.btnBack4);
        mbtnHome4 = findViewById (R.id.btnHome4);
        mbtnBack4.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ReeCommomActivity.this,MainActivity.class);
                startActivity (intent);
            }

        });
        mbtnHome4.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ReeCommomActivity.this,test1.class);
                startActivity (intent);
            }
        });
    }
}
