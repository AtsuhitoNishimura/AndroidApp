package com.example.kukuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class Result extends AppCompatActivity {

    private int Questionlengs;
    private int[][] Answer = new int[2][100];

    private TextView resulttime;

    private TextView resultcorrect;
    int i,k,j,l;
    int K;

    private TextView wrong;
    private int[] wrongData = new int[81];

    private Button onemore;

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;

    private int[][] misscountData = new int[2][81];

    private int update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        Questionlengs = intent.getIntExtra("QuestionLengs", 0);
        Answer[0] = intent.getIntArrayExtra("Answer1");
        Answer[1] = intent.getIntArrayExtra("Answer2");
        long Time = intent.getLongExtra("Time", 0);

        //-----------------------------タイマー結果（始め）-----------------------------------------
        resulttime = findViewById(R.id.t_resulttime);
        int minutes = (int) (Time / 1000) / 60;
        int seconds = (int) (Time / 1000) % 60;
        String time = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        resulttime.setText("TIME\n" + time);
        //-----------------------------タイマー結果（終わり）---------------

        //-----------------------------正解率表示(始め）-----------------------------------------
        k = 0;
        for (i = 0; i < Answer[1].length; i++) {
            if (Answer[1][i] == 1) {
               k += 1;
            }
        }
        resultcorrect = findViewById(R.id.t_resultcorrect);
        resultcorrect.setText("正解率\n" + k + "/" + Questionlengs);
        //-----------------------------正解率表示（終わり）---------------

        //-----------------------------間違い問題（始め）-----------------------------------------
        int ten = 0;
        int one = 0;
        int tenone = 0;
        String settext = "";
        settext = settext + "間違えた問題\n";
        K=0;
        for (i = 0; i < Answer[1].length; i++) {
            if (Answer[1][i] == 0){
                ten = Answer[0][i]/10;
                one = Answer[0][i]%10;
                tenone = ten * one;
                settext = settext + ten + "×" + one + "=" + tenone;
                if (String.valueOf(tenone).length() == 1) {
                    //テキストに追加で格納する処理
                    settext = settext + "  ";
                    K += 1;
                } else {
                    settext = settext + "";
                    K += 1;
                }

                if (K % 5 == 0) {
                    settext = settext + "\n";
                } else {
                    settext = settext + " ";
                }
            }
        }

        wrong = findViewById(R.id.t_wrong);
        wrong.setText(settext);
        //-----------------------------間違い問題（終わり）---------------

        //-----------------------------間違えた問題をデータに保存(始め）-----------------------------------------
        insertdata();
        //-----------------------------間違えた問題をデータに保存（終わり）---------------



        onemore = findViewById(R.id.b_onemore);
        onemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move();
            }
        });
    }

    private void insertdata(){
        helper = new SQL(getApplicationContext());
        db = helper.getReadableDatabase();

        Cursor cursor2 = db.query(
                "misscountdb",
                new String[] { "misscount", "misscountsub" },
                null,
                null,
                null,
                null,
                null
        );

        cursor2.moveToFirst();
        for (i = 0; i < cursor2.getCount(); i++) {
            misscountData[0][i] = cursor2.getInt(0);
            misscountData[1][i] = cursor2.getInt(1);
            cursor2.moveToNext();
        }
        cursor2.close();

        for (i=0; i<Answer[1].length; i++ ){
            if (Answer[1][i] == 0){
                helper = new SQL(getApplicationContext());
                db = helper.getReadableDatabase();

                for (j=0; j<misscountData[0].length; j++) {
                    if (Answer[0][i] == misscountData[0][j]){
                        update = misscountData[1][j] + 1;
                    }
                }

                ContentValues values = new ContentValues();
                values.put("missCount", Answer[0][i]);
                values.put("missCountsub", update);

                try {
                    db.update("missCountdb", values, "missCount=?", new String[]{toString().valueOf(Answer[0][i])});
                } finally {
                    db.close();
                }
            }
        }


    }

    private void move(){
        Intent intent = new Intent();
        //インテントの作成
        intent.setClass(getApplication(), MainActivity.class);
        //移動する画面先を指定
        startActivity(intent);
        //画面推移
    }

}
