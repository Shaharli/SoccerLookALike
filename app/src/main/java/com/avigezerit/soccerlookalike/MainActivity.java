package com.avigezerit.soccerlookalike;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    //tag for debugging
    static final String TAG = MainActivity.class.getSimpleName();

    //requests codes
    static final int numRQ = 2;
    static final int skinRQ = 3;
    static final int shirtRQ = 4;

    //score
    static SharedPreferences pref;
    static int score = 0;

    //character name and design
    TextView numberTV;
    TextView NameOnShirtTV;
    ImageView bodyIV;
    ImageView shirtIV;
    TextView playerNamePH;

    //customizing icons
    ImageView bodyIcon;
    ImageView shirtIcon;
    ImageView numberIcon;

    String currentTeamShirt;

    //data base and queries
    PlayerDataBase allPlayers;
    String randomPlayerFirstName;
    String hisLastname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml ref - customizing icons
        shirtIcon = (ImageView) findViewById(R.id.shirtIcon);
        numberIcon = (ImageView) findViewById(R.id.numIcon);

        //xml ref - character properties
        bodyIV = (ImageView) findViewById(R.id.bodyIV);
        shirtIV = (ImageView) findViewById(R.id.shirtIV);
        numberTV = (TextView) findViewById(R.id.numberTV);
        playerNamePH = (TextView) findViewById(R.id.playerNametitle);
        NameOnShirtTV = (TextView) findViewById(R.id.nameOnShirtTV);

        //xml ref - btns
        Button goBtn = (Button) findViewById(R.id.goBtn);
        ImageView refresh = (ImageView) findViewById(R.id.refreshIcon);

        //setting - default character properties
        setCurrentTeamShirt("default");

        //set clickLis - customizing icons & btns
        shirtIcon.setOnClickListener(this);
        numberIcon.setOnClickListener(this);
        numberTV.setOnClickListener(this);
        goBtn.setOnClickListener(this);
        refresh.setOnClickListener(this);

        //db creation and generating a random player
        allPlayers = new PlayerDataBase(this);
        allPlayers.AddAllPlayersToDB();
        getRandomPlayerFromDB();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //on click - customizing icons - intent with result
            case R.id.shirtIcon:
                Intent setShirtIntent = new Intent(MainActivity.this, ShirtActivity.class);
                startActivityForResult(setShirtIntent, shirtRQ);
                break;
            case R.id.numIcon:
            case R.id.numberTV:
                Intent setNumIntent = new Intent(MainActivity.this, NumberActivity.class);
                setNumIntent.putExtra("currentNumber", numberTV.getText());
                startActivityForResult(setNumIntent, numRQ);
                break;

            //on click - btns - intent with extra - character's current properties
            case R.id.goBtn:
                Intent goToResultIntent = new Intent(MainActivity.this, ResultActivity.class);
                goToResultIntent
                        .putExtra("lastName", hisLastname)
                        .putExtra("number", Integer.parseInt(numberTV.getText().toString()))
                        .putExtra("team", getCurrentTeamShirt());
                startActivity(goToResultIntent);
                break;
            case R.id.refreshIcon:
                getAnotherPlayerFromDB();
        }
    }

    public void getRandomPlayerFromDB() {

        //query for strings: first and last name
        randomPlayerFirstName = allPlayers.getRandomPlayerFirstName();
        hisLastname = allPlayers.getHisLastName(randomPlayerFirstName);

        //setting names
        playerNamePH.setText(randomPlayerFirstName + " " + hisLastname);
        NameOnShirtTV.setText(hisLastname);

        //setting skin tone
        setSkinTone(hisLastname);

    }

    public void getAnotherPlayerFromDB() {

        String currentRandomPlayerName = randomPlayerFirstName;

        do {
            //query for strings: first and last name
            randomPlayerFirstName = allPlayers.getRandomPlayerFirstName();
            hisLastname = allPlayers.getHisLastName(randomPlayerFirstName);

        } while (currentRandomPlayerName.equals(randomPlayerFirstName));

        if (!currentRandomPlayerName.equals(randomPlayerFirstName)) {
            //setting names
            playerNamePH.setText(randomPlayerFirstName + " " + hisLastname);
            NameOnShirtTV.setText(hisLastname);

            setSkinTone(hisLastname);
        }
    }

    public void setSkinTone(String hisLastname){

        //setting skin tone
        String hisSkinTone = allPlayers.getHisSkinTone(hisLastname);

        switch (hisSkinTone){
            case "skint1":
                bodyIV.setImageResource(R.drawable.body_base_skint1);
                break;
            case "skint2":
                bodyIV.setImageResource(R.drawable.body_base_skint2);
                break;
            case "skint3":
                bodyIV.setImageResource(R.drawable.body_base_skint3);
                break;
            case "skint4":
                bodyIV.setImageResource(R.drawable.body_base_skint4);
                break;
        }
    }


    //on result - setting character's properties
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            //setting - player number
            case numRQ:
                switch (resultCode) {
                    case RESULT_OK:
                        int newNumOfPlayer = data.getIntExtra("number", 0);
                        numberTV.setText("" + newNumOfPlayer);
                        break;
                    case RESULT_CANCELED:
                        Toast.makeText(this, "Nothing changed", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            //setting - player shirt
            case shirtRQ:
                switch (resultCode) {
                    case RESULT_OK:
                        String newShirt = data.getStringExtra("shirt");
                        setNewShirt(newShirt);
                        break;

                }
        }
    }

    public void setNewShirt(String newShirt) {


        switch (newShirt) {
            case "Juventus":
                shirtIV.setImageResource(R.drawable.juve);
                NameOnShirtTV.setTextColor(Color.BLACK);
                numberTV.setTextColor(Color.BLACK);
                break;
            case "Bayern Munich":
                shirtIV.setImageResource(R.drawable.bayern);
                NameOnShirtTV.setTextColor(Color.WHITE);
                numberTV.setTextColor(Color.WHITE);
                break;
            case "Arsenal":
                shirtIV.setImageResource(R.drawable.arsenal);
                NameOnShirtTV.setTextColor(Color.WHITE);
                numberTV.setTextColor(Color.WHITE);
                break;
            case "Barcelona":
                shirtIV.setImageResource(R.drawable.barca);
                NameOnShirtTV.setTextColor(getResources().getColor(R.color.yellowBarca));
                numberTV.setTextColor(getResources().getColor(R.color.yellowBarca));
                break;
            case "Atlético Madrid":
                shirtIV.setImageResource(R.drawable.athletico);
                NameOnShirtTV.setTextColor(Color.WHITE);
                numberTV.setTextColor(Color.WHITE);
                break;
            case "Borussia Dortmund":
                shirtIV.setImageResource(R.drawable.dortmund);
                NameOnShirtTV.setTextColor(Color.BLACK);
                numberTV.setTextColor(Color.BLACK);
                break;
            case "Manchester City":
                shirtIV.setImageResource(R.drawable.manch_city);
                NameOnShirtTV.setTextColor(getResources().getColor(R.color.darkBlue));
                numberTV.setTextColor(getResources().getColor(R.color.darkBlue));
                break;
            case "Chelsea":
                shirtIV.setImageResource(R.drawable.chelsea);
                NameOnShirtTV.setTextColor(Color.WHITE);
                numberTV.setTextColor(Color.WHITE);
                break;
        }
        setCurrentTeamShirt(newShirt);
    }

    //on back press
    @Override
    public void onBackPressed() {

        AlertDialog dontExit = new AlertDialog.Builder(MainActivity.this).create();
        dontExit.setTitle("Hold on...");
        dontExit.setMessage("Are you sure you want to leave?");
        dontExit.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", this);
        dontExit.setButton(DialogInterface.BUTTON_NEGATIVE, "No", this);

        dontExit.show();

    }

    //on back press - on click btns
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

    public String getCurrentTeamShirt() {
        return currentTeamShirt;
    }

    public void setCurrentTeamShirt(String currentTeamShirt) {
        this.currentTeamShirt = currentTeamShirt;
    }

}