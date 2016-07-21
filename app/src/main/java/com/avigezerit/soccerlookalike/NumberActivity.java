package com.avigezerit.soccerlookalike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class NumberActivity extends AppCompatActivity implements View.OnClickListener , NumberPicker.OnValueChangeListener {

    static final String TAG = NumberActivity.class.getSimpleName();
    public DisplayMetrics dm = new DisplayMetrics();

    Button setNumBtn;
    NumberPicker np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_number);

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w * .8), (int) (h * .6));

        int currentNumber = Integer.parseInt(getIntent().getStringExtra("currentNumber"));

        np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMaxValue(99);
        np.setMinValue(1);
        np.setValue(currentNumber);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);


        setNumBtn = (Button) findViewById(R.id.setNumBtn);
        setNumBtn.setOnClickListener(this);

        Button cancelNumBtn = (Button) findViewById(R.id.cancelNumBtn);
        cancelNumBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent backAndsetNewNum = new Intent();
        switch (v.getId()) {
            case R.id.setNumBtn:
                backAndsetNewNum.putExtra("number", np.getValue());
                setResult(Activity.RESULT_OK, backAndsetNewNum);
                break;
            case R.id.cancelNumBtn:
                setResult(Activity.RESULT_CANCELED, backAndsetNewNum);
                break;
        }
        finish();
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("value is",""+newVal);
    }


}

