package com.example.bottom.ui.health;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import com.example.bottom.BmiCommomActivity;
import com.example.bottom.BmrCommom;
import com.example.bottom.R;
import com.example.bottom.ReeCommomActivity;
import com.example.bottom.test1;

public class health extends Fragment {
    int pos1 = 230, pos2 = 150, pos3 = 150;
    float w, h;
    int age;
    ConstraintLayout mbackround;
     SeekBar msb1, msb2, msb3;
    EditText met1, met2, met3, met4, met5,met6;
    Button mbtnBack1, mbtnbmi, mbtnHome1, mbtnBmr,mbtnRee;
    RadioGroup mrgsex;
    float bmr = 0,ree = 0;

//需要重寫onStart方法，所有的控制元件放到這裡面初始化。
//不能用下面四行程式
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.luckyactivity_main);

    public void onStart() {
        super.onStart();
       // TextView textView = getView().findViewById(R.id.text_test);

        //mbackround = getView().findViewById (R.id.background);
        msb1 = getView().findViewById (R.id.sb1);
        msb2 = getView().findViewById (R.id.sb2);
        msb3 = getView().findViewById (R.id.sb3);
        met1 = getView().findViewById (R.id.et1);
        met2 = getView().findViewById (R.id.et2);
        met3 = getView().findViewById (R.id.et3);
        met4 = getView().findViewById (R.id.et4);
        met5 = getView().findViewById (R.id.et5);
        met6 = getView().findViewById (R.id.et6);
        mbtnBack1 = getView().findViewById (R.id.btnBack1);
        mbtnbmi = getView().findViewById (R.id.btnBmi);
        mbtnHome1 = getView().findViewById (R.id.btnHome1);
        mbtnBmr = getView().findViewById (R.id.btnBmr);
        mbtnRee = getView().findViewById (R.id.btnRee);
        mrgsex = getView().findViewById (R.id.rgSex);


            msb1.setProgress(pos1);
            msb2.setProgress(pos2);
            msb3.setProgress(pos3);

        msb1.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener ( ) {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                met1.setText (String.valueOf (progress));
                pos1 = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                float w = Float.valueOf (met2.getText ( ).toString ( ));
                float h = Float.valueOf (met1.getText ( ).toString ( ));
                float bmi = w / (h * h / 10000);
                met3.setText (String.valueOf (bmi));

                switch (mrgsex.getCheckedRadioButtonId ( )) {
                    case R.id.rbSex1: {
                        bmr = (float) ((13.7 * w) + (5 * h) - (6.8 * age) + 66);
                        ree = (float)((10 * w) +(6.25 * h) - (5 * age) + 5);
                    }
                    met5.setText (String.valueOf (bmr));
                    met6.setText (String.valueOf (ree));
                    break;

                    case R.id.rbSex2: {
                        bmr = (float) ((9.6 * w) + (1.8 * h) - (4.7 * age) + 655);
                        ree = (float)((10 * w) +(6.25 * h) - (5 * age) - 161);
                    }
                    met5.setText (String.valueOf (bmr));
                    met6.setText (String.valueOf (ree));
                    break;
                }


//                if (String.valueOf (mrgsex) == "男")
//                { bmr =(float)((13.7*w)+(5*h)-(6.8*age)+66);}
//                else
//                {bmr =(float)((9.6*w)+(1.8*h)-(4.7*age)+655);}
//                met5.setText (String.valueOf (bmr));
            }
        });
        msb2.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener ( ) {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress;
                met2.setText (String.valueOf (progress));
                pos2 = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                w = Float.valueOf (met2.getText ( ).toString ( ));
                h = Float.valueOf (met1.getText ( ).toString ( ));
                float bmi = w / (h * h / 10000);
                met3.setText (String.valueOf (bmi));
                //met3.setText(seekBar.getProgress ( ));

                switch (mrgsex.getCheckedRadioButtonId ( )) {
                    case R.id.rbSex1: {
                        bmr = (float) ((13.7 * w) + (5 * h) - (6.8 * age) + 66);
                        ree = (float)((10 * w) +(6.25 * h) - (5 * age) + 5);
                    }
                    met5.setText (String.valueOf (bmr));
                    met6.setText (String.valueOf (ree));
                    break;

                    case R.id.rbSex2: {
                        bmr = (float) ((9.6 * w) + (1.8 * h) - (4.7 * age) + 655);
                        ree = (float)((10 * w) +(6.25 * h) - (5 * age) - 161);
                    }
                    met5.setText (String.valueOf (bmr));
                    met6.setText (String.valueOf (ree));
                    break;
                }


//                if (String.valueOf (mrgsex) == "男")
//                { bmr =(float)((13.7*w)+(5*h)-(6.8*age)+66);}
//                else
//                {bmr =(float)((9.6*w)+(1.8*h)-(4.7*age)+655);}
//                met5.setText (String.valueOf (bmr));
            }
        });
        mbtnbmi.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), BmiCommomActivity.class);
                startActivity (intent);

            }
        });

        msb3.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener ( ) {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progres, boolean fromUser) {
                progress = progres;
                met4.setText (String.valueOf (progress));
                pos3 = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                age = progress;
                Log.d ("age值", String.valueOf (age));
                switch (mrgsex.getCheckedRadioButtonId ( )) {
                    case R.id.rbSex1: {
                        bmr = (float) ((13.7 * w) + (5 * h) - (6.8 * age) + 66);
                        ree = (float)((10 * w) +(6.25 * h) - (5 * age) + 5);
                    }
                    met5.setText (String.valueOf (bmr));
                    met6.setText (String.valueOf (ree));
                    break;

                    case R.id.rbSex2: {
                        bmr = (float) ((9.6 * w) + (1.8 * h) - (4.7 * age) + 655);
                        ree = (float)((10 * w) +(6.25 * h) - (5 * age) - 161);
                    }
                    met5.setText (String.valueOf (bmr));
                    met6.setText (String.valueOf (ree));
                    break;
                }


//                if (String.valueOf (mrgsex) == "男")
//                    { bmr =(float)((13.7*w)+(5*h)-(6.8*age)+66);}
//                else
//                    {bmr =(float)((9.6*w)+(1.8*h)-(4.7*age)+655);}
//                met5.setText (String.valueOf (bmr));
            }
        });
        mbtnBmr.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), BmrCommom.class);
                startActivity (intent);
            }


        });
        mbtnRee.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), ReeCommomActivity.class);
                startActivity (intent);
            }
        });
        mbtnBack1.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(), test1.class);
                startActivity (intent);
            }
        });
        mbtnHome1.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity(),test1.class);
                startActivity (intent);
            }
        });


    }

    private healthViewModel testViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        testViewModel =
                ViewModelProviders.of(this).get(healthViewModel.class);
        View root = inflater.inflate(R.layout.fragment_health, container, false);
        //final TextView textView = root.findViewById(R.id.health);
        //上面這行textView所要找的ID是要找到layout這個資料夾下相對應的xml(fragment_testtest.xml)裡面的textView的ID
//        testViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}