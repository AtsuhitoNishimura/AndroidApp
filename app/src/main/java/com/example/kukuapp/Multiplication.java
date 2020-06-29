package com.example.kukuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Multiplication extends AppCompatActivity {

    private static final long START_TIME = 3600000;
    private TextView time;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME;
    long timeresult = 0;

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    int i,j,k,l;

    String[] configrationdatamain= new String[12];
    int [] configrationdatasub = new int[12];
    int[][] misscountdata = new int[2][81];

    Integer[] multiplicationQarray = new Integer[81];

    int I, K;
    int questionLengs;
    private TextView display;
    private TextView ordinulnum;
    private int answer;
    private TextView input;
    private Button next;
    int[][] multiplicationAarray = new int[2][100];

    private int[] Answer1 = new int[100];
    private int[] Answer2 = new int[100];

    private int number;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button deletenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        //-----------------------------SQLiteに接続・設定データ取得（始め）-----------------------------------------
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
        //-----------------------------SQLiteに接続・設定データ取得（終わり）-----------------------------------------

        //-----------------------------データ読み込み（始め）-----------------------------------------
        cursor1.moveToFirst();
        for (i = 0; i < cursor1.getCount(); i++) {
            configrationdatamain[i] = cursor1.getString(0);
            configrationdatasub[i] = cursor1.getInt(1);
            cursor1.moveToNext();
        }
        cursor1.close();
        //-----------------------------データ読み込み（終わり）-----------------------------------------

        //-----------------------------設定データ反映、問題データ作成（始め）-----------------------------------------
        List<Integer> multiplicationQ = new ArrayList<>();

        for(i = 1; i <10; i++){
            for(j = 1; j <10; j++){
                k = i*10 + j;
                if (configrationdatasub[i+1] == 1){
                    multiplicationQ.add(k);
                }
            }
        }

        if (configrationdatasub[1] == 1){
            Collections.shuffle(multiplicationQ);
        }

        multiplicationQarray = (Integer[])multiplicationQ.toArray(new Integer[0]);

        //-----------------------------設定データ反映、問題データ作成（終わり）-----------------------------------------

        //-----------------------------タイマー（始め）-----------------------------------------
        time = findViewById(R.id.t_time);
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = START_TIME-millisUntilFinished;
                int minutes = (int)(mTimeLeftInMillis/1000)/60;
                int seconds = (int)(mTimeLeftInMillis/1000)%60;
                //秒の値
                String timerLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                //表示する文字列を格納
                time.setText(timerLeftFormatted);
                //文字列の表示
                //テキストをアップデート
            }

            //カウントダウンが終了した時の処理
            @Override
            public void onFinish() {
                timeresult = START_TIME;
                //timeresult = 500000;
                move();
            }

        }.start();
        //-----------------------------タイマー（終わり）---------------------------------------

        //-----------------------------九九実行（始め）-----------------------------------------
        buttoninsertfunction();

        I = 0;
        j = multiplicationQarray[I]/10;
        k = multiplicationQarray[I]%10;
        String settext;
        settext = j + "×" + k;

        K = I + 1;
        ordinulnum=findViewById(R.id.t_ordinulnum);
        ordinulnum.setText(K + "問目");

        display = findViewById(R.id.t_display);
        display.setText(settext);

        answer = j * k;

        input = findViewById(R.id.t_input);
        input.setText("");

        for (i=0; i<multiplicationAarray[1].length; i++) {
            multiplicationAarray[0][i] = i;
            multiplicationAarray[1][i] = 2;
        }

        next = findViewById(R.id.b_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextmultiplication();
            }
        });

        //-----------------------------九九実行（終わり）-----------------------------------------


    }

    private void insert(){
        input = findViewById(R.id.t_input);
        String addtext = input.getText().toString() + number;
        input.setText(addtext);
    }

    private void alldelete(){
        input = findViewById(R.id.t_input);
        Editable editable = Editable.Factory.getInstance().newEditable(input.getText());
        if(editable.length() > 0){
            editable.delete(0, editable.length());
        }
        input.setText(editable);
    }

    private void delete(){
        input = findViewById(R.id.t_input);
        Editable editable = Editable.Factory.getInstance().newEditable(input.getText());
        if(editable.length() > 0){
            editable.delete(editable.length()-1, editable.length());
            //editable.delete(削除するデータ範囲;
        }
        input.setText(editable);
        if (editable.length() == 0){
            //input = findViewById(R.id.t_input);
            input.setText("");
        }
    }

    private void buttoninsertfunction(){
        alldelete();

        one = findViewById(R.id.b_one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 1;
                insert();
            }
        });

        two= findViewById(R.id.b_two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 2;
                insert();
            }
        });

        three = findViewById(R.id.b_three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 3;
                insert();
            }
        });

        four = findViewById(R.id.b_four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 4;
                insert();
            }
        });

        five = findViewById(R.id.b_five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 5;
                insert();
            }
        });

        six = findViewById(R.id.b_six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 6;
                insert();
            }
        });

        seven = findViewById(R.id.b_seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 7;
                insert();
            }
        });

        eight = findViewById(R.id.b_eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 8;
                insert();
            }
        });

        nine = findViewById(R.id.b_nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 9;
                insert();
            }
        });

        zero = findViewById(R.id.b_zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = 0;
                insert();
            }
        });

        deletenum = findViewById(R.id.b_deletenum);
        deletenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }


    private void nextmultiplication(){
        String inputanswerString = input.getText().toString();

        //空欄だった場合の処理

        if(inputanswerString == ""){
            multiplicationAarray[1][multiplicationQarray[I]] = 0;
        }else{
            int inputanswerint = Integer.parseInt(inputanswerString);
            if (answer == inputanswerint) {
                multiplicationAarray[1][multiplicationQarray[I]] = 1;
            } else {
                multiplicationAarray[1][multiplicationQarray[I]] = 0;
            }
        }
        input = findViewById(R.id.t_input);
        input.setText("");

        I +=1;

        if (multiplicationQarray.length > configrationdatasub[0]){
            questionLengs = configrationdatasub[0];
        }else{
            questionLengs = multiplicationQarray.length;
        }

        if (I < questionLengs){
            j = multiplicationQarray[I]/10;
            k = multiplicationQarray[I]%10;
            String settext;
            settext = j + "×" + k;

            answer = j * k;

            K = I + 1;
            ordinulnum=findViewById(R.id.t_ordinulnum);
            ordinulnum.setText(K + "問目");
            display = findViewById(R.id.t_display);
            display.setText(settext);

            if (I == questionLengs - 1){
                next.setText("Answer");
            }
        }else{
            next.setVisibility(View.INVISIBLE);
            timeresult = mTimeLeftInMillis;
            move();
        }
    }

    private void move(){
        for (i = 0; i < multiplicationAarray[0].length; i++) {
            Answer1[i] = multiplicationAarray[0][i];
            Answer2[i] = multiplicationAarray[1][i];
        }
        Intent intent = new Intent();
        //インテントの作成
        intent.setClass(getApplication(), Result.class);
        //移動する画面先を指定
        intent.putExtra("QuestionLengs", questionLengs);
        intent.putExtra("Answer1", Answer1);
        intent.putExtra("Answer2", Answer2);
        intent.putExtra("Time" ,timeresult);
        //次の画面に引き継ぐもの（問題の配列と、答えの結果の配列）
        startActivity(intent);
        //画面推移
    }

}
