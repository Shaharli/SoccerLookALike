package com.avigezerit.soccerlookalike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SkinColorActivity extends AppCompatActivity implements View.OnClickListener {

    static final String TAG = SkinColorActivity.class.getSimpleName();
    RadioGroup skinColorRG;
    RadioButton selectedSkinColor;
    public DisplayMetrics dm = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_color);

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w * .8), (int) (h * .6));

        skinColorRG = (RadioGroup) findViewById(R.id.skin_color_RG);
        ((RadioButton) findViewById(R.id.skin_color_rb1)).setChecked(true);
        Button setColor = (Button) findViewById(R.id.setColorBtn);
        Button cancelColor = (Button) findViewById(R.id.cancelColorBtn);
        setColor.setOnClickListener(this);
        cancelColor.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent backAndSetNewSkinColor = new Intent();
        switch (v.getId()){
            case R.id.setColorBtn:
                int selectedId = skinColorRG.getCheckedRadioButtonId();
                selectedSkinColor = (RadioButton) findViewById(selectedId);
                backAndSetNewSkinColor.putExtra("color", selectedSkinColor.getText());
                setResult(Activity.RESULT_OK, backAndSetNewSkinColor);
                break;
            case R.id.cancelColorBtn:
                setResult(Activity.RESULT_CANCELED, backAndSetNewSkinColor);
                break;
        } finish();


//        int border = 3;
//        skinColor1IV.setPadding(border,border,border,border);
//        skinColor1IV.setBackgroundColor(Color.GREEN);


    }
}
