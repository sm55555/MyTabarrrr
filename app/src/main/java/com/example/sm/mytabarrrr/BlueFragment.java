package com.example.sm.mytabarrrr;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static android.content.Context.ACTIVITY_SERVICE;
import static com.example.sm.mytabarrrr.MainActivity.LightNumber;
import static com.example.sm.mytabarrrr.MainActivity.arr;
import static com.example.sm.mytabarrrr.MainActivity.btng;
/**
 * Created by SM on 2017-02-07.
 */
public class BlueFragment extends Fragment {
    static Button btnOn, btnOff, btnStop, btnUp, btnDown, btnSetStopStore, btnSetStop;
    static Button[] btnGroupSet = new Button[8];
    static Button[] btngg = new Button[35];
    static MainActivity.DBHelper dbHelper3;
    static int[] dbgroup1 = new int[36];
    static int[] dbgroup2 = new int[36];
    static int[] dbgroup3 = new int[36];
    static int[] dbgroup4 = new int[36];
    static int[] dbgroup5 = new int[36];
    static int[] dbgroup6 = new int[36];
    static int[] dbgroup7 = new int[36];
    static int[] dbgroup8 = new int[36];
    static int DB_LightNumber=0;
    static int DB_LightNumberfotTitle=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blue, container, false);
        Log.d(ACTIVITY_SERVICE, "Bluefragment is restart");

        dbHelper3.searchGroup1();
        dbHelper3.searchGroup2();
        dbHelper3.searchGroup3();
        dbHelper3.searchGroup4();
        dbHelper3.searchGroup5();
        dbHelper3.searchGroup6();
        dbHelper3.searchGroup7();
        dbHelper3.searchGroup8();

        // 그룹 부분의 그룹버튼
        btnGroupSet[0] = (Button) view.findViewById(R.id.Group1Set);
        btnGroupSet[1] = (Button) view.findViewById(R.id.Group2Set);
        btnGroupSet[2] = (Button) view.findViewById(R.id.Group3Set);
        btnGroupSet[3] = (Button) view.findViewById(R.id.Group4Set);
        btnGroupSet[4] = (Button) view.findViewById(R.id.Group5Set);
        btnGroupSet[5] = (Button) view.findViewById(R.id.Group6Set);
        btnGroupSet[6] = (Button) view.findViewById(R.id.Group7Set);
        btnGroupSet[7] = (Button) view.findViewById(R.id.Group8Set);
        // 그룹 부분의 조명버튼
        btngg[0] = (Button) view.findViewById(R.id.btn1g);
        btngg[1] = (Button) view.findViewById(R.id.btn2g);
        btngg[2] = (Button) view.findViewById(R.id.btn3g);
        btngg[3] = (Button) view.findViewById(R.id.btn4g);
        btngg[4] = (Button) view.findViewById(R.id.btn5g);
        btngg[5] = (Button) view.findViewById(R.id.btn6g);
        btngg[6] = (Button) view.findViewById(R.id.btn7g);
        btngg[7] = (Button) view.findViewById(R.id.btn8g);
        btngg[8] = (Button) view.findViewById(R.id.btn9g);
        btngg[9] = (Button) view.findViewById(R.id.btn10g);
        btngg[10] = (Button) view.findViewById(R.id.btn11g);
        btngg[11] = (Button) view.findViewById(R.id.btn12g);
        btngg[12] = (Button) view.findViewById(R.id.btn13g);
        btngg[13] = (Button) view.findViewById(R.id.btn14g);
        btngg[14] = (Button) view.findViewById(R.id.btn15g);
        btngg[15] = (Button) view.findViewById(R.id.btn16g);
        btngg[16] = (Button) view.findViewById(R.id.btn17g);
        btngg[17] = (Button) view.findViewById(R.id.btn18g);
        btngg[18] = (Button) view.findViewById(R.id.btn19g);
        btngg[19] = (Button) view.findViewById(R.id.btn20g);
        btngg[20] = (Button) view.findViewById(R.id.btn21g);
        btngg[21] = (Button) view.findViewById(R.id.btn22g);
        btngg[22] = (Button) view.findViewById(R.id.btn23g);
        btngg[23] = (Button) view.findViewById(R.id.btn24g);
        btngg[24] = (Button) view.findViewById(R.id.btn25g);
        btngg[25] = (Button) view.findViewById(R.id.btn26g);
        btngg[26] = (Button) view.findViewById(R.id.btn27g);
        btngg[27] = (Button) view.findViewById(R.id.btn28g);
        btngg[28] = (Button) view.findViewById(R.id.btn29g);
        btngg[29] = (Button) view.findViewById(R.id.btn30g);
        btngg[30] = (Button) view.findViewById(R.id.btn31g);
        btngg[31] = (Button) view.findViewById(R.id.btn32g);
        btngg[32] = (Button) view.findViewById(R.id.btn33g);
        btngg[33] = (Button) view.findViewById(R.id.btn34g);
        btngg[34] = (Button) view.findViewById(R.id.btn35g);
        //그룹의 그룹을 계속 활성화 시키기
        BlueSetting();


        Button Group1Set = (Button) view.findViewById(R.id.Group1Set);
        Group1Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[0] == 0) {
                    //한번 그룹을 설정하고 이상하게 주황색을 이용해서 끼워넣기 할때 막아주는것
                    resetGroupLightOff();
                    MainActivity.groupSize = 1;
                    resetGroup();
                    btnGroupSet[0].setBackgroundResource(R.drawable.groupbutton);
                    MainActivity.group[0] = 1;
                    dbHelper3.searchGroup1();
                    dbHelper3.searchGroup2();
                    dbHelper3.searchGroup3();
                    dbHelper3.searchGroup4();
                    dbHelper3.searchGroup5();
                    dbHelper3.searchGroup6();
                    dbHelper3.searchGroup7();
                    dbHelper3.searchGroup8();
                    for(int i=0; i<35; i++){
                        //자신을 제외한 나머지 그룹을 비활성화 시키기위함 물론 Fragment 변화에도 사용해야한다.
                        if(MainActivity.group[0] == 1 && dbgroup1[0] == 1){
                            btngg[i].setEnabled(false);
                        }
                        if(dbgroup1[i+1] == 1){
                            btngg[i].setEnabled(false);
                            btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
                        }

                    }
                    // 만약 자신의 그룹이 없다면 사용할 수 있는 조명들을 true로 만든다
                    if(dbgroup1[0]==0){
                        for(int i=0; i<35; i++){
                            if(dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                                btngg[i].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
        Button Group2Set = (Button) view.findViewById(R.id.Group2Set);
        Group2Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[1] == 0) {
                    //한번 그룹을 설정하고 이상하게 주황색을 이용해서 끼워넣기 할때 막아주는것
                    resetGroupLightOff();
                    MainActivity.groupSize = 1;
                    resetGroup();
                    btnGroupSet[1].setBackgroundResource(R.drawable.groupbutton);
                    MainActivity.group[1] = 1;
                    dbHelper3.searchGroup1();
                    dbHelper3.searchGroup2();
                    dbHelper3.searchGroup3();
                    dbHelper3.searchGroup4();
                    dbHelper3.searchGroup5();
                    dbHelper3.searchGroup6();
                    dbHelper3.searchGroup7();
                    dbHelper3.searchGroup8();
                    for(int i=0; i<35; i++){
                        //자신을 제외한 나머지 그룹을 비활성화 시키기위함 물론 Fragment 변화에도 사용해야한다.
                        if(MainActivity.group[1] == 1 && dbgroup2[0] == 1){
                            btngg[i].setEnabled(false);
                        }
                        if(dbgroup2[i+1] == 1){
                            btngg[i].setEnabled(false);
                            btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
                        }
                    }
                    // 만약 자신의 그룹이 없다면 사용할 수 있는 조명들을 true로 만든다
                    if(dbgroup2[0]==0){
                        for(int i=0; i<35; i++){
                            if(dbgroup1[i+1] !=1 && dbgroup3[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                                btngg[i].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
        Button Group3Set = (Button) view.findViewById(R.id.Group3Set);
        Group3Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[2] == 0) {
                    //한번 그룹을 설정하고 이상하게 주황색을 이용해서 끼워넣기 할때 막아주는것
                    resetGroupLightOff();
                    MainActivity.groupSize = 1;
                    resetGroup();
                    btnGroupSet[2].setBackgroundResource(R.drawable.groupbutton);
                    MainActivity.group[2] = 1;
                    dbHelper3.searchGroup1();
                    dbHelper3.searchGroup2();
                    dbHelper3.searchGroup3();
                    dbHelper3.searchGroup4();
                    dbHelper3.searchGroup5();
                    dbHelper3.searchGroup6();
                    dbHelper3.searchGroup7();
                    dbHelper3.searchGroup8();
                    for(int i=0; i<35; i++){
                        //자신을 제외한 나머지 그룹을 비활성화 시키기위함 물론 Fragment 변화에도 사용해야한다.
                        if(MainActivity.group[2] == 1 && dbgroup3[0] == 1){
                            btngg[i].setEnabled(false);
                        }
                        if(dbgroup3[i+1] == 1){
                            btngg[i].setEnabled(false);
                            btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
                        }
                    }
                    // 만약 자신의 그룹이 없다면 사용할 수 있는 조명들을 true로 만든다
                    if(dbgroup3[0]==0){
                        for(int i=0; i<35; i++){
                            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                                btngg[i].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
        Button Group4Set = (Button) view.findViewById(R.id.Group4Set);
        Group4Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[3] == 0) {
                    //한번 그룹을 설정하고 이상하게 주황색을 이용해서 끼워넣기 할때 막아주는것
                    resetGroupLightOff();
                    MainActivity.groupSize = 1;
                    resetGroup();
                    btnGroupSet[3].setBackgroundResource(R.drawable.groupbutton);
                    MainActivity.group[3] = 1;
                    dbHelper3.searchGroup1();
                    dbHelper3.searchGroup2();
                    dbHelper3.searchGroup3();
                    dbHelper3.searchGroup4();
                    dbHelper3.searchGroup5();
                    dbHelper3.searchGroup6();
                    dbHelper3.searchGroup7();
                    dbHelper3.searchGroup8();
                    for(int i=0; i<35; i++){
                        //자신을 제외한 나머지 그룹을 비활성화 시키기위함 물론 Fragment 변화에도 사용해야한다.
                        if(MainActivity.group[3] == 1 && dbgroup4[0] == 1){
                            btngg[i].setEnabled(false);
                        }
                        if(dbgroup4[i+1] == 1){
                            btngg[i].setEnabled(false);
                            btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
                        }
                    }
                    // 만약 자신의 그룹이 없다면 사용할 수 있는 조명들을 true로 만든다
                    if(dbgroup4[0]==0){
                        for(int i=0; i<35; i++){
                            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1&& dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                                btngg[i].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
        Button Group5Set = (Button) view.findViewById(R.id.Group5Set);
        Group5Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[4] == 0) {
                    //한번 그룹을 설정하고 이상하게 주황색을 이용해서 끼워넣기 할때 막아주는것
                    resetGroupLightOff();
                    MainActivity.groupSize = 1;
                    resetGroup();
                    btnGroupSet[4].setBackgroundResource(R.drawable.groupbutton);
                    MainActivity.group[4] = 1;
                    dbHelper3.searchGroup1();
                    dbHelper3.searchGroup2();
                    dbHelper3.searchGroup3();
                    dbHelper3.searchGroup4();
                    dbHelper3.searchGroup5();
                    dbHelper3.searchGroup6();
                    dbHelper3.searchGroup7();
                    dbHelper3.searchGroup8();
                    for(int i=0; i<35; i++){
                        //자신을 제외한 나머지 그룹을 비활성화 시키기위함 물론 Fragment 변화에도 사용해야한다.
                        if(MainActivity.group[4] == 1 && dbgroup5[0] == 1){
                            btngg[i].setEnabled(false);
                        }
                        if(dbgroup5[i+1] == 1){
                            btngg[i].setEnabled(false);
                            btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
                        }
                    }
                    // 만약 자신의 그룹이 없다면 사용할 수 있는 조명들을 true로 만든다
                    if(dbgroup5[0]==0){
                        for(int i=0; i<35; i++){
                            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1&& dbgroup4[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                                btngg[i].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
        Button Group6Set = (Button) view.findViewById(R.id.Group6Set);
        Group6Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[5] == 0) {
                    //한번 그룹을 설정하고 이상하게 주황색을 이용해서 끼워넣기 할때 막아주는것
                    resetGroupLightOff();
                    MainActivity.groupSize = 1;
                    resetGroup();
                    btnGroupSet[5].setBackgroundResource(R.drawable.groupbutton);
                    MainActivity.group[5] = 1;
                    dbHelper3.searchGroup1();
                    dbHelper3.searchGroup2();
                    dbHelper3.searchGroup3();
                    dbHelper3.searchGroup4();
                    dbHelper3.searchGroup5();
                    dbHelper3.searchGroup6();
                    dbHelper3.searchGroup7();
                    dbHelper3.searchGroup8();
                    for(int i=0; i<35; i++){
                        //자신을 제외한 나머지 그룹을 비활성화 시키기위함 물론 Fragment 변화에도 사용해야한다.
                        if(MainActivity.group[5] == 1 && dbgroup6[0] == 1){
                            btngg[i].setEnabled(false);
                        }
                        if(dbgroup6[i+1] == 1){
                            btngg[i].setEnabled(false);
                            btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
                        }
                    }
                    // 만약 자신의 그룹이 없다면 사용할 수 있는 조명들을 true로 만든다
                    if(dbgroup6[0]==0){
                        for(int i=0; i<35; i++){
                            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1&& dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                                btngg[i].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
        Button Group7Set = (Button) view.findViewById(R.id.Group7Set);
        Group7Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[6] == 0) {
                    //한번 그룹을 설정하고 이상하게 주황색을 이용해서 끼워넣기 할때 막아주는것
                    resetGroupLightOff();
                    MainActivity.groupSize = 1;
                    resetGroup();
                    btnGroupSet[6].setBackgroundResource(R.drawable.groupbutton);
                    MainActivity.group[6] = 1;
                    dbHelper3.searchGroup1();
                    dbHelper3.searchGroup2();
                    dbHelper3.searchGroup3();
                    dbHelper3.searchGroup4();
                    dbHelper3.searchGroup5();
                    dbHelper3.searchGroup6();
                    dbHelper3.searchGroup7();
                    dbHelper3.searchGroup8();
                    for(int i=0; i<35; i++){
                        //자신을 제외한 나머지 그룹을 비활성화 시키기위함 물론 Fragment 변화에도 사용해야한다.
                        if(MainActivity.group[6] == 1 && dbgroup7[0] == 1){
                            btngg[i].setEnabled(false);
                        }
                        if(dbgroup7[i+1] == 1){
                            btngg[i].setEnabled(false);
                            btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
                        }
                    }
                    // 만약 자신의 그룹이 없다면 사용할 수 있는 조명들을 true로 만든다
                    if(dbgroup7[0]==0){
                        for(int i=0; i<35; i++){
                            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1&& dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup8[i+1] !=1){
                                btngg[i].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
        Button Group8Set = (Button) view.findViewById(R.id.Group8Set);
        Group8Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[7] == 0) {
                    //한번 그룹을 설정하고 이상하게 주황색을 이용해서 끼워넣기 할때 막아주는것
                    resetGroupLightOff();
                    MainActivity.groupSize = 1;
                    resetGroup();
                    btnGroupSet[7].setBackgroundResource(R.drawable.groupbutton);
                    MainActivity.group[7] = 1;
                    dbHelper3.searchGroup1();
                    dbHelper3.searchGroup2();
                    dbHelper3.searchGroup3();
                    dbHelper3.searchGroup4();
                    dbHelper3.searchGroup5();
                    dbHelper3.searchGroup6();
                    dbHelper3.searchGroup7();
                    dbHelper3.searchGroup8();
                    for(int i=0; i<35; i++){
                        //자신을 제외한 나머지 그룹을 비활성화 시키기위함 물론 Fragment 변화에도 사용해야한다.
                        if(MainActivity.group[7] == 1 && dbgroup8[0] == 1){
                            btngg[i].setEnabled(false);
                        }
                        if(dbgroup8[i+1] == 1){
                            btngg[i].setEnabled(false);
                            btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
                        }
                    }
                    // 만약 자신의 그룹이 없다면 사용할 수 있는 조명들을 true로 만든다
                    if(dbgroup8[0]==0){
                        for(int i=0; i<35; i++){
                            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1&& dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1){
                                btngg[i].setEnabled(true);
                            }
                        }
                    }
                }
            }
        });

        Button btn1g = (Button) view.findViewById(R.id.btn1g);
        btn1g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[0] == 0) {
                    btngg[0].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[0] = 1;
                } else {
                    btngg[0].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[0] = 0;
                }
            }
        });
        Button btn2g = (Button) view.findViewById(R.id.btn2g);
        btn2g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[1] == 0) {
                    btngg[1].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[1] = 1;
                } else {
                    btngg[1].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[1] = 0;
                }
            }
        });
        Button btn3g = (Button) view.findViewById(R.id.btn3g);
        btn3g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[2] == 0) {
                    btngg[2].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[2] = 1;
                } else {
                    btngg[2].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[2] = 0;
                }
            }
        });
        Button btn4g = (Button) view.findViewById(R.id.btn4g);
        btn4g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[3] == 0) {
                    btngg[3].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[3] = 1;
                } else {
                    btngg[3].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[3] = 0;
                }
            }
        });
        Button btn5g = (Button) view.findViewById(R.id.btn5g);
        btn5g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[4] == 0) {
                    btngg[4].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[4] = 1;
                } else {
                    btngg[4].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[4] = 0;
                }
            }
        });
        Button btn6g = (Button) view.findViewById(R.id.btn6g);
        btn6g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[5] == 0) {
                    btngg[5].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[5] = 1;
                } else {
                    btngg[5].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[5] = 0;
                }
            }
        });
        Button btn7g = (Button) view.findViewById(R.id.btn7g);
        btn7g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[6] == 0) {
                    btngg[6].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[6] = 1;
                } else {
                    btngg[6].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[6] = 0;
                }
            }
        });
        Button btn8g = (Button) view.findViewById(R.id.btn8g);
        btn8g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[7] == 0) {
                    btngg[7].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[7] = 1;
                } else {
                    btngg[7].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[7] = 0;
                }
            }
        });
        Button btn9g = (Button) view.findViewById(R.id.btn9g);
        btn9g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[8] == 0) {
                    btngg[8].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[8] = 1;
                } else {
                    btngg[8].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[8] = 0;
                }
            }
        });
        Button btn10g = (Button) view.findViewById(R.id.btn10g);
        btn10g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[9] == 0) {
                    btngg[9].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[9] = 1;
                } else {
                    btngg[9].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[9] = 0;
                }
            }
        });
        Button btn11g = (Button) view.findViewById(R.id.btn11g);
        btn11g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[10] == 0) {
                    btngg[10].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[10] = 1;
                } else {
                    btngg[10].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[10] = 0;
                }
            }
        });
        Button btn12g = (Button) view.findViewById(R.id.btn12g);
        btn12g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[11] == 0) {
                    btngg[11].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[11] = 1;
                } else {
                    btngg[11].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[11] = 0;
                }
            }
        });
        Button btn13g = (Button) view.findViewById(R.id.btn13g);
        btn13g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[12] == 0) {
                    btngg[12].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[12] = 1;
                } else {
                    btngg[12].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[12] = 0;
                }
            }
        });
        Button btn14g = (Button) view.findViewById(R.id.btn14g);
        btn14g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[13] == 0) {
                    btngg[13].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[13] = 1;
                } else {
                    btngg[13].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[13] = 0;
                }
            }
        });
        Button btn15g = (Button) view.findViewById(R.id.btn15g);
        btn15g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[14] == 0) {
                    btngg[14].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[14] = 1;
                } else {
                    btngg[14].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[14] = 0;
                }
            }
        });
        Button btn16g = (Button) view.findViewById(R.id.btn16g);
        btn16g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[15] == 0) {
                    btngg[15].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[15] = 1;
                } else {
                    btngg[15].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[15] = 0;
                }
            }
        });
        Button btn17g = (Button) view.findViewById(R.id.btn17g);
        btn17g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[16] == 0) {
                    btngg[16].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[16] = 1;
                } else {
                    btngg[16].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[16] = 0;
                }
            }
        });
        Button btn18g = (Button) view.findViewById(R.id.btn18g);
        btn18g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[17] == 0) {
                    btngg[17].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[17] = 1;
                } else {
                    btngg[17].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[17] = 0;
                }
            }
        });
        Button btn19g = (Button) view.findViewById(R.id.btn19g);
        btn19g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[18] == 0) {
                    btngg[18].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[18] = 1;
                } else {
                    btngg[18].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[18] = 0;
                }
            }
        });
        Button btn20g = (Button) view.findViewById(R.id.btn20g);
        btn20g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[19] == 0) {
                    btngg[19].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[19] = 1;
                } else {
                    btngg[19].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[19] = 0;
                }
            }
        });
        Button btn21g = (Button) view.findViewById(R.id.btn21g);
        btn21g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[20] == 0) {
                    btngg[20].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[20] = 1;
                } else {
                    btngg[20].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[20] = 0;
                }
            }
        });
        Button btn22g = (Button) view.findViewById(R.id.btn22g);
        btn22g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[21] == 0) {
                    btngg[21].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[21] = 1;
                } else {
                    btngg[21].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[21] = 0;
                }
            }
        });
        Button btn23g = (Button) view.findViewById(R.id.btn23g);
        btn23g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[22] == 0) {
                    btngg[22].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[22] = 1;
                } else {
                    btngg[22].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[22] = 0;
                }
            }
        });
        Button btn24g = (Button) view.findViewById(R.id.btn24g);
        btn24g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[23] == 0) {
                    btngg[23].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[23] = 1;
                } else {
                    btngg[23].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[23] = 0;
                }
            }
        });
        Button btn25g = (Button) view.findViewById(R.id.btn25g);
        btn25g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[24] == 0) {
                    btngg[24].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[24] = 1;
                } else {
                    btngg[24].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[24] = 0;
                }
            }
        });
        Button btn26g = (Button) view.findViewById(R.id.btn26g);
        btn26g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[25] == 0) {
                    btngg[25].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[25] = 1;
                } else {
                    btngg[25].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[25] = 0;
                }
            }
        });
        Button btn27g = (Button) view.findViewById(R.id.btn27g);
        btn27g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[26] == 0) {
                    btngg[26].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[26] = 1;
                } else {
                    btngg[26].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[26] = 0;
                }
            }
        });
        Button btn28g = (Button) view.findViewById(R.id.btn28g);
        btn28g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[27] == 0) {
                    btngg[27].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[27] = 1;
                } else {
                    btngg[27].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[27] = 0;
                }
            }
        });
        Button btn29g = (Button) view.findViewById(R.id.btn29g);
        btn29g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[28] == 0) {
                    btngg[28].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[28] = 1;
                } else {
                    btngg[28].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[28] = 0;
                }
            }
        });
        Button btn30g = (Button) view.findViewById(R.id.btn30g);
        btn30g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[29] == 0) {
                    btngg[29].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[29] = 1;
                } else {
                    btngg[29].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[29] = 0;
                }
            }
        });
        Button btn31g = (Button) view.findViewById(R.id.btn31g);
        btn31g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[30] == 0) {
                    btngg[30].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[30] = 1;
                } else {
                    btngg[30].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[30] = 0;
                }
            }
        });
        Button btn32g = (Button) view.findViewById(R.id.btn32g);
        btn32g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[31] == 0) {
                    btngg[31].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[31] = 1;
                } else {
                    btngg[31].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[31] = 0;
                }
            }
        });
        Button btn33g = (Button) view.findViewById(R.id.btn33g);
        btn33g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[32] == 0) {
                    btngg[32].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[32] = 1;
                } else {
                    btngg[32].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[32] = 0;
                }
            }
        });
        Button btn34g = (Button) view.findViewById(R.id.btn34g);
        btn34g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[33] == 0) {
                    btngg[33].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[33] = 1;
                } else {
                    btngg[33].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[33] = 0;
                }
            }
        });
        Button btn35g = (Button) view.findViewById(R.id.btn35g);
        btn35g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btng[34] == 0) {
                    btngg[34].setBackgroundResource(R.drawable.buttonforgroupon);
                    btng[34] = 1;
                } else {
                    btngg[34].setBackgroundResource(R.drawable.buttonforgroupoff);
                    btng[34] = 0;
                }
            }
        });


        Button Group = (Button) view.findViewById(R.id.Group);
        Group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[0] == 1) {
                    if(btng[0]+btng[1]+btng[2]+btng[3]+btng[4]+btng[5]+btng[6]+btng[7]+btng[8]+btng[9]+btng[10]+btng[11]+btng[12]+btng[13]+
                            btng[14]+btng[15]+btng[16]+btng[17]+btng[18]+btng[19]+btng[20]+btng[21]+btng[22]+btng[23]+btng[24]+btng[25]+btng[26]+btng[27]+
                            btng[28]+btng[29]+btng[30]+btng[31]+btng[32]+btng[33]+btng[34] != 0){
                        int arr1 = btng[0];int arr2 = btng[1];int arr3 = btng[2];int arr4 = btng[3];int arr5 = btng[4];int arr6 = btng[5];int arr7 = btng[6];
                        int arr8 = btng[7];int arr9 = btng[8];int arr10 = btng[9];int arr11 = btng[10];int arr12 = btng[11];int arr13 = btng[12];int arr14 = btng[13];
                        int arr15 = btng[14];int arr16 = btng[15];int arr17 = btng[16];int arr18 = btng[17];int arr19 = btng[18];int arr20 = btng[19];int arr21 = btng[20];
                        int arr22 = btng[21];int arr23 = btng[22];int arr24 = btng[23];int arr25 = btng[24];int arr26 = btng[25];int arr27 = btng[26];int arr28 = btng[27];
                        int arr29 = btng[28];int arr30 = btng[29];int arr31 = btng[30];int arr32 = btng[31];int arr33 = btng[32];int arr34 = btng[33];int arr35 = btng[34];

                        dbHelper3.insert(11, 1, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13, arr14, arr15, arr16, arr17, arr18, arr19, arr20, arr21
                                , arr22, arr23, arr24, arr25, arr26, arr27, arr28, arr29, arr30, arr31, arr32, arr33, arr34, arr35);
                        dbHelper3.searchGroup1();

                        for(int i=0; i<35; i++){
                            if(dbgroup1[i+1] ==1){
                                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive); //선택된 애들만 회색화면으로 변경
                                btng[i]=0;
                            }
                            btngg[i].setEnabled(false);
                        }
                        //그룹 설정하고 컨트롤 부분을 초기화 시키는 부분 !
                        MainActivity.groupControl[0] = 0;
                        RedFragment.Group[0].setBackgroundResource(R.drawable.smbutton);
                        GreenFragment.Group[0].setBackgroundResource(R.drawable.smbutton);
                        Toast.makeText(getActivity(),"A 그룹 설정",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getActivity(), "그룹을 설정해 주세요", Toast.LENGTH_SHORT).show();
                    }
                }
                if (MainActivity.group[1] == 1) {
                    if(btng[0]+btng[1]+btng[2]+btng[3]+btng[4]+btng[5]+btng[6]+btng[7]+btng[8]+btng[9]+btng[10]+btng[11]+btng[12]+btng[13]+
                            btng[14]+btng[15]+btng[16]+btng[17]+btng[18]+btng[19]+btng[20]+btng[21]+btng[22]+btng[23]+btng[24]+btng[25]+btng[26]+btng[27]+
                            btng[28]+btng[29]+btng[30]+btng[31]+btng[32]+btng[33]+btng[34] != 0){
                        int arr1 = btng[0];int arr2 = btng[1];int arr3 = btng[2];int arr4 = btng[3];int arr5 = btng[4];int arr6 = btng[5];int arr7 = btng[6];
                        int arr8 = btng[7];int arr9 = btng[8];int arr10 = btng[9];int arr11 = btng[10];int arr12 = btng[11];int arr13 = btng[12];int arr14 = btng[13];
                        int arr15 = btng[14];int arr16 = btng[15];int arr17 = btng[16];int arr18 = btng[17];int arr19 = btng[18];int arr20 = btng[19];int arr21 = btng[20];
                        int arr22 = btng[21];int arr23 = btng[22];int arr24 = btng[23];int arr25 = btng[24];int arr26 = btng[25];int arr27 = btng[26];int arr28 = btng[27];
                        int arr29 = btng[28];int arr30 = btng[29];int arr31 = btng[30];int arr32 = btng[31];int arr33 = btng[32];int arr34 = btng[33];int arr35 = btng[34];

                        dbHelper3.insert(22, 1, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13, arr14, arr15, arr16, arr17, arr18, arr19, arr20, arr21
                                , arr22, arr23, arr24, arr25, arr26, arr27, arr28, arr29, arr30, arr31, arr32, arr33, arr34, arr35);
                        dbHelper3.searchGroup2();

                        for(int i=0; i<35; i++){
                            if(dbgroup2[i+1] ==1){
                                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive); //선택된 애들만 회색화면으로 변경
                                btng[i]=0;
                            }
                            btngg[i].setEnabled(false);
                        }
                        //그룹 설정하고 컨트롤 부분을 초기화 시키는 부분 !
                        MainActivity.groupControl[1] = 0;
                        RedFragment.Group[1].setBackgroundResource(R.drawable.smbutton);
                        GreenFragment.Group[1].setBackgroundResource(R.drawable.smbutton);
                        Toast.makeText(getActivity(),"B 그룹 설정",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getActivity(), "그룹을 설정해 주세요", Toast.LENGTH_SHORT).show();
                    }
                }
                if (MainActivity.group[2] == 1) {
                    if(btng[0]+btng[1]+btng[2]+btng[3]+btng[4]+btng[5]+btng[6]+btng[7]+btng[8]+btng[9]+btng[10]+btng[11]+btng[12]+btng[13]+
                            btng[14]+btng[15]+btng[16]+btng[17]+btng[18]+btng[19]+btng[20]+btng[21]+btng[22]+btng[23]+btng[24]+btng[25]+btng[26]+btng[27]+
                            btng[28]+btng[29]+btng[30]+btng[31]+btng[32]+btng[33]+btng[34] != 0){
                        int arr1 = btng[0];int arr2 = btng[1];int arr3 = btng[2];int arr4 = btng[3];int arr5 = btng[4];int arr6 = btng[5];int arr7 = btng[6];
                        int arr8 = btng[7];int arr9 = btng[8];int arr10 = btng[9];int arr11 = btng[10];int arr12 = btng[11];int arr13 = btng[12];int arr14 = btng[13];
                        int arr15 = btng[14];int arr16 = btng[15];int arr17 = btng[16];int arr18 = btng[17];int arr19 = btng[18];int arr20 = btng[19];int arr21 = btng[20];
                        int arr22 = btng[21];int arr23 = btng[22];int arr24 = btng[23];int arr25 = btng[24];int arr26 = btng[25];int arr27 = btng[26];int arr28 = btng[27];
                        int arr29 = btng[28];int arr30 = btng[29];int arr31 = btng[30];int arr32 = btng[31];int arr33 = btng[32];int arr34 = btng[33];int arr35 = btng[34];

                        dbHelper3.insert(33, 1, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13, arr14, arr15, arr16, arr17, arr18, arr19, arr20, arr21
                                , arr22, arr23, arr24, arr25, arr26, arr27, arr28, arr29, arr30, arr31, arr32, arr33, arr34, arr35);
                        dbHelper3.searchGroup3();

                        for(int i=0; i<35; i++){
                            if(dbgroup3[i+1] ==1){
                                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive); //선택된 애들만 회색화면으로 변경
                                btng[i]=0;
                            }
                            btngg[i].setEnabled(false);
                        }
                        //그룹 설정하고 컨트롤 부분을 초기화 시키는 부분 !
                        MainActivity.groupControl[2] = 0;
                        RedFragment.Group[2].setBackgroundResource(R.drawable.smbutton);
                        GreenFragment.Group[2].setBackgroundResource(R.drawable.smbutton);
                        Toast.makeText(getActivity(),"C 그룹 설정",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getActivity(), "그룹을 설정해 주세요", Toast.LENGTH_SHORT).show();
                    }
                }
                if (MainActivity.group[3] == 1) {
                    if(btng[0]+btng[1]+btng[2]+btng[3]+btng[4]+btng[5]+btng[6]+btng[7]+btng[8]+btng[9]+btng[10]+btng[11]+btng[12]+btng[13]+
                            btng[14]+btng[15]+btng[16]+btng[17]+btng[18]+btng[19]+btng[20]+btng[21]+btng[22]+btng[23]+btng[24]+btng[25]+btng[26]+btng[27]+
                            btng[28]+btng[29]+btng[30]+btng[31]+btng[32]+btng[33]+btng[34] != 0){
                        int arr1 = btng[0];int arr2 = btng[1];int arr3 = btng[2];int arr4 = btng[3];int arr5 = btng[4];int arr6 = btng[5];int arr7 = btng[6];
                        int arr8 = btng[7];int arr9 = btng[8];int arr10 = btng[9];int arr11 = btng[10];int arr12 = btng[11];int arr13 = btng[12];int arr14 = btng[13];
                        int arr15 = btng[14];int arr16 = btng[15];int arr17 = btng[16];int arr18 = btng[17];int arr19 = btng[18];int arr20 = btng[19];int arr21 = btng[20];
                        int arr22 = btng[21];int arr23 = btng[22];int arr24 = btng[23];int arr25 = btng[24];int arr26 = btng[25];int arr27 = btng[26];int arr28 = btng[27];
                        int arr29 = btng[28];int arr30 = btng[29];int arr31 = btng[30];int arr32 = btng[31];int arr33 = btng[32];int arr34 = btng[33];int arr35 = btng[34];

                        dbHelper3.insert(44, 1, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13, arr14, arr15, arr16, arr17, arr18, arr19, arr20, arr21
                                , arr22, arr23, arr24, arr25, arr26, arr27, arr28, arr29, arr30, arr31, arr32, arr33, arr34, arr35);
                        dbHelper3.searchGroup4();

                        for(int i=0; i<35; i++){
                            if(dbgroup4[i+1] ==1){
                                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive); //선택된 애들만 회색화면으로 변경
                                btng[i]=0;
                            }
                            btngg[i].setEnabled(false);
                        }
                        //그룹 설정하고 컨트롤 부분을 초기화 시키는 부분 !
                        MainActivity.groupControl[3] = 0;
                        RedFragment.Group[3].setBackgroundResource(R.drawable.smbutton);
                        GreenFragment.Group[3].setBackgroundResource(R.drawable.smbutton);
                        Toast.makeText(getActivity(),"D 그룹 설정",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getActivity(), "그룹을 설정해 주세요", Toast.LENGTH_SHORT).show();
                    }
                }
                if (MainActivity.group[4] == 1) {
                    if(btng[0]+btng[1]+btng[2]+btng[3]+btng[4]+btng[5]+btng[6]+btng[7]+btng[8]+btng[9]+btng[10]+btng[11]+btng[12]+btng[13]+
                            btng[14]+btng[15]+btng[16]+btng[17]+btng[18]+btng[19]+btng[20]+btng[21]+btng[22]+btng[23]+btng[24]+btng[25]+btng[26]+btng[27]+
                            btng[28]+btng[29]+btng[30]+btng[31]+btng[32]+btng[33]+btng[34] != 0){
                        int arr1 = btng[0];int arr2 = btng[1];int arr3 = btng[2];int arr4 = btng[3];int arr5 = btng[4];int arr6 = btng[5];int arr7 = btng[6];
                        int arr8 = btng[7];int arr9 = btng[8];int arr10 = btng[9];int arr11 = btng[10];int arr12 = btng[11];int arr13 = btng[12];int arr14 = btng[13];
                        int arr15 = btng[14];int arr16 = btng[15];int arr17 = btng[16];int arr18 = btng[17];int arr19 = btng[18];int arr20 = btng[19];int arr21 = btng[20];
                        int arr22 = btng[21];int arr23 = btng[22];int arr24 = btng[23];int arr25 = btng[24];int arr26 = btng[25];int arr27 = btng[26];int arr28 = btng[27];
                        int arr29 = btng[28];int arr30 = btng[29];int arr31 = btng[30];int arr32 = btng[31];int arr33 = btng[32];int arr34 = btng[33];int arr35 = btng[34];

                        dbHelper3.insert(55, 1, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13, arr14, arr15, arr16, arr17, arr18, arr19, arr20, arr21
                                , arr22, arr23, arr24, arr25, arr26, arr27, arr28, arr29, arr30, arr31, arr32, arr33, arr34, arr35);
                        dbHelper3.searchGroup5();

                        for(int i=0; i<35; i++){
                            if(dbgroup5[i+1] ==1){
                                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive); //선택된 애들만 회색화면으로 변경
                                btng[i]=0;
                            }
                            btngg[i].setEnabled(false);
                        }
                        //그룹 설정하고 컨트롤 부분을 초기화 시키는 부분 !
                        MainActivity.groupControl[4] = 0;
                        RedFragment.Group[4].setBackgroundResource(R.drawable.smbutton);
                        GreenFragment.Group[4].setBackgroundResource(R.drawable.smbutton);
                        Toast.makeText(getActivity(),"E 그룹 설정",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getActivity(), "그룹을 설정해 주세요", Toast.LENGTH_SHORT).show();
                    }
                }
                if (MainActivity.group[5] == 1) {
                    if(btng[0]+btng[1]+btng[2]+btng[3]+btng[4]+btng[5]+btng[6]+btng[7]+btng[8]+btng[9]+btng[10]+btng[11]+btng[12]+btng[13]+
                            btng[14]+btng[15]+btng[16]+btng[17]+btng[18]+btng[19]+btng[20]+btng[21]+btng[22]+btng[23]+btng[24]+btng[25]+btng[26]+btng[27]+
                            btng[28]+btng[29]+btng[30]+btng[31]+btng[32]+btng[33]+btng[34] != 0){
                        int arr1 = btng[0];int arr2 = btng[1];int arr3 = btng[2];int arr4 = btng[3];int arr5 = btng[4];int arr6 = btng[5];int arr7 = btng[6];
                        int arr8 = btng[7];int arr9 = btng[8];int arr10 = btng[9];int arr11 = btng[10];int arr12 = btng[11];int arr13 = btng[12];int arr14 = btng[13];
                        int arr15 = btng[14];int arr16 = btng[15];int arr17 = btng[16];int arr18 = btng[17];int arr19 = btng[18];int arr20 = btng[19];int arr21 = btng[20];
                        int arr22 = btng[21];int arr23 = btng[22];int arr24 = btng[23];int arr25 = btng[24];int arr26 = btng[25];int arr27 = btng[26];int arr28 = btng[27];
                        int arr29 = btng[28];int arr30 = btng[29];int arr31 = btng[30];int arr32 = btng[31];int arr33 = btng[32];int arr34 = btng[33];int arr35 = btng[34];

                        dbHelper3.insert(66, 1, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13, arr14, arr15, arr16, arr17, arr18, arr19, arr20, arr21
                                , arr22, arr23, arr24, arr25, arr26, arr27, arr28, arr29, arr30, arr31, arr32, arr33, arr34, arr35);
                        dbHelper3.searchGroup6();

                        for(int i=0; i<35; i++){
                            if(dbgroup6[i+1] ==1){
                                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive); //선택된 애들만 회색화면으로 변경
                                btng[i]=0;
                            }
                            btngg[i].setEnabled(false);
                        }
                        //그룹 설정하고 컨트롤 부분을 초기화 시키는 부분 !
                        MainActivity.groupControl[5] = 0;
                        RedFragment.Group[5].setBackgroundResource(R.drawable.smbutton);
                        GreenFragment.Group[5].setBackgroundResource(R.drawable.smbutton);
                        Toast.makeText(getActivity(),"F 그룹 설정",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getActivity(), "그룹을 설정해 주세요", Toast.LENGTH_SHORT).show();
                    }
                }
                if (MainActivity.group[6] == 1) {
                    if(btng[0]+btng[1]+btng[2]+btng[3]+btng[4]+btng[5]+btng[6]+btng[7]+btng[8]+btng[9]+btng[10]+btng[11]+btng[12]+btng[13]+
                            btng[14]+btng[15]+btng[16]+btng[17]+btng[18]+btng[19]+btng[20]+btng[21]+btng[22]+btng[23]+btng[24]+btng[25]+btng[26]+btng[27]+
                            btng[28]+btng[29]+btng[30]+btng[31]+btng[32]+btng[33]+btng[34] != 0){
                        int arr1 = btng[0];int arr2 = btng[1];int arr3 = btng[2];int arr4 = btng[3];int arr5 = btng[4];int arr6 = btng[5];int arr7 = btng[6];
                        int arr8 = btng[7];int arr9 = btng[8];int arr10 = btng[9];int arr11 = btng[10];int arr12 = btng[11];int arr13 = btng[12];int arr14 = btng[13];
                        int arr15 = btng[14];int arr16 = btng[15];int arr17 = btng[16];int arr18 = btng[17];int arr19 = btng[18];int arr20 = btng[19];int arr21 = btng[20];
                        int arr22 = btng[21];int arr23 = btng[22];int arr24 = btng[23];int arr25 = btng[24];int arr26 = btng[25];int arr27 = btng[26];int arr28 = btng[27];
                        int arr29 = btng[28];int arr30 = btng[29];int arr31 = btng[30];int arr32 = btng[31];int arr33 = btng[32];int arr34 = btng[33];int arr35 = btng[34];

                        dbHelper3.insert(77, 1, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13, arr14, arr15, arr16, arr17, arr18, arr19, arr20, arr21
                                , arr22, arr23, arr24, arr25, arr26, arr27, arr28, arr29, arr30, arr31, arr32, arr33, arr34, arr35);
                        dbHelper3.searchGroup7();

                        for(int i=0; i<35; i++){
                            if(dbgroup7[i+1] ==1){
                                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive); //선택된 애들만 회색화면으로 변경
                                btng[i]=0;
                            }
                            btngg[i].setEnabled(false);
                        }
                        //그룹 설정하고 컨트롤 부분을 초기화 시키는 부분 !
                        MainActivity.groupControl[6] = 0;
                        RedFragment.Group[6].setBackgroundResource(R.drawable.smbutton);
                        GreenFragment.Group[6].setBackgroundResource(R.drawable.smbutton);
                        Toast.makeText(getActivity(),"G 그룹 설정",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getActivity(), "그룹을 설정해 주세요", Toast.LENGTH_SHORT).show();
                    }
                }
                if (MainActivity.group[7] == 1) {
                    if(btng[0]+btng[1]+btng[2]+btng[3]+btng[4]+btng[5]+btng[6]+btng[7]+btng[8]+btng[9]+btng[10]+btng[11]+btng[12]+btng[13]+
                            btng[14]+btng[15]+btng[16]+btng[17]+btng[18]+btng[19]+btng[20]+btng[21]+btng[22]+btng[23]+btng[24]+btng[25]+btng[26]+btng[27]+
                            btng[28]+btng[29]+btng[30]+btng[31]+btng[32]+btng[33]+btng[34] != 0){
                        int arr1 = btng[0];int arr2 = btng[1];int arr3 = btng[2];int arr4 = btng[3];int arr5 = btng[4];int arr6 = btng[5];int arr7 = btng[6];
                        int arr8 = btng[7];int arr9 = btng[8];int arr10 = btng[9];int arr11 = btng[10];int arr12 = btng[11];int arr13 = btng[12];int arr14 = btng[13];
                        int arr15 = btng[14];int arr16 = btng[15];int arr17 = btng[16];int arr18 = btng[17];int arr19 = btng[18];int arr20 = btng[19];int arr21 = btng[20];
                        int arr22 = btng[21];int arr23 = btng[22];int arr24 = btng[23];int arr25 = btng[24];int arr26 = btng[25];int arr27 = btng[26];int arr28 = btng[27];
                        int arr29 = btng[28];int arr30 = btng[29];int arr31 = btng[30];int arr32 = btng[31];int arr33 = btng[32];int arr34 = btng[33];int arr35 = btng[34];

                        dbHelper3.insert(88, 1, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13, arr14, arr15, arr16, arr17, arr18, arr19, arr20, arr21
                                , arr22, arr23, arr24, arr25, arr26, arr27, arr28, arr29, arr30, arr31, arr32, arr33, arr34, arr35);
                        dbHelper3.searchGroup8();

                        for(int i=0; i<35; i++){
                            if(dbgroup8[i+1] ==1){
                                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive); //선택된 애들만 회색화면으로 변경
                                btng[i]=0;
                            }
                            btngg[i].setEnabled(false);
                        }
                        //그룹 설정하고 컨트롤 부분을 초기화 시키는 부분 !
                        MainActivity.groupControl[7] = 0;
                        RedFragment.Group[7].setBackgroundResource(R.drawable.smbutton);
                        GreenFragment.Group[7].setBackgroundResource(R.drawable.smbutton);
                        Toast.makeText(getActivity(),"H 그룹 설정",Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getActivity(), "그룹을 설정해 주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button Reset = (Button) view.findViewById(R.id.Reset);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.group[0] == 1){
                    reseting1();
                }
                if (MainActivity.group[1] == 1){
                    reseting2();
                }
                if (MainActivity.group[2] == 1){
                    reseting3();
                }
                if (MainActivity.group[3] == 1){
                    reseting4();
                }
                if (MainActivity.group[4] == 1){
                    reseting5();
                }
                if (MainActivity.group[5] == 1){
                    reseting6();
                }
                if (MainActivity.group[6] == 1){
                    reseting7();
                }
                if (MainActivity.group[7] == 1){
                    reseting8();
                }
            }
        });

        return view;
    }


    public void resetGroup() {
        if (MainActivity.groupSize > 0) {
            for(int i=0; i<8; i++){
                MainActivity.group[i] =0;
                btnGroupSet[i].setBackgroundResource(R.drawable.smbutton);
            }
            MainActivity.groupSize = 0;
        }
    }

    public void resetGroupLightOff(){
        for(int i=0; i<35; i++){
            btng[i] =0;
            btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
        }
    }
    public void reseting1(){
        //한그룹을 초기화 시키면 나머지도 다 off 되기 때문에 그에 맞게 arraySizeforgroup을 맞춰야함
        MainActivity.arraySizeforgroup=0;
        dbHelper3.delete(11);
        Toast.makeText(getActivity(),"A 그룹 해체",Toast.LENGTH_SHORT).show();
        dbHelper3.searchGroup2();
        dbHelper3.searchGroup3();
        dbHelper3.searchGroup4();
        dbHelper3.searchGroup5();
        dbHelper3.searchGroup6();
        dbHelper3.searchGroup7();
        dbHelper3.searchGroup8();
        for(int i=0; i<35; i++){
            //그룹모드 초기화 할때 본인에 해당하는것만 지우기위한것
            if(dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                btngg[i].setEnabled(true);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            }
            // 기존의 신호 보내는거 없애기
            MainActivity.tempforgroup[i] = 0;
            MainActivity.tempControlforgroup[i] = 0;
            if(arr[0] != 1){
                RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
            }
        }
        for(int i=0; i<8; i++){
            MainActivity.groupControl[i] = 0;
            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
        }
    }
    public void reseting2(){
        //한그룹을 초기화 시키면 나머지도 다 off 되기 때문에 그에 맞게 arraySizeforgroup을 맞춰야함
        MainActivity.arraySizeforgroup=0;
        dbHelper3.delete2(22);
        Toast.makeText(getActivity(),"B 그룹 해체",Toast.LENGTH_SHORT).show();
        dbHelper3.searchGroup1();
        dbHelper3.searchGroup3();
        dbHelper3.searchGroup4();
        dbHelper3.searchGroup5();
        dbHelper3.searchGroup6();
        dbHelper3.searchGroup7();
        dbHelper3.searchGroup8();

        for(int i=0; i<35; i++){
            //그룹모드 초기화 할때 본인에 해당하는것만 지우기위한것
            if(dbgroup1[i+1] !=1 && dbgroup3[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                btngg[i].setEnabled(true);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            }
            // 기존의 신호 보내는거 없애기
            MainActivity.tempforgroup[i] = 0;
            MainActivity.tempControlforgroup[i] = 0;
            if(arr[i] != 1){
                RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
            }
        }
        for(int i=0; i<8; i++){
            MainActivity.groupControl[i] = 0;
            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
        }

    }
    public void reseting3(){
        //한그룹을 초기화 시키면 나머지도 다 off 되기 때문에 그에 맞게 arraySizeforgroup을 맞춰야함
        MainActivity.arraySizeforgroup=0;
        dbHelper3.delete3(33);
        Toast.makeText(getActivity(),"C 그룹 해체",Toast.LENGTH_SHORT).show();
        dbHelper3.searchGroup1();
        dbHelper3.searchGroup2();
        dbHelper3.searchGroup4();
        dbHelper3.searchGroup5();
        dbHelper3.searchGroup6();
        dbHelper3.searchGroup7();
        dbHelper3.searchGroup8();

        for(int i=0; i<35; i++){
            //그룹모드 초기화 할때 본인에 해당하는것만 지우기위한것
            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                btngg[i].setEnabled(true);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            }
            // 기존의 신호 보내는거 없애기
            MainActivity.tempforgroup[i] = 0;
            MainActivity.tempControlforgroup[i] = 0;
            if(arr[i] != 1){
                RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
            }
        }
        for(int i=0; i<8; i++){
            MainActivity.groupControl[i] = 0;
            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
        }

    }
    public void reseting4(){
        //한그룹을 초기화 시키면 나머지도 다 off 되기 때문에 그에 맞게 arraySizeforgroup을 맞춰야함
        MainActivity.arraySizeforgroup=0;
        dbHelper3.delete4(44);
        Toast.makeText(getActivity(),"D 그룹 해체",Toast.LENGTH_SHORT).show();
        dbHelper3.searchGroup1();
        dbHelper3.searchGroup2();
        dbHelper3.searchGroup3();
        dbHelper3.searchGroup5();
        dbHelper3.searchGroup6();
        dbHelper3.searchGroup7();
        dbHelper3.searchGroup8();

        for(int i=0; i<35; i++){
            //그룹모드 초기화 할때 본인에 해당하는것만 지우기위한것
            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                btngg[i].setEnabled(true);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            }
            // 기존의 신호 보내는거 없애기
            MainActivity.tempforgroup[i] = 0;
            MainActivity.tempControlforgroup[i] = 0;
            if(arr[i] != 1){
                RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
            }
        }
        for(int i=0; i<8; i++){
            MainActivity.groupControl[i] = 0;
            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
        }
    }
    public void reseting5(){
        //한그룹을 초기화 시키면 나머지도 다 off 되기 때문에 그에 맞게 arraySizeforgroup을 맞춰야함
        MainActivity.arraySizeforgroup=0;
        dbHelper3.delete5(55);
        Toast.makeText(getActivity(),"E 그룹 해체",Toast.LENGTH_SHORT).show();
        dbHelper3.searchGroup1();
        dbHelper3.searchGroup2();
        dbHelper3.searchGroup3();
        dbHelper3.searchGroup4();
        dbHelper3.searchGroup6();
        dbHelper3.searchGroup7();
        dbHelper3.searchGroup8();

        for(int i=0; i<35; i++){
            //그룹모드 초기화 할때 본인에 해당하는것만 지우기위한것
            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                btngg[i].setEnabled(true);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            }
            // 기존의 신호 보내는거 없애기
            MainActivity.tempforgroup[i] = 0;
            MainActivity.tempControlforgroup[i] = 0;
            if(arr[i] != 1){
                RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
            }
        }
        for(int i=0; i<8; i++){
            MainActivity.groupControl[i] = 0;
            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
        }
    }
    public void reseting6(){
        //한그룹을 초기화 시키면 나머지도 다 off 되기 때문에 그에 맞게 arraySizeforgroup을 맞춰야함
        MainActivity.arraySizeforgroup=0;
        dbHelper3.delete6(66);
        Toast.makeText(getActivity(),"F 그룹 해체",Toast.LENGTH_SHORT).show();
        dbHelper3.searchGroup1();
        dbHelper3.searchGroup2();
        dbHelper3.searchGroup3();
        dbHelper3.searchGroup4();
        dbHelper3.searchGroup5();
        dbHelper3.searchGroup7();
        dbHelper3.searchGroup8();

        for(int i=0; i<35; i++){
            //그룹모드 초기화 할때 본인에 해당하는것만 지우기위한것
            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup7[i+1] !=1 && dbgroup8[i+1] !=1){
                btngg[i].setEnabled(true);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            }
            // 기존의 신호 보내는거 없애기
            MainActivity.tempforgroup[i] = 0;
            MainActivity.tempControlforgroup[i] = 0;
            if(arr[i] != 1){
                RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
            }
        }
        for(int i=0; i<8; i++){
            MainActivity.groupControl[i] = 0;
            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
        }
    }
    public void reseting7(){
        //한그룹을 초기화 시키면 나머지도 다 off 되기 때문에 그에 맞게 arraySizeforgroup을 맞춰야함
        MainActivity.arraySizeforgroup=0;
        dbHelper3.delete7(77);
        Toast.makeText(getActivity(),"G 그룹 해체",Toast.LENGTH_SHORT).show();
        dbHelper3.searchGroup1();
        dbHelper3.searchGroup2();
        dbHelper3.searchGroup3();
        dbHelper3.searchGroup4();
        dbHelper3.searchGroup5();
        dbHelper3.searchGroup6();
        dbHelper3.searchGroup8();

        for(int i=0; i<35; i++){
            //그룹모드 초기화 할때 본인에 해당하는것만 지우기위한것
            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup8[i+1] !=1){
                btngg[i].setEnabled(true);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            }
            // 기존의 신호 보내는거 없애기
            MainActivity.tempforgroup[i] = 0;
            MainActivity.tempControlforgroup[i] = 0;
            if(arr[i] != 1){
                RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
            }
        }
        for(int i=0; i<8; i++){
            MainActivity.groupControl[i] = 0;
            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
        }
    }
    public void reseting8(){
        //한그룹을 초기화 시키면 나머지도 다 off 되기 때문에 그에 맞게 arraySizeforgroup을 맞춰야함
        MainActivity.arraySizeforgroup=0;
        dbHelper3.delete8(88);
        Toast.makeText(getActivity(),"H 그룹 해체",Toast.LENGTH_SHORT).show();
        dbHelper3.searchGroup1();
        dbHelper3.searchGroup2();
        dbHelper3.searchGroup3();
        dbHelper3.searchGroup4();
        dbHelper3.searchGroup5();
        dbHelper3.searchGroup6();
        dbHelper3.searchGroup7();

        for(int i=0; i<35; i++){
            //그룹모드 초기화 할때 본인에 해당하는것만 지우기위한것
            if(dbgroup1[i+1] !=1 && dbgroup2[i+1] !=1 && dbgroup3[i+1] !=1 && dbgroup4[i+1] !=1 && dbgroup5[i+1] !=1 && dbgroup6[i+1] !=1 && dbgroup7[i+1] !=1){
                btngg[i].setEnabled(true);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            }
            // 기존의 신호 보내는거 없애기
            MainActivity.tempforgroup[i] = 0;
            MainActivity.tempControlforgroup[i] = 0;
            if(arr[i] != 1){
                RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
            }
        }
        for(int i=0; i<8; i++){
            MainActivity.groupControl[i] = 0;
            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
        }
    }

    public void BlueSetting(){
        Log.d(ACTIVITY_SERVICE, "너의 값은.." + DB_LightNumber);
        for(int i=0; i<DB_LightNumber+1; i++){
            btngg[34-i].setVisibility(View.GONE);

        }
        for(int i=0; i<35-DB_LightNumber; i++){
            btngg[i].setVisibility(View.VISIBLE);
        }


        for(int i=0; i<8; i++){
            if(MainActivity.group[i]==1){
                btnGroupSet[i].setBackgroundResource(R.drawable.groupbutton);
            }
        }
        //그룹의 버튼을 계속 활성화 시키기
        for(int i=0; i<35; i++){
            if(btng[i]==1){
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupon);
            }
        }
        for(int i=0; i<35; i++){
            if(MainActivity.group[0] == 1 && dbgroup1[0] == 1){
                btngg[i].setEnabled(false);
            }
            if(MainActivity.group[1] == 1 && dbgroup2[0] == 1){
                btngg[i].setEnabled(false);
            }
            if(MainActivity.group[2] == 1 && dbgroup3[0] == 1){
                btngg[i].setEnabled(false);
            }
            if(MainActivity.group[3] == 1 && dbgroup4[0] == 1){
                btngg[i].setEnabled(false);
            }
            if(MainActivity.group[4] == 1 && dbgroup5[0] == 1){
                btngg[i].setEnabled(false);
            }
            if(MainActivity.group[5] == 1 && dbgroup6[0] == 1){
                btngg[i].setEnabled(false);
            }
            if(MainActivity.group[6] == 1 && dbgroup7[0] == 1){
                btngg[i].setEnabled(false);
            }
            if(MainActivity.group[7] == 1 && dbgroup8[0] == 1){
                btngg[i].setEnabled(false);
            }
        }
        for(int i=0; i<35; i++){
            if(dbgroup1[i+1] == 1){
                btngg[i].setEnabled(false);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
            }
            if(dbgroup2[i+1] == 1){
                btngg[i].setEnabled(false);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
            }
            if(dbgroup3[i+1] == 1){
                btngg[i].setEnabled(false);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
            }
            if(dbgroup4[i+1] == 1){
                btngg[i].setEnabled(false);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
            }
            if(dbgroup5[i+1] == 1){
                btngg[i].setEnabled(false);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
            }
            if(dbgroup6[i+1] == 1){
                btngg[i].setEnabled(false);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
            }
            if(dbgroup7[i+1] == 1){
                btngg[i].setEnabled(false);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
            }
            if(dbgroup8[i+1] == 1){
                btngg[i].setEnabled(false);
                btngg[i].setBackgroundResource(R.drawable.buttonforgroupinactive);
            }
        }

    }
}
