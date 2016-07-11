package com.avigezerit.soccerlookalike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

public class SkinColorActivity extends AppCompatActivity implements  View.OnClickListener {

    static final String TAG = SkinColorActivity.class.getSimpleName();
    public DisplayMetrics dm = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_color_btns);

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w * .8), (int) (h * .6));

    }

    @Override
    public void onClick(View v) {
        Intent backAndSetNewSkinColor = new Intent();
        backAndSetNewSkinColor.putExtra("color", v.getId());
        setResult(Activity.RESULT_OK, backAndSetNewSkinColor);
        finish();
    }


}
