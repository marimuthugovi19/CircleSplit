package com.mari.android.circlesplit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    int[] colorArray;
    MultiColorCircle multiColorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multiColorView = (MultiColorCircle)findViewById(R.id.multiColorView);
        colorArray= getResources().getIntArray(R.array.multi_color);

        multiColorView.setColorList(colorArray);
        multiColorView.setDviderWidth(2);


    }
}
