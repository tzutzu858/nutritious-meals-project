package com.example.bottom;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bottom.ui.home.HomeFragment;
import com.example.bottom.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity  {
    AlertDialog.Builder builder=null;
    private TextView tvtitle;
    Intent intent;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//取得自訂 Layout_bartitle的TextVeiw 物件，設定ToolBar的標題
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.bartitle, null);
        tvtitle = view.findViewById(R.id.tvtitle);

        //設定自訂   ToolBar樣式
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false); //隱藏ToolBar左上標題
        actionBar.setLogo(R.drawable.logo_design); //設定左上Icon
        actionBar.setDisplayUseLogoEnabled(true);//顯示LOGO(icon)
        actionBar.setDisplayShowHomeEnabled(true);//顯示左上Icon
        //actionBar.setCustomView(view); // 設置自訂layout(view)來顯示中間標題
        actionBar.setDisplayShowCustomEnabled(true);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //加入menu資料夾底下的bottom_nav_menu.xml裡面所新增的item的id
        //和在navigation資料夾底下的mobile_navigation.xml所新增destination的Fragment的id必須一致
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.testtest1,R.id.connection)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(MainActivity.this)
            .setTitle("確認視窗")
            .setMessage("確定要結束應用程式嗎？")
            .setIcon(R.mipmap.icon_bird)
            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            })
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder builder;
        Dialog dialogMenu;

        switch (item.getItemId()){
            case R.id.setting:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("好康通知")
                        .setIcon(R.mipmap.bell)
                        .setMessage("現在選三期營養課程，打五折" +
                                "活動優惠到12/31，名額有限要搶要快！！")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                dialogMenu = builder.create();
                dialogMenu.show();
                break;


            case R.id.action_setting:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("設定")
                        .setIcon(R.mipmap.bell)
                        .setMessage("請問您是否要離開此APP，前往手機設定介面？")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                intent = new Intent(Settings.ACTION_SETTINGS);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });;
                dialogMenu = builder.create();
                dialogMenu.show();
                break;

            case R.id.action_about:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("關閉APP")
                        .setIcon(R.mipmap.bell)
                        .setMessage("請問您是否要關閉APP")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAndRemoveTask();
                                Toast.makeText(MainActivity.this,"已離開，期待與您的再度見面～～", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });;
                dialogMenu = builder.create();
                dialogMenu.show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

}
