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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static final String TAG = MainActivity.class.getSimpleName();
    static final int numRQ = 2;

    TextView numTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bodyIV = (ImageView) findViewById(R.id.bodyIcon);
        ImageView shirtIV = (ImageView) findViewById(R.id.shirtIcon);
        ImageView hairIV = (ImageView) findViewById(R.id.hairIcon);
        ImageView numberIV = (ImageView) findViewById(R.id.numIcon);

        TextView nameTV = (TextView) findViewById(R.id.nameTV);
        numTV = (TextView) findViewById(R.id.numberTV);


        bodyIV.setOnClickListener(this);
        shirtIV.setOnClickListener(this);
        hairIV.setOnClickListener(this);
        numberIV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bodyIcon:
                AlertDialog shirtDialog = new AlertDialog.Builder(MainActivity.this).create();
                shirtDialog.setTitle("shirt");
                shirtDialog.setMessage("this is a msg ahirt");
                shirtDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "clicked OK");
                    }
                });
                shirtDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "clicked NO");
                    }
                });
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

        switch (resultCode) {

            case RESULT_OK:
                String newNumOfPlayer = data.getStringExtra("number");
                numTV.setText(newNumOfPlayer);
                break;
            case RESULT_CANCELED:
                Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
