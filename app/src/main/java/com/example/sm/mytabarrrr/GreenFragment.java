package com.example.sm.mytabarrrr;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static com.example.sm.mytabarrrr.BlueFragment.DB_LightNumber;

/**
 * Created by SM on 2017-02-07.
 */
public class GreenFragment extends Fragment {
    static Button btnOn, btnOff, btnStop, btnUp, btnDown, btnSetStopStore, btnSetStop;
    static Button[] btn = new Button[35];
    static Button[] Group = new Button[8];


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_green, container, false);


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
            GreenFragment.btn[34-i].setVisibility(View.GONE);

        }
        for(int i=0; i<35-DB_LightNumber; i++){
            GreenFragment.btn[i].setVisibility(View.VISIBLE);

        }

        //조명갯수 최신화 해주는

        btnUp = (Button) view.findViewById(R.id.Up);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setupUp();
                MainActivity.setupUp2();
                MainActivity.SerialSendUp();
                MainActivity.SendUpArray(v);
                MainActivity.resetUpArray();
            }
        });

        btnDown = (Button) view.findViewById(R.id.Down);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setupDown();
                MainActivity.setupDown2();
                MainActivity.SerialSendDown();
                MainActivity.SendDownArray(v);
                MainActivity.resetDownArray();
            }
        });

        btnStop = (Button) view.findViewById(R.id.Stop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setupStop();
                MainActivity.setupStop2();
                MainActivity.SerialSendStop();
                MainActivity.SendStopArray(v);
                MainActivity.resetStopArray();
            }
        });

        btnSetStop = (Button) view.findViewById(R.id.SetStop);
        btnSetStop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setupSetStop();
                MainActivity.setupSetStop2();
                MainActivity.SerialSendSetStop();
                MainActivity.SendSetStopArray(v);
                MainActivity.resetSetStopArray();
            }
        });

        btnSetStopStore = (Button) view.findViewById(R.id.SetStore);
        btnSetStopStore.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setupSetStore();
                MainActivity.setupSetStore2();
                MainActivity.SerialSendSetStore();
                MainActivity.SendSetStoreArray(v);
                MainActivity.resetSetStoreArray();
                Toast.makeText(getActivity(),"위치가 저장되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }




}

