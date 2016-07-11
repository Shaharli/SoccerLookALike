package com.avigezerit.soccerlookalike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumberActivity extends AppCompatActivity implements View.OnClickListener {

    static final String TAG = NumberActivity.class.getSimpleName();
    public DisplayMetrics dm = new DisplayMetrics();

    TextView alertError;
    EditText newNum;
    Button setNumBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w * .8), (int) (h * .6));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        newNum = (EditText) findViewById(R.id.newNumET);
        String currentNum = getIntent().getStringExtra("currentNumber");
        newNum.setHint(currentNum);
        alertError = (TextView) findViewById(R.id.alertError);
        setNumBtn = (Button) findViewById(R.id.setNumBtn);
        setNumBtn.setOnClickListener(this);
        newNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    isNumberVaild();
                    Intent backAndsetNewNum = new Intent();
                    backAndsetNewNum.putExtra("number", newNum.getText().toString());
                    setResult(Activity.RESULT_OK, backAndsetNewNum);
                    finish();
                }
                return false;
            }
        });
        Button cancelNumBtn = (Button) findViewById(R.id.cancelNumBtn);
        cancelNumBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent backAndsetNewNum = new Intent();
        switch (v.getId()) {
            case R.id.setNumBtn:
                isNumberVaild();
                backAndsetNewNum.putExtra("number", newNum.getText().toString());
                setResult(Activity.RESULT_OK, backAndsetNewNum);
                break;
            case R.id.cancelNumBtn:
                setResult(Activity.RESULT_CANCELED, backAndsetNewNum);
                break;
        }
        finish();
    }

    public boolean isNumberVaild() {
        if (newNum.getText().toString() == null || newNum.getText().toString().contains("0") || newNum.getText().toString().length() > 3) {
            alertError.setText("Check the number again");
            return false;
        } else return true;
    }


}

