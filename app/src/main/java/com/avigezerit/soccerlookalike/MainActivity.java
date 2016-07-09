package com.avigezerit.soccerlookalike;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bodyIV = (ImageView) findViewById(R.id.bodyIV);
        ImageView shirtIV = (ImageView) findViewById(R.id.shirtIV);
        ImageView hairIV = (ImageView) findViewById(R.id.hairIV);
        TextView nameTV = (TextView) findViewById(R.id.nameTV);
        TextView numberTV = (TextView) findViewById(R.id.numberTV);

        bodyIV.setOnClickListener(this);
        shirtIV.setOnClickListener(this);
        hairIV.setOnClickListener(this);
        nameTV.setOnClickListener(this);
        numberTV.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bodyIV:
                //skin tone bar
                Log.d(TAG, "Clicked body");
                break;
            case R.id.shirtIV:
                //change shirt
                Log.d(TAG, "Clicked shirt");
                break;
            case R.id.hairIV:
                //change hair
                Log.d(TAG, "Clicked hair");
                break;
            case R.id.nameTV:
                //change name
                Log.d(TAG, "Clicked name");
                break;
            case R.id.numberTV:
                //change number
                Log.d(TAG, "Clicked number");
                break;
        }


    }
}
