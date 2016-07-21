package com.avigezerit.soccerlookalike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ResultActivity extends AppCompatActivity {

    public DisplayMetrics dm = new DisplayMetrics();

    //xml ref
    Button doneBtn;
    ImageView points;
    ImageView goal;

    //given properties from user
    String hisLastName;
    int givenNumber;
    String givenTeam;

    //data base and queries for properties
    PlayerDataBase allPlayers;
    int hisNumber;
    String hisTeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w * .8), (int) (h * .6));

        //getting database properties - last name
        allPlayers = new PlayerDataBase(this);
        hisLastName = getIntent().getStringExtra("lastName");

        //query for properties using his last name: number, skin tone, team
        hisNumber = allPlayers.getHisNumber(hisLastName);
        hisTeam = allPlayers.getHisTeam(hisLastName);

        //getting given properties from user - number, skin tone, team
        givenNumber = getIntent().getIntExtra("number", 0);
        givenTeam = getIntent().getStringExtra("team");

        //xml ref - text
        goal = (ImageView) findViewById(R.id.goalIV);

        doneBtn = (Button) findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        compareProperties();

    }


    private void compareProperties() {

        if (hisNumber == givenNumber && hisTeam.equals(givenTeam)) {

        } else {
            doneBtn.setText("Try again");
            goal.setImageResource(R.drawable.miss);
        }
    }
}
