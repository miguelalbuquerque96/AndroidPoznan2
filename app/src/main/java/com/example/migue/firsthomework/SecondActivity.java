package com.example.migue.firsthomework;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
     int value;
     public   int index;
      Button yes;
     Button cancel;
     int color;
    Intent intent;
   public  Spinner spinner;
 public   int [] colors ={Color.RED,color= Color.BLUE,Color.GREEN,Color.CYAN,Color.MAGENTA,Color.BLACK};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
         spinner = (Spinner) findViewById(R.id.spinner);
          yes=(Button)findViewById(R.id.yes);
          cancel=(Button)findViewById(R.id.cancel);

        Intent PrevActivity = getIntent();//Toast.makeText(this, ""+PrevActivity.getType(), Toast.LENGTH_SHORT).show();

       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SecondActivity.this, "position ="+position, Toast.LENGTH_SHORT).show();
                index=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SecondActivity.this, "select a color", Toast.LENGTH_SHORT).show();

            }
        });
      //  value =PrevActivity.getIntExtra("value",0);
        //Toast.makeText(this, "puta do valor é = "+value, Toast.LENGTH_SHORT).show();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        onBackPressed();

            }
        });

//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SecondActivity.this, "Nothing changed!!", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });








    }
    @Override
    public void onBackPressed(){
         intent  = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("color",index);
        setResult(RESULT_OK,intent);
        finish();
    }

}
