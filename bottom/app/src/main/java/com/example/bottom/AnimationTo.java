package com.example.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AnimationTo extends AppCompatActivity {

    private ImageView health ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        final Animation fadein= AnimationUtils.loadAnimation(AnimationTo.this,R.anim.fade_in);
        Animation fadeout= AnimationUtils.loadAnimation(AnimationTo.this,R.anim.fade_out);

        health=findViewById(R.id.health);
        health.setAnimation(fadein);

//        Thread thread2=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                    try {Thread.sleep(3000);}
//                    catch (Exception e) {e.printStackTrace();}
//                    finally {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
//                        run();
//                    }
//
//            }
//        });
//        thread2.start();



        //logo.setAnimation(fadeout);
        Thread timer= new Thread()

        {
            @Override
            public void run() {
                super.run();

                try {
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally
                {   overridePendingTransition(android.R.anim.fade_out,android.R.anim.fade_out);
                    //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_in);
                    startActivity(new Intent(AnimationTo.this,Main_Home.class));
                    finish();

                }


            }
        };
        timer.start();
    }
}