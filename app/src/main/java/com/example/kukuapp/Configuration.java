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
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Configuration extends AppCompatActivity {

    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;
    int i,j,k,l;

    String[] configrationdatamain= new String[12];
    int [] configrationdatasub = new int[12];

    public RadioGroup group_cof;
    public RadioButton radio;

    private CheckBox check_shuffle_conf,check_1_conf,check_2_conf,check_3_conf,check_4_conf,check_5_conf,check_6_conf,check_7_conf,check_8_conf,check_9_conf;

    public Button back_conf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        //-----------------------------データ定義（始め）-----------------------------------------

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
        //-----------------------------データ定義（終わり）-----------------------------------------

        //-----------------------------データ読み込み（始め）-----------------------------------------
        cursor1.moveToFirst();
        for (i = 0; i < cursor1.getCount(); i++) {
            configrationdatamain[i] = cursor1.getString(0);
            configrationdatasub[i] = cursor1.getInt(1);
            cursor1.moveToNext();
        }
        cursor1.close();
        //-----------------------------データ読み込み（終わり）-----------------------------------------

        //-----------------------------読み込みデータ反映（始め）-----------------------------------------
        group_cof = findViewById(R.id.rb_group_conf);
        switch (configrationdatasub[0]){
            case 21:
                group_cof.check(R.id.rb_21_conf);
                break;
            case 41:
                group_cof.check(R.id.rb_41_conf);
                break;
            case 61:
                group_cof.check(R.id.rb_61_conf);
                break;
            case 81:
                group_cof.check(R.id.rb_81_conf);
                break;
        }

        check_shuffle_conf = findViewById(R.id.checkb_shuffle_conf);
        if (configrationdatasub[1] == 1 ) {
            check_shuffle_conf.setChecked(true);
        }else{
            check_shuffle_conf.setChecked(false);
        }

        check_1_conf = findViewById(R.id.checkBox_1_conf);
        if (configrationdatasub[2] == 1){
            check_1_conf.setChecked(true);
        }else{
            check_1_conf.setChecked(false);
        }

        check_2_conf = findViewById(R.id.checkBox_2_conf);
        if (configrationdatasub[3] == 1){
            check_2_conf.setChecked(true);
        }else{
            check_2_conf.setChecked(false);
        }

        check_3_conf = findViewById(R.id.checkBox_3_conf);
        if (configrationdatasub[4] == 1){
            check_3_conf.setChecked(true);
        }else{
            check_3_conf.setChecked(false);
        }

        check_4_conf = findViewById(R.id.checkBox_4_conf);
        check_4_conf = findViewById(R.id.checkBox_4_conf);
        if (configrationdatasub[5] == 1){
            check_4_conf.setChecked(true);
        }else{
            check_4_conf.setChecked(false);
        }

        check_5_conf = findViewById(R.id.checkBox_5_conf);
        check_5_conf = findViewById(R.id.checkBox_5_conf);
        if (configrationdatasub[6] == 1){
            check_5_conf.setChecked(true);
        }else{
            check_5_conf.setChecked(false);
        }

        check_6_conf = findViewById(R.id.checkBox_6_conf);
        check_6_conf = findViewById(R.id.checkBox_6_conf);
        if (configrationdatasub[7] == 1){
            check_6_conf.setChecked(true);
        }else{
            check_6_conf.setChecked(false);
        }

        check_7_conf = findViewById(R.id.checkBox_7_conf);
        check_7_conf = findViewById(R.id.checkBox_7_conf);
        if (configrationdatasub[8] == 1){
            check_7_conf.setChecked(true);
        }else{
            check_7_conf.setChecked(false);
        }

        check_8_conf = findViewById(R.id.checkBox_8_conf);
        check_8_conf = findViewById(R.id.checkBox_8_conf);
        if (configrationdatasub[9] == 1){
            check_8_conf.setChecked(true);
        }else{
            check_8_conf.setChecked(false);
        }

        check_9_conf = findViewById(R.id.checkBox_9_conf);
        check_9_conf = findViewById(R.id.checkBox_9_conf);
        if (configrationdatasub[10] == 1){
            check_9_conf.setChecked(true);
        }else{
            check_9_conf.setChecked(false);
        }
        //-----------------------------読み込みデータ反映（終わり）-----------------------------------------


        //------------------------------------Radiobotton（始め）--------------------------------------
        group_cof = findViewById(R.id.rb_group_conf);
        group_cof.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radio = findViewById(checkedId);
                if (radio.isChecked() == true) {
                    if (findViewById(checkedId) == findViewById(R.id.rb_21_conf)) { configrationdatasub[0] =21; }
                    if (findViewById(checkedId) == findViewById(R.id.rb_41_conf)) { configrationdatasub[0] =41; }
                    if (findViewById(checkedId) == findViewById(R.id.rb_61_conf)) { configrationdatasub[0] =61; }
                    if (findViewById(checkedId) == findViewById(R.id.rb_81_conf)) { configrationdatasub[0] =81; }
                }
            }
        });
        //------------------------------------Radiobotton（終わり）--------------------------------------

        //------------------------------------checkbox（始め）--------------------------------------
        check_shuffle_conf = findViewById(R.id.checkb_shuffle_conf);
        check_shuffle_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_shuffle_conf.isChecked()) {
                    configrationdatasub[1] = 1;
                }else{
                    configrationdatasub[1] = 0;
                }
                unselectedChecker();
            }
        });

        check_1_conf = findViewById(R.id.checkBox_1_conf);
        check_1_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_1_conf.isChecked()) {
                    configrationdatasub[2] = 1;
                }else{
                    configrationdatasub[2] = 0;
                }
                unselectedChecker();
            }
        });

        check_2_conf = findViewById(R.id.checkBox_2_conf);
        check_2_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_2_conf.isChecked()) {
                    configrationdatasub[3] = 1;
                }else{
                    configrationdatasub[3] = 0;
                }
                unselectedChecker();
            }
        });

        check_3_conf = findViewById(R.id.checkBox_3_conf);
        check_3_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_3_conf.isChecked()) {
                    configrationdatasub[4] = 1;
                }else{
                    configrationdatasub[4] = 0;
                }
                unselectedChecker();
            }
        });

        check_4_conf = findViewById(R.id.checkBox_4_conf);
        check_4_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_4_conf.isChecked()) {
                    configrationdatasub[5] = 1;
                }else{
                    configrationdatasub[5] = 0;
                }
                unselectedChecker();
            }
        });

        check_5_conf = findViewById(R.id.checkBox_5_conf);
        check_5_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_5_conf.isChecked()) {
                    configrationdatasub[6] = 1;
                }else{
                    configrationdatasub[6] = 0;
                }
                unselectedChecker();
            }
        });

        check_6_conf = findViewById(R.id.checkBox_6_conf);
        check_6_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_6_conf.isChecked()) {
                    configrationdatasub[7] = 1;
                }else{
                    configrationdatasub[7] = 0;
                }
                unselectedChecker();
            }
        });

        check_7_conf = findViewById(R.id.checkBox_7_conf);
        check_7_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_7_conf.isChecked()) {
                    configrationdatasub[8] = 1;
                }else{
                    configrationdatasub[8] = 0;
                }
                unselectedChecker();
            }
        });

        check_8_conf = findViewById(R.id.checkBox_8_conf);
        check_8_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_8_conf.isChecked()) {
                    configrationdatasub[9] = 1;
                }else{
                    configrationdatasub[9] = 0;
                }
                unselectedChecker();
            }
        });

        check_9_conf = findViewById(R.id.checkBox_9_conf);
        check_9_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check_9_conf.isChecked()) {
                    configrationdatasub[10] = 1;
                }else{
                    configrationdatasub[10] = 0;
                }
                unselectedChecker();
            }
        });
        //------------------------------------checkbox（終わり）--------------------------------------

        //-----------------------------変更保存・画面推移（始め）-----------------------------------------
        back_conf = findViewById(R.id.b_back_conf);
        back_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                move();
            }
        });
        //-----------------------------変更保存・画面推移（終わり）-----------------------------------------

    }

    private void unselectedChecker(){
        if (configrationdatasub[2]==0 && configrationdatasub[3]==0 && configrationdatasub[4]==0 && configrationdatasub[5]==0 && configrationdatasub[6]==0 && configrationdatasub[7]==0 && configrationdatasub[8]==0 && configrationdatasub[9]==0 && configrationdatasub[10]==0){
            check_1_conf = findViewById(R.id.checkBox_1_conf);
            check_1_conf.setChecked(true);
            configrationdatasub[2] = 1;
        }
    }

    private void insertData(){
        String[] firstinsertdata_conftitle = {"questionsNum", "questionsShuffle", "questionsTableOne", "questionsTableTwo", "questionsTableThree", "questionsTableFour", "questionsTableFive", "questionsTableSix", "questionsTableSeven", "questionsTableEight", "questionsTableNine"};

        for (i = 0; i < firstinsertdata_conftitle.length; i++){
            helper = new SQL(getApplicationContext());
            db = helper.getReadableDatabase();
            ContentValues values3 = new ContentValues();
            values3.put("configuration", firstinsertdata_conftitle[i]);
            values3.put("configurationsub", configrationdatasub[i]);
            try {
                db.update("configurationdb", values3, "configuration=?", new String[]{firstinsertdata_conftitle[i]});
            } finally {
                db.close();
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
