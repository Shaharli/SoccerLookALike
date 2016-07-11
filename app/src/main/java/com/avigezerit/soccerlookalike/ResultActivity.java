package com.avigezerit.soccerlookalike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Player player;
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button actionBtn = (Button) findViewById(R.id.actionBtn);
        resultTV = (TextView) findViewById(R.id.resultTV);

        player = new Player("1", "skint2", "Juventus");

        checkGivenValues();
    }

    public void checkGivenValues(){
        String givenNumber = getIntent().getStringExtra("number");
        String givenColor = getIntent().getStringExtra("color");
        String givenShirt = getIntent().getStringExtra("shirt");

        if (givenNumber.equals(player.getNumber()) && givenColor.equals(player.getColor()) && givenShirt.equals(player.getShirt())){
            resultTV.setText("Great! holy shit! i've made it!");
        } else {
            resultTV.setText("try again. but holy Shit i've made it!");
        } if (givenColor.equals("default")){
            resultTV.setText("try changing the default");
        }

    }
}
