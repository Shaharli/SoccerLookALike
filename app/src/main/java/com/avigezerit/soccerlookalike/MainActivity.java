package com.avigezerit.soccerlookalike;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    int newSkinColorId;

    String currentSkinColor;
    String currentTeamShirt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bodyIcon = (ImageView) findViewById(R.id.bodyIcon);
        bodyIV = (ImageView) findViewById(R.id.bodyIV);
        setCurrentSkinColor("default");
        shirtIcon = (ImageView) findViewById(R.id.shirtIcon);
        shirtIV = (ImageView) findViewById(R.id.shirtIV);
        setCurrentTeamShirt("default");
        ImageView hairIV = (ImageView) findViewById(R.id.hairIcon);
        numberIcon = (ImageView) findViewById(R.id.numIcon);
        numTV = (TextView) findViewById(R.id.numberTV);

        Button goBtn = (Button) findViewById(R.id.goBtn);
        goBtn.setOnClickListener(this);

        bodyIcon.setOnClickListener(this);
        shirtIcon.setOnClickListener(this);
        hairIV.setOnClickListener(this);
        numberIcon.setOnClickListener(this);

        // TODO: 11/07/2016 : figure out how reverse images source
//        if (savedInstanceState != null){
//            numTV.setText(savedInstanceState.getString("number"));
//            bodyIV.setImageResource(savedInstanceState.getInt("body"));
//            shirtIV.setImageResource(savedInstanceState.getInt("shirt"));
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bodyIcon:
                Intent setSkinColorIntent = new Intent(MainActivity.this, SkinColorActivity.class);
                startActivityForResult(setSkinColorIntent, skinRQ);
                break;
            case R.id.shirtIcon:
                Intent setShirtIntent = new Intent(MainActivity.this, ShirtActivity.class);
                startActivityForResult(setShirtIntent, shirtRQ);
                break;
            case R.id.hairIcon:
                //change hair
                Log.d(TAG, "Clicked hair");
                break;
            case R.id.numIcon:
                Intent setNumIntent = new Intent(MainActivity.this, NumberActivity.class);
                setNumIntent.putExtra("currentNumber", numTV.getText().toString());
                startActivityForResult(setNumIntent, numRQ);
                break;
            case R.id.goBtn:
                Intent goToResultIntent = new Intent(MainActivity.this, ResultActivity.class);
                goToResultIntent
                        .putExtra("number", numTV.getText().toString())
                        .putExtra("color", getCurrentSkinColor())
                        .putExtra("shirt", getCurrentTeamShirt());
                startActivity(goToResultIntent);
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
                        newSkinColorId = data.getIntExtra("color", 1);
                        setNewSkinColor(newSkinColorId);
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


    public void setNewSkinColor(int newSkinColorId) {
        switch (newSkinColorId) {
            case R.id.btnSkin1:
                bodyIV.setImageResource(R.drawable.body_base_skint1);
                setCurrentSkinColor("skint1");
                break;
            case R.id.btnSkin2:
                bodyIV.setImageResource(R.drawable.body_base_skint2);
                setCurrentSkinColor("skint2");
                break;
            case R.id.btnSkin3:
                bodyIV.setImageResource(R.drawable.body_base_skint3);
                setCurrentSkinColor("skint3");
                break;
            case R.id.btnSkin4:
                bodyIV.setImageResource(R.drawable.body_base_skint4);
                setCurrentSkinColor("skint4");
                break;
        }
    }

    public void setNewShirt(String newShirt) {
        switch (newShirt) {
            case "Juventus":
                shirtIV.setImageResource(R.drawable.shirt_juve);
                break;
            case "Barcelona":
                shirtIV.setImageResource(R.drawable.shirt_barcelona);
                break;
            case "Bayren":
                shirtIV.setImageResource(R.drawable.shirt_bayern);
                break;
        } setCurrentTeamShirt(newShirt);
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

    public String getCurrentSkinColor() {
        return currentSkinColor;
    }

    public void setCurrentSkinColor(String currentSkinColor) {
        this.currentSkinColor = currentSkinColor;
    }

    public String getCurrentTeamShirt() {
        return currentTeamShirt;
    }

    public void setCurrentTeamShirt(String currentTeamShirt) {
        this.currentTeamShirt = currentTeamShirt;
    }
}