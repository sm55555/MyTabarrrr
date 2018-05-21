package com.example.sm.mytabarrrr;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import static android.content.Context.ACTIVITY_SERVICE;
import static com.example.sm.mytabarrrr.BlueFragment.DB_LightNumber;
import static com.example.sm.mytabarrrr.BlueFragment.dbHelper3;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup1;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup2;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup3;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup4;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup5;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup6;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup7;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup8;
import static com.example.sm.mytabarrrr.MainActivity.LightNumber;
import static com.example.sm.mytabarrrr.MainActivity.OffArray;
import static com.example.sm.mytabarrrr.MainActivity.OnArray;
import static com.example.sm.mytabarrrr.MainActivity.groupControl;
import static com.example.sm.mytabarrrr.MainActivity.resetOnArray;

/**
 * Created by SM on 2017-02-07.
 */
public class RedFragment extends Fragment {

    static Button btnOn, btnOff, btnStop, btnUp, btnDown, btnSetStopStore, btnSetStop;
    static Button[] btn = new Button[35];
    static Button[] Group = new Button[8];
    final byte[] a1 = new byte[]{(byte) 0xFA, (byte) 0xEF, 0x01, 0x01, 0x03};
    final byte[] a2 = new byte[]{(byte) 0xFA, (byte) 0xEF, 0x01, 0x01, 0x04};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_red, container, false);


        btn[0] = (Button) view.findViewById(R.id.btn1);
        btn[1] = (Button) view.findViewById(R.id.btn2);
        btn[2] = (Button) view.findViewById(R.id.btn3);
        btn[3] = (Button) view.findViewById(R.id.btn4);
        btn[4] = (Button) view.findViewById(R.id.btn5);
        btn[5] = (Button) view.findViewById(R.id.btn6);
        btn[6] = (Button) view.findViewById(R.id.btn7);
        btn[7] = (Button) view.findViewById(R.id.btn8);
        btn[8] = (Button) view.findViewById(R.id.btn9);
        btn[9] = (Button) view.findViewById(R.id.btn10);
        btn[10] = (Button) view.findViewById(R.id.btn11);
        btn[11] = (Button) view.findViewById(R.id.btn12);
        btn[12] = (Button) view.findViewById(R.id.btn13);
        btn[13] = (Button) view.findViewById(R.id.btn14);
        btn[14] = (Button) view.findViewById(R.id.btn15);
        btn[15] = (Button) view.findViewById(R.id.btn16);
        btn[16] = (Button) view.findViewById(R.id.btn17);
        btn[17] = (Button) view.findViewById(R.id.btn18);
        btn[18] = (Button) view.findViewById(R.id.btn19);
        btn[19] = (Button) view.findViewById(R.id.btn20);
        btn[20] = (Button) view.findViewById(R.id.btn21);
        btn[21] = (Button) view.findViewById(R.id.btn22);
        btn[22] = (Button) view.findViewById(R.id.btn23);
        btn[23] = (Button) view.findViewById(R.id.btn24);
        btn[24] = (Button) view.findViewById(R.id.btn25);
        btn[25] = (Button) view.findViewById(R.id.btn26);
        btn[26] = (Button) view.findViewById(R.id.btn27);
        btn[27] = (Button) view.findViewById(R.id.btn28);
        btn[28] = (Button) view.findViewById(R.id.btn29);
        btn[29] = (Button) view.findViewById(R.id.btn30);
        btn[30] = (Button) view.findViewById(R.id.btn31);
        btn[31] = (Button) view.findViewById(R.id.btn32);
        btn[32] = (Button) view.findViewById(R.id.btn33);
        btn[33] = (Button) view.findViewById(R.id.btn34);
        btn[34] = (Button) view.findViewById(R.id.btn35);
        Group[0] = (Button) view.findViewById(R.id.Group1);
        Group[1] = (Button) view.findViewById(R.id.Group2);
        Group[2] = (Button) view.findViewById(R.id.Group3);
        Group[3] = (Button) view.findViewById(R.id.Group4);
        Group[4] = (Button) view.findViewById(R.id.Group5);
        Group[5] = (Button) view.findViewById(R.id.Group6);
        Group[6] = (Button) view.findViewById(R.id.Group7);
        Group[7] = (Button) view.findViewById(R.id.Group8);


        //조명갯수 최신화 해주는것
        for(int i=0; i<DB_LightNumber+1; i++){
            RedFragment.btn[34-i].setVisibility(View.GONE);
        }
        for(int i=0; i<35-DB_LightNumber; i++){
            RedFragment.btn[i].setVisibility(View.VISIBLE);
        }
        //조명갯수 최신화 해주는것

        for(int i=0; i<35; i++){
            if(MainActivity.arr[i]==1){
            RedFragment.btn[i].setBackgroundResource(R.drawable.buttonforgroupon);
            }
        }
        for(int i=0; i<8; i++){
//            if(MainActivity.groupControl[i] == 1){
//                RedFragment.Group[i].setBackgroundResource(R.drawable.groupbutton);
//            }
            if(MainActivity.groupControl[i] > 0) {
                switch (i) {
                    case 0: RedFragment.Group[0].setBackgroundResource(R.drawable.groupbutton1); break;
                    case 1: RedFragment.Group[1].setBackgroundResource(R.drawable.groupbutton2); break;
                    case 2: RedFragment.Group[2].setBackgroundResource(R.drawable.groupbutton3); break;
                    case 3: RedFragment.Group[3].setBackgroundResource(R.drawable.groupbutton4); break;
                    case 4: RedFragment.Group[4].setBackgroundResource(R.drawable.groupbutton5); break;
                    case 5: RedFragment.Group[5].setBackgroundResource(R.drawable.groupbutton6); break;
                    case 6: RedFragment.Group[6].setBackgroundResource(R.drawable.groupbutton7); break;
                    case 7: RedFragment.Group[7].setBackgroundResource(R.drawable.groupbutton8); break;
                }
            }
        }
        if(MainActivity.groupControl[0] == 1){
            for(int i=0; i<35; i++){
                if(dbgroup1[i+1] == 1){
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton1);

                }
            }

        }
        if(MainActivity.groupControl[1] == 1){
            for(int i=0; i<35; i++){
                if(dbgroup2[i+1] == 1){
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton2);
                }
            }

        }
        if(MainActivity.groupControl[2] == 1){
            for(int i=0; i<35; i++){
                if(dbgroup3[i+1] == 1){
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton3);
                }
            }

        }
        if(MainActivity.groupControl[3] == 1){
            for(int i=0; i<35; i++){
                if(dbgroup4[i+1] == 1){
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton4);
                }
            }

        }
        if(MainActivity.groupControl[4] == 1){
            for(int i=0; i<35; i++){
                if(dbgroup5[i+1] == 1){
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton5);
                }
            }

        }
        if(MainActivity.groupControl[5] == 1){
            for(int i=0; i<35; i++){
                if(dbgroup6[i+1] == 1){
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton6);
                }
            }

        }
        if(MainActivity.groupControl[6] == 1){
            for(int i=0; i<35; i++){
                if(dbgroup7[i+1] == 1){
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton7);
                }
            }

        }
        if(MainActivity.groupControl[7] == 1){
            for(int i=0; i<35; i++){
                if(dbgroup8[i+1] == 1){
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton8);
                }
            }

        }




        btnOn = (Button) view.findViewById(R.id.On);
        btnOn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setupOn();
                MainActivity.setupOn2();
                MainActivity.SerialSendOn();

                MainActivity.SendOnArray(v);
                MainActivity.resetOnArray();


            }
        });
        btnOff = (Button) view.findViewById(R.id.Off);
        btnOff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setupOff();
                MainActivity.setupOff2();
                MainActivity.SerialSendOff();

                MainActivity.SendOffArray(v);
                MainActivity.resetOffArray();

            }
        });


        return view;


    }


}
