package com.avigezerit.soccerlookalike;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    static final String TAG = MainActivity.class.getSimpleName();
    static final int numRQ = 2;
    static final int skinRQ = 3;
    static final int shirtRQ = 4;

    TextView numTV;
    ImageView bodyIV;
    ImageView bodyIcon;
    ImageView shirtIcon;
    ImageView shirtIV;
    ImageView numberIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bodyIcon = (ImageView) findViewById(R.id.bodyIcon);
        bodyIV = (ImageView) findViewById(R.id.bodyIV);
        bodyIV.setTag(R.drawable.body_base_skint1);
        shirtIcon = (ImageView) findViewById(R.id.shirtIcon);
        shirtIV = (ImageView) findViewById(R.id.shirtIV);
        shirtIV.setTag(R.drawable.shirt_juve);
        ImageView hairIV = (ImageView) findViewById(R.id.hairIcon);
        numberIcon = (ImageView) findViewById(R.id.numIcon);
        numTV = (TextView) findViewById(R.id.numberTV);

        bodyIcon.setOnClickListener(this);
        shirtIcon.setOnClickListener(this);
        hairIV.setOnClickListener(this);
        numberIcon.setOnClickListener(this);

        if (savedInstanceState != null){
            numTV.setText(savedInstanceState.getString("number"));
            bodyIV.setImageResource(savedInstanceState.getInt("body"));
            shirtIV.setImageResource(savedInstanceState.getInt("shirt"));
        }

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
                Intent setShirtIntent = new Intent(MainActivity.this, ShirtActivity.class);
                startActivityForResult(setShirtIntent, shirtRQ);
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
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                super.onBackPressed();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            //number
            case numRQ:
                switch (resultCode) {
                    case RESULT_OK:
                        String newNumOfPlayer = data.getStringExtra("number");
                        numTV.setText(newNumOfPlayer);
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Nothing changed", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;

            //skin color
            case skinRQ:
                switch (resultCode) {
                    case RESULT_OK:
                        String newSkinColor = data.getStringExtra("color");
                        setNewSkinColor(newSkinColor);
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Nothing changed", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;

            //shirt
            case shirtRQ:
                switch (resultCode) {
                    case RESULT_OK:
                        String newShirt = data.getStringExtra("shirt");
                        setNewShirt(newShirt);
                        break;

                }
        }
    }


    public void setNewSkinColor(String newSkinColor) {
        switch (newSkinColor) {
            case "bright":
                bodyIV.setImageResource(R.drawable.body_base_skint1);
                bodyIV.setTag(R.drawable.body_base_skint1);
                break;
            case "mid":
                bodyIV.setImageResource(R.drawable.body_base_skint2);
                break;
            case "dark":
                bodyIV.setImageResource(R.drawable.body_base_skint3);
                break;
        }
    }

    public void setNewShirt(String newShirt) {
        switch (newShirt) {
            case "Juventus":
                shirtIV.setImageResource(R.drawable.shirt_juve);
                shirtIV.setTag(R.drawable.shirt_juve);
                break;
            case "Barcelona":
                shirtIV.setImageResource(R.drawable.shirt_barcelona);
                shirtIV.setTag(R.drawable.shirt_barcelona);
                break;
            case "Bayren":
                shirtIV.setImageResource(R.drawable.shirt_bayern);
                shirtIV.setTag(R.drawable.shirt_bayern);
                break;
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog dontExit = new AlertDialog.Builder(MainActivity.this).create();
        dontExit.setTitle("So soon?");
        dontExit.setMessage("Are you sure you want to leave?");
        dontExit.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", this);
        dontExit.setButton(DialogInterface.BUTTON_NEGATIVE, "No", this);

        dontExit.show();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("number", numTV.getText().toString());
        outState.putInt("body", (Integer) (bodyIV.getTag()));
        outState.putInt("shirt", ((Integer) shirtIV.getTag()));
        super.onSaveInstanceState(outState);
    }
}