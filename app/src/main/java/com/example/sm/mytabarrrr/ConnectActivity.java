package com.example.sm.mytabarrrr;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
/**
 * Created by osangmin on 2017. 2. 14..
 */

public class ConnectActivity extends ActionBarActivity {
    private ListView listView;
    private ArrayList<String> mDeviceList = new ArrayList<String>();
    private List<BluetoothDevice> DeviceListForConnect = new ArrayList<BluetoothDevice>();

    private BluetoothAdapter mBluetoothAdapter;

    private UUID myUUID;
    private final String UUID_STRING_WELL_KNOWN_SPP = "00001101-0000-1000-8000-00805F9B34FB";
    static ThreadConnectBTdevice myThreadConnectBTdevice2;
    static ThreadConnected myThreadConnected2;

    String selectedItem;
    private ProgressBar ProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        listView = (ListView) findViewById(R.id.listView);

        ProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
//        filter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);
//        filter.addAction(BluetoothDevice.EXTRA_BOND_STATE);
//        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
//        filter.addAction(String.valueOf(BluetoothDevice.BOND_BONDED));

        registerReceiver(mReceiver, filter);
        ProgressBar.setVisibility(View.VISIBLE);



        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDeviceList);
        listView.setAdapter(adapter); //위에 만들어진

        listView.setOnItemClickListener(listener);

        myUUID = UUID.fromString(UUID_STRING_WELL_KNOWN_SPP);

    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_connect, menu);
            return true;
        }
    @Override
          public boolean onOptionsItemSelected(MenuItem item) {
              int id = item.getItemId();
              if (id == R.id.resarch) {
                  mBluetoothAdapter.cancelDiscovery();
                  mBluetoothAdapter.startDiscovery();
                  ProgressBar.setVisibility(View.VISIBLE);
                  Toast.makeText(this, "디바이스 탐색 중...", Toast.LENGTH_SHORT).show();
                  return true;
              }
            return super.onOptionsItemSelected(item);
          }

    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // TODO Auto-generated method stub
            //클릭된 아이템의 위치를 이용하여 데이터인 문자열을 Toast로 출력
            ProgressBar.setVisibility(View.VISIBLE);
            listView.setEnabled(false);
            selectedItem = DeviceListForConnect.get(position).getAddress();
            pairDevice(DeviceListForConnect.get(position));
            parent.getChildAt(position).setBackgroundColor(Color.parseColor("#bfbfbf"));
        }
    };


    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
//        if(myThreadConnectBTdevice!=null){
//            myThreadConnectBTdevice.cancel();
//        }
    }


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Set<BluetoothDevice> mDevices = mBluetoothAdapter.getBondedDevices();

            Log.d(ACTIVITY_SERVICE, action.toString() + mDevices.size());
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                ProgressBar.setVisibility(View.GONE);
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(device.getName() != null) {
                    DeviceListForConnect.add(device);
                    mDeviceList.add("Name    :   " + device.getName() + "\n" + "Address :   " + device.getAddress());
                    listView.setAdapter(new ArrayAdapter<String>(context,
                    android.R.layout.simple_list_item_1, mDeviceList));

                }
            }
            else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                for(BluetoothDevice device : mDevices) {
                    Log.d(ACTIVITY_SERVICE, selectedItem);
                    Log.d(ACTIVITY_SERVICE, device.toString());
                    if(device.toString().equals(selectedItem)) {
                        Toast.makeText(ConnectActivity.this, "페어링 완료", Toast.LENGTH_SHORT).show();
                        myThreadConnectBTdevice2 = new ThreadConnectBTdevice(device);
                        myThreadConnectBTdevice2.start();
                    }
                }

            }
        }
    };



    private void pairDevice(BluetoothDevice device) {

        try {
            Set<BluetoothDevice> mDevices = mBluetoothAdapter.getBondedDevices();

            for(BluetoothDevice _device : mDevices) {
                Log.d(ACTIVITY_SERVICE, selectedItem);
                Log.d(ACTIVITY_SERVICE, _device.toString());
                if(_device.toString().equals(selectedItem)) {
                    myThreadConnectBTdevice2 = new ThreadConnectBTdevice(device);
                    myThreadConnectBTdevice2.start();
                    return;
                }
            }

            Method method = device.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
//            myThreadConnectBTdevice = new ThreadConnectBTdevice(device);
//            myThreadConnectBTdevice.start();
            Log.i("페어링 함수완료", "ㅎ");
            Log.d(ACTIVITY_SERVICE, "end");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //쓰레드 부분
    //Called in ThreadConnectBTdevice once connect successed
    //to start ThreadConnected
    private void startThreadConnected(BluetoothSocket socket){

        myThreadConnected2 = new ThreadConnected(socket);
        myThreadConnected2.start();
    }

    /*
    ThreadConnectBTdevice:
    Background Thread to handle BlueTooth connecting
    */
    private class ThreadConnectBTdevice extends Thread {

        private BluetoothSocket bluetoothSocket = null;
        private final BluetoothDevice bluetoothDevice;


        private ThreadConnectBTdevice(BluetoothDevice device) {

            bluetoothDevice = device;

            try {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(myUUID);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

        @Override
        public void run() {
            boolean success = false;
            mBluetoothAdapter.cancelDiscovery();
            try {
                bluetoothSocket.connect();
                success = true;
            } catch (IOException e) {
                e.printStackTrace();

                final String eMessage = e.getMessage();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        Toast.makeText(ConnectActivity.this, "잠시 후 다시 시도해주세요", Toast.LENGTH_SHORT).show();
                        ProgressBar.setVisibility(View.GONE);
                        listView.setEnabled(true);
                    }
                });

                try {
                    bluetoothSocket.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if(success){
                //connect successful
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(ConnectActivity.this, "연결되었습니다", Toast.LENGTH_SHORT).show();
                        ProgressBar.setVisibility(View.GONE);
                        listView.setEnabled(true);
                        finish();

                    }
                });
                startThreadConnected(bluetoothSocket);
            }else{
                //fail 블루투스 팅기는거
//                Toast.makeText(MainActivity.this, "다시 시도 해주세요", Toast.LENGTH_LONG).show();
//                run();
            }
        }

        public void cancel() {

            Toast.makeText(getApplicationContext(),
                    "close bluetoothSocket",
                    Toast.LENGTH_LONG).show();
            try {
                bluetoothSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /*
    ThreadConnected:
    Background Thread to handle Bluetooth data communication
    after connected
     */
    public class ThreadConnected extends Thread {
        private final BluetoothSocket connectedBluetoothSocket;
        private final InputStream connectedInputStream;
        private final OutputStream connectedOutputStream;

        public ThreadConnected(BluetoothSocket socket) {
            connectedBluetoothSocket = socket;
            InputStream in = null;
            OutputStream out = null;
            try {
                in = socket.getInputStream();
                out = socket.getOutputStream();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            connectedInputStream = in;
            connectedOutputStream = out;
        }
        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;
            while (true) {
                try {
                    bytes = connectedInputStream.read(buffer);
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                        }});

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    final String msgConnectionLost = "Connection lost:\n"
                            + e.getMessage();
                    runOnUiThread(new Runnable(){

                        @Override
                        public void run() {
                        }});
                }
            }
        }

         public  void write(byte[] buffer) {
            try {
                connectedOutputStream.write(buffer);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                connectedBluetoothSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //쓰레드 부분

}

