package com.example.bottom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuPage extends AppCompatActivity {

    private ContactAdapter_Menu adapter2;
    private RecyclerView contactlist2;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_page);

        adapter2 = new ContactAdapter_Menu(new ArrayList<Contact_Menu>(), this);
        contactlist2 = findViewById(R.id.recyclerView);

        (new ConnectMysql2()).execute("http://gesticulatory-conta.000webhostapp.com/php/list_contacts2.php");


        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contactlist2.setLayoutManager(layoutManager);
        contactlist2.setAdapter(adapter2);

    }
    public class ConnectMysql2 extends AsyncTask<String, Void, List<Contact_Menu>> {

        private final ProgressDialog dialog = new ProgressDialog(MenuPage.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("資料下載中...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(List<Contact_Menu> menus) {
            super.onPostExecute(menus);
            dialog.dismiss();
            adapter2.setItemList(menus);
            adapter2.notifyDataSetChanged();


        }

        @Override
        protected List<Contact_Menu> doInBackground(String... strings) {
            List<Contact_Menu> result = new ArrayList<Contact_Menu>();
            URL u = null;
            try{
                u = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                //讀取網頁上的資料
                InputStream is2 = conn.getInputStream();
                byte[] b2 = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ( is2.read(b2) != -1)
                    baos.write(b2);
                String JSONResp = new String(baos.toByteArray());
                Log.i("JSONResp4=", JSONResp);
                //將資料轉換成陣列並加入result
                JSONArray arr = new JSONArray(JSONResp);


                for(int i =0; i <1 ; i++){
                    if(arr.getJSONObject(i) != null){
                        result.add(convertMenu(arr.getJSONObject(i)));
                        Log.v("data=", arr.getJSONObject(i).toString());
                    }

                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        //將json物件轉成自訂的Contact資料格式
        private Contact_Menu convertMenu(JSONObject obj) throws JSONException {

            String id = obj.getString("ContactID");
            String breakfast = obj.getString("Breakfast");
            String lunch = obj.getString("Lunch");
            String dinner = obj.getString("Dinner");
            String date = obj.getString("Date");

            return new Contact_Menu(id, date, breakfast, lunch, dinner);
        }

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
    //intent跳轉activity共用函式
    public void changeView(Context context, Class<?> cla){

        intent = intent.setClass(context, cla);
        startActivity(intent);
        this.finish();
    }
}
