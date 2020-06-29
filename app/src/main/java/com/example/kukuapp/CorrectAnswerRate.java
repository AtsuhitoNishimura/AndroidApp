package com.example.kukuapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TintInfo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CorrectAnswerRate extends AppCompatActivity {

    private Cursor cursor2;
    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private int i, j;

    private int[][] misscountData = new int[2][81];

    private TextView onetimetable_corr, twotimetable_corr, threetimetable_corr, fourtimetable_corr, fivetimetable_corr, sixtimetable_corr, seventimetable_corr, eighttimetable_corr, ninetimetable_corr;
    private String settext;

    private Button reset_corr, back_corr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_answer_rate);

       //-----------------------------SQLiteに接続・設定データ取得・データ読み込み（始め）-----------------------------------------
        readdata();
        //-----------------------------QLiteに接続・設定データ取得・データ読み込み（終わり）-----------------------------------------

        //-----------------------------読み込みデータ表示（始め）-----------------------------------------
        readdatadisplay();
        //-----------------------------読み込みデータ表示（終わり）-----------------------------------------

        reset_corr = findViewById(R.id.b_reset_corr);
        reset_corr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                readdata();
                readdatadisplay();
            }
        });

        back_corr = findViewById(R.id.b_back_corr);
        back_corr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move();
            }
        });
    }

    public  void readdata(){
        helper = new SQL(getApplicationContext());
        db = helper.getReadableDatabase();

        cursor2 = db.query(
                "missCountdb",
                new String[] { "missCount", "missCountsub" },
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
    }

    public void readdatadisplay(){
        int ten;
        int one;
        String[] settextpre = new String[81];
        for(i=0; i<81; i++) {
            ten = misscountData[0][i] / 10;
            one = misscountData[0][i] % 10;
            settextpre[i] = ten + "×" + one + ":" + misscountData[1][i];
        }

        String settextone = settextpre[0];
        for (i=1; i<9; i++){
            settextone = settextone + "\n" + settextpre[i];
        }
        onetimetable_corr = findViewById(R.id.t_onetimetable_corr);
        onetimetable_corr.setText(settextone);

        String settexttwo = settextpre[9];
        for (i=10; i<18; i++){
            settexttwo = settexttwo + "\n" + settextpre[i];
        }
        twotimetable_corr = findViewById(R.id.t_twotimetable_corr);
        twotimetable_corr.setText(settexttwo);

        String settextthree = settextpre[18];
        for (i=19; i<27; i++){
            settextthree = settextthree + "\n" + settextpre[i];
        }
        threetimetable_corr = findViewById(R.id.t_threetimetable_corr);
        threetimetable_corr.setText(settextthree);

        String settextfour = settextpre[27];
        for (i=28; i<36; i++){
            settextfour = settextfour + "\n" + settextpre[i];
        }
        fourtimetable_corr = findViewById(R.id.t_fourtimetable_corr);
        fourtimetable_corr.setText(settextfour);

        String settextfive = settextpre[36];
        for (i=37; i<45; i++){
            settextfive = settextfive + "\n" + settextpre[i];
        }
        fivetimetable_corr = findViewById(R.id.t_fivetimetable_corr);
        fivetimetable_corr.setText(settextfive);

        String settextsix = settextpre[45];
        for (i=46; i<54; i++){
            settextsix = settextsix + "\n" + settextpre[i];
        }
        sixtimetable_corr = findViewById(R.id.t_sixtimetable_corr);
        sixtimetable_corr.setText(settextsix);

        String settextseven = settextpre[54];
        for (i=55; i<63; i++){
            settextseven = settextseven + "\n" + settextpre[i];
        }
        seventimetable_corr = findViewById(R.id.t_seventimetable_corr);
        seventimetable_corr.setText(settextseven);

        String settexteight = settextpre[63];
        for (i=64; i<72; i++){
            settexteight = settexteight + "\n" + settextpre[i];
        }
        eighttimetable_corr = findViewById(R.id.t_eighttimetable_corr);
        eighttimetable_corr.setText(settexteight);

        String settextnine = settextpre[72];
        for (i=73; i<81; i++){
            settextnine = settextnine + "\n" + settextpre[i];
        }
        ninetimetable_corr = findViewById(R.id.t_ninetimetable_corr);
        ninetimetable_corr.setText(settextnine);
    }

    public void reset(){
        for (i=1; i<10; i++){
            for (j=1; j<10; j++){
                int datalabel;
                datalabel = i * 10 + j;
                helper = new SQL(getApplicationContext());
                db = helper.getReadableDatabase();

                ContentValues values = new ContentValues();
                values.put("missCount", datalabel);
                values.put("missCountsub", 0);

                try {
                    db.update("missCountdb", values, "missCount=?", new String[]{toString().valueOf(datalabel)});
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
