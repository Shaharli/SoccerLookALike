package com.avigezerit.soccerlookalike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShirtActivity extends AppCompatActivity {
    ArrayList<String> allteamsAL;
    public DisplayMetrics dm = new DisplayMetrics();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt);

        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w * .9), (int) (h * .7));

        ListView allTeamsLV = (ListView) findViewById(R.id.allTeamsLV);

        allteamsAL = new ArrayList<>();
        allteamsAL.add("Barcelona");
        allteamsAL.add("Juventus");
        allteamsAL.add("Bayren");
        allteamsAL.add("Barcelona");
        allteamsAL.add("Juventus");
        allteamsAL.add("Bayren");
        allteamsAL.add("Barcelona");
        allteamsAL.add("Juventus");
        allteamsAL.add("Bayren");
        allteamsAL.add("Barcelona");
        allteamsAL.add("Juventus");
        allteamsAL.add("Bayren");

        ArrayAdapter allTeamsAA = new ArrayAdapter(ShirtActivity.this, android.R.layout.simple_list_item_1, allteamsAL);
        allTeamsLV.setAdapter(allTeamsAA);
        allTeamsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent backToMainAndSetShirt = new Intent();
                backToMainAndSetShirt.putExtra("shirt", allteamsAL.get(position));
                setResult(Activity.RESULT_OK, backToMainAndSetShirt);
                finish();
            }
        });


    }
}
