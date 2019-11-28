package com.example.bottom.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bottom.R;
import com.example.bottom.menushow;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Bundle bundle;
    String nameS,emailS;
    Intent it;
    Bundle bundle1=new Bundle();
    EditText mTel,mDate;
    Button mBtn;

    public void onStart() {
        super.onStart();
        it = new Intent(getActivity(), menushow.class);
        mTel=getView().findViewById(R.id.tel);
        mDate=getView().findViewById(R.id.date);

        mBtn=getView().findViewById(R.id.btn);
        mBtn.setOnClickListener(btnON);

    }

    private View.OnClickListener btnON=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String TEL=mTel.getText().toString();
            String DATE=mDate.getText().toString();
            bundle1.putString("TEL", TEL);
            bundle1.putString("DATE",DATE);
            it.putExtras(bundle1);

            Log.d("KKK=",DATE);

            startActivity(it);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getActivity().getIntent().getExtras();
        Log.v("getName",bundle.getString("name"));
        Log.v("getEmail",bundle.getString("email"));
        nameS = bundle.getString("name");
        emailS = bundle.getString("email");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
         TextView textView = root.findViewById(R.id.text_home);
         TextView textView2 = root.findViewById(R.id.textView19);
        textView.setText(nameS);
        textView2.setText(emailS);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}