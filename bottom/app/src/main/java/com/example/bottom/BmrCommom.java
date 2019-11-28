package com.example.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BmrCommom extends AppCompatActivity {
    Button mbtnBack3, mbtnHome3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_bmr_commom);
        mbtnBack3 = findViewById (R.id.btnBack3);
        mbtnHome3 = findViewById (R.id.btnHome3);
        mbtnBack3.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (BmrCommom.this, MainActivity.class);
                startActivity (intent);
            }
        });
        mbtnHome3.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (BmrCommom.this,test1.class );
                startActivity (intent);
            }
        });
    }
}