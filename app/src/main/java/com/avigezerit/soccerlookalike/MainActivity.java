package com.avigezerit.soccerlookalike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String TAG = MainActivity.class.getSimpleName();
    static final int numRQ = 2;
    static final int skinRQ = 3;

    TextView numTV;
    ImageView bodyIV;
    ImageView bodyIcon;
    ImageView shirtIcon;
    ImageView numberIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bodyIcon = (ImageView) findViewById(R.id.bodyIcon);
        bodyIV = (ImageView) findViewById(R.id.bodyIV);
        shirtIcon = (ImageView) findViewById(R.id.shirtIcon);
        ImageView hairIV = (ImageView) findViewById(R.id.hairIcon);
        numberIcon = (ImageView) findViewById(R.id.numIcon);
        numTV = (TextView) findViewById(R.id.numberTV);

        bodyIcon.setOnClickListener(this);
        shirtIcon.setOnClickListener(this);
        hairIV.setOnClickListener(this);
        numberIcon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bodyIcon:
//                String currentSkinColor = bodyIV.getDrawable().getCurrent().toString();
                Intent setSkinColorIntent = new Intent(MainActivity.this, SkinColorActivity.class);
//                setSkinColorIntent.putExtra("currentColor", currentSkinColor);
                startActivityForResult(setSkinColorIntent, skinRQ);
                Log.d(TAG, "Clicked body");
                break;
            case R.id.shirtIcon:
                //change shirt
                Log.d(TAG, "Clicked shirt");
                break;
            case R.id.hairIcon:
                //change hair
                Log.d(TAG, "Clicked hair");
                break;
            case R.id.numIcon:
                Intent setNumIntent = new Intent(MainActivity.this, NumberActivity.class);
                setNumIntent.putExtra("currentNumber", numTV.getText().toString());
                startActivityForResult(setNumIntent, numRQ);
                Log.d(TAG, "Clicked number");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        switch (requestCode) {
            case numRQ:
                switch (resultCode) {
                    case RESULT_OK:
                        String newNumOfPlayer = data.getStringExtra("number");
                        numTV.setText(newNumOfPlayer);
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Nothing changed", Toast.LENGTH_SHORT).show();
                        break;
                } break;
            case skinRQ:
                switch (resultCode) {
                    case RESULT_OK:
                        String newSkinColor = data.getStringExtra("color");
                        setNewSkinColor(newSkinColor);
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Nothing changed", Toast.LENGTH_SHORT).show();
                        break;
                } break;
        }
    }


//    public void currentSkinColorChecker (ImageView imageView){
//
//        String current = bodyIV.getResources().getResourceEntryName((bodyIV.getId()));
//
//        switch (current){
//            case "skin_color1":
//                Log.d(TAG, "skin color 1");
//                break;}
////        bodyIV.setTag(R.drawable.skin_color1);
////        int skinDrawableId = (Integer)bodyIV.getTag();
//    }


    public void setNewSkinColor(String newSkinColor) {
        switch (newSkinColor) {
            case "bright":
                bodyIV.setImageResource(R.drawable.body_base_skint1);
                break;
            case "mid":
                bodyIV.setImageResource(R.drawable.body_base_skint2);
                break;
            case "dark":
                bodyIV.setImageResource(R.drawable.body_base_skint3);
                break;
        }
    }
}