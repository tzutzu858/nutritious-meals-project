package com.example.bottom.ui.nutritional_menu;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bottom.Contact;
import com.example.bottom.ContactAdapter;
import com.example.bottom.MenuPage;
import com.example.bottom.R;

import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {
    private ContactAdapter adapter;
    private ListView contactlist;
    private Intent intent;


    public void onStart() {
        super.onStart();

        adapter = new ContactAdapter(new ArrayList<Contact>(), getActivity());
        contactlist = getView().findViewById(R.id.listview);

        contactlist.setAdapter(adapter);
        (new ConnectMysql()).execute("http://gesticulatory-conta.000webhostapp.com/php/list_contacts.php");
        contactlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent();
                intent.putExtra("id",adapter.getItem(position).getId());

                intent.setClass(getActivity(), MenuPage.class);
                startActivity(intent);
            }
        });
    }
    private class ConnectMysql extends AsyncTask<String, Void, List<Contact>> {
        private final ProgressDialog dialog = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("資料下載中...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            super.onPostExecute(contacts);
            dialog.dismiss();
            adapter.setItemList(contacts);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected List<Contact> doInBackground(String... strings) {
            List<Contact> result = new ArrayList<Contact>();
            URL u = null;
            try{
                u = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                //讀取網頁上的資料
                InputStream is = conn.getInputStream();
                byte[] b = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ( is.read(b) != -1)
                    baos.write(b);
                String JSONResp = new String(baos.toByteArray());
                Log.i("JSONResp=", JSONResp);
                //將資料轉換成陣列並加入result
                JSONArray arr = new JSONArray(JSONResp);
                //列出最新三筆資料,之後可以設定變數,讓使用者自己決定要列出最新幾筆資料
                for(int i =arr.length()-7; i <arr.length() ; i++){
                    if(arr.getJSONObject(i) != null){
                        result.add(convertContact(arr.getJSONObject(i)));
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
        private Contact convertContact(JSONObject obj) throws JSONException {
            Bitmap bitmap;
            if(obj.getString("Picture") != null){
                bitmap = LoadImage("https://gesticulatory-conta.000webhostapp.com/php/upload/" + obj.getString("Picture").toString());
            }else{
                bitmap = LoadImage("https://gesticulatory-conta.000webhostapp.com/php/upload/supportmale.png");
            }
            String id = obj.getString("ContactID");
            String pic_filename = obj.getString("Picture");
            String name = obj.getString("Name");
            String phone = obj.getString("Phone");
            String email = obj.getString("Email");
            String birthday = obj.getString("Birthday");
            String address = obj.getString("Address");
            return new Contact(id, pic_filename, bitmap, name, phone, email, birthday, address);
        }
        //連線網路圖片位址並轉成bitmap
        private Bitmap LoadImage(String imageUrl){
            Bitmap bitmap = null;
            try{
                URL url = new URL(imageUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();

                    bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    return bitmap;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
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

//        switch(id){
//            case R.id.action_add:
//                intent = new Intent();
//                intent.putExtra("title","新增好友");
//                intent.putExtra("type","new");
//
//                changeView(this, MenuPage.class);
//                break;
//        }

        return super.onOptionsItemSelected(item);
    }
    //intent跳轉activity共用函式
    public void changeView(Context context, Class<?> cla){

        intent = intent.setClass(context, cla);
        startActivity(intent);
        getActivity().finish();
    }


    private nutritionalViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(nutritionalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        return root;
    }
}