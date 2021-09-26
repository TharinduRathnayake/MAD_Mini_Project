package com.example.calculatebmr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class bmractivity extends AppCompatActivity {

    android.widget.Button mrecalculatebmr;

    TextView mbmrdisplay,mbmrcateogory,mgender;
    Intent intent;
    ImageView mimageview;
    String mbmr;
    float intbmr;

    String height;
    String weight;
    String age;
    float intheight,intweight,intage;
    RelativeLayout mbackground,mmale,mfemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmractivity);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\"></font>"));
        getSupportActionBar().setTitle("Result");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);


        intent=getIntent();

        mbmrdisplay=findViewById(R.id.bmrdisplay);
        mbmrcateogory=findViewById(R.id.bmicategory);
        mgender=findViewById(R.id.genderdisplay);
        mbackground=findViewById(R.id.contentlayout);
        mimageview=findViewById(R.id.imageview);
        mrecalculatebmr=findViewById(R.id.recalculatebmr);

        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");
        age=intent.getStringExtra("age");

        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);
        intage=Float.parseFloat(age);

        intheight=intheight/100;

        mbmr=Float.toString(intbmr);

        mbmr= String.valueOf(66.47+(13.75 * intweight)+(5.003*intheight)-(6.755*intage));



        getSupportActionBar().hide();
        mrecalculatebmr=findViewById(R.id.recalculatebmr);

        mrecalculatebmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(bmractivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}