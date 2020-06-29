package com.example.kukuapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    int i,j,k,l;

    private Button start;
    private Button data;
    private Button configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-----------------------------データ定義（・作成）（始め）-----------------------------------------

        helper = new SQL(getApplicationContext());
        db = helper.getReadableDatabase();

        Cursor cursor1 = db.query(
                "configurationdb",
                new String[]{"configuration", "configurationsub"},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor1.getCount() == 0) {
            firstinsertData();
        }

        //-----------------------------データ定義（・作成）（終わり）-----------------------------------------


        start =findViewById(R.id.b_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.INVISIBLE);
                data.setVisibility(View.INVISIBLE);
                configuration.setVisibility(View.INVISIBLE);

                movetoMultiplication();
            }
        });

        data =findViewById(R.id.b_data);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.INVISIBLE);
                data.setVisibility(View.INVISIBLE);
                configuration.setVisibility(View.INVISIBLE);

                movetoCorrectAnswerRate();
            }
        });

        configuration =findViewById(R.id.b_configuration);
        configuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.INVISIBLE);
                data.setVisibility(View.INVISIBLE);
                configuration.setVisibility(View.INVISIBLE);

                movetoConfiguration();
            }
        });

    }

    private void firstinsertData() {
        String[] firstinsertdata_conftitle = {"questionsNum", "questionsShuffle", "questionsTableOne", "questionsTableTwo", "questionsTableThree", "questionsTableFour", "questionsTableFive", "questionsTableSix", "questionsTableSeven", "questionsTableEight", "questionsTableNine"};

        for (i = 0; i < firstinsertdata_conftitle.length; i++) {
            helper = new SQL(getApplicationContext());
            db = helper.getReadableDatabase();

            int I;
            if (i == 0) { I = 81; }else{ I =1; }

            ContentValues values = new ContentValues();
            values.put("configuration", firstinsertdata_conftitle[i]);
            values.put("configurationsub", I);

            try {
                db.insert("configurationdb", null, values);
            } finally {
                db.close();
            }
        }

        //データ作成
        int[] firstinsertdata_misscotitle = new int[81];
        k=0;
        for(i = 1; i <10; i++){
            for(j = 1; j <10; j++){
                l = i*10 + j;
                firstinsertdata_misscotitle[k] = l;
                k += 1;
            }
        }

        for (i = 0; i < firstinsertdata_misscotitle.length; i++) {
            helper = new SQL(getApplicationContext());
            db = helper.getReadableDatabase();

            ContentValues values = new ContentValues();
            values.put("missCount", firstinsertdata_misscotitle[i]);
            values.put("missCountsub", 0);

            try {
                db.insert("missCountdb", null, values);
            } finally {
                db.close();
            }
        }
    }

    private void movetoMultiplication(){
        Intent intent = new Intent();
        //インテントの作成
        intent.setClass(getApplication(), Multiplication.class);
        //移動する画面先を指定
        startActivity(intent);
        //画面推移
    }

    private void movetoCorrectAnswerRate(){
        Intent intent = new Intent();
        //インテントの作成
        intent.setClass(getApplication(), CorrectAnswerRate.class);
        //移動する画面先を指定
        startActivity(intent);
        //画面推移
    }

    private void movetoConfiguration(){
        Intent intent = new Intent();
        //インテントの作成
        intent.setClass(getApplication(), Configuration.class);
        //移動する画面先を指定
        startActivity(intent);
        //画面推移
    }
}
