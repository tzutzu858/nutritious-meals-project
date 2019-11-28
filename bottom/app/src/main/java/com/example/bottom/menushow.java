package com.example.bottom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class menushow extends AppCompatActivity {
    private menushowAdapter adapter;
    private RecyclerView recyclerView;
    public String mDATE,mTEL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menushow);

        adapter=new menushowAdapter(new ArrayList<menushowContact>(),this);
        recyclerView=findViewById(R.id.mrecycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

        Intent it=getIntent();
        Bundle bundle=it.getExtras();
        mTEL=bundle.getString("TEL");
        mDATE=bundle.getString("DATE");

        Log.d("HHH=",mDATE);

        (new ConnectMysql()).execute("http://andyetw.000webhostapp.com/list_menu.php");
        //final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(adapter);

    }

    private class ConnectMysql extends AsyncTask<String,Void,List<menushowContact>>{
        private final ProgressDialog dialog = new ProgressDialog(menushow.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("資料下載中...");
            dialog.show();
        }
        @Override
        protected void onPostExecute(List<menushowContact>contacts ) {
            super.onPostExecute(contacts);
            dialog.dismiss();
            adapter.setItemList(contacts);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected List<menushowContact> doInBackground(String... strings) {
            List<menushowContact> result=new ArrayList<menushowContact>();
            URL u=null;
            try {
                u=new URL(strings[0]);
                HttpURLConnection conn=(HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                InputStream is=conn.getInputStream();
                byte[] b=new byte[1024];
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                while (is.read(b)!=-1) baos.write(b);
                String JSONResp=new String(baos.toByteArray());
                Log.i("JSONResp=",JSONResp);

                JSONArray arr=new JSONArray(JSONResp);
                for (int i=0;i<arr.length();i++){
                    if(arr.getJSONObject(i)!=null
                            && arr.getJSONObject(i).getString("eatdate").equals(mDATE)
                            && arr.getJSONObject(i).getString("tel1").equals(mTEL)
                    ){
                        result.add(convertContact(arr.getJSONObject(i)));
                        Log.v("BBB=",arr.toString());
                    }
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        private menushowContact convertContact(JSONObject obj) throws JSONException{
            String id1=obj.getString("id");
            String tel=obj.getString("tel1");
            String eatdate=obj.getString("eatdate");
            String when=obj.getString("radio1");
            String dish1=obj.getString("dish1");
            String dish2=obj.getString("dish2");
            String dish3=obj.getString("dish3");
            String dish4=obj.getString("dish4");
            String dish5=obj.getString("dish5");

            Log.d("AAA=",id1+tel+eatdate+when+dish1+dish2+dish3+dish4+dish5);

            return new menushowContact(id1,tel,eatdate,when,dish1,dish2,dish3,dish4,dish5);
        }

    }

}
