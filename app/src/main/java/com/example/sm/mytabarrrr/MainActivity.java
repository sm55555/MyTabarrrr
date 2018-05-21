package com.example.sm.mytabarrrr;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.MetricAffectingSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tw.com.prolific.driver.pl2303.PL2303Driver;
import android.hardware.usb.UsbManager;

import static com.example.sm.mytabarrrr.BlueFragment.DB_LightNumberfotTitle;
import static com.example.sm.mytabarrrr.BlueFragment.btngg;
import static com.example.sm.mytabarrrr.BlueFragment.dbHelper3;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup1;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup2;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup3;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup4;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup5;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup6;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup7;
import static com.example.sm.mytabarrrr.BlueFragment.dbgroup8;
import static com.example.sm.mytabarrrr.BlueFragment.DB_LightNumber;
import static com.example.sm.mytabarrrr.ConnectActivity.myThreadConnected2;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    //시리얼 변수
    private static final boolean SHOW_DEBUG = true;
    static PL2303Driver mSerial;
    String TAG = "PL2303HXD_APLog";

    private static PL2303Driver.BaudRate mBaudrate = PL2303Driver.BaudRate.B9600;
    private static final String ACTION_USB_PERMISSION = "com.prolific.pl2303hxdsimpletest.USB_PERMISSION";
    private static final String NULL = null;
    //시리얼 변수

    //블루투스 변수
    private static final int REQUEST_ENABLE_BT = 1;
    static final byte[] a1 = new byte[]{(byte) 0xFA, (byte) 0xEF, 0x01, 0x01, 0x03};
    static final byte[] a2 = new byte[]{(byte) 0xFA, (byte) 0xEF, 0x01, 0x01, 0x04};
    ArrayList<BluetoothDevice> pairedDeviceArrayList;
    ArrayAdapter<BluetoothDevice> pairedDeviceAdapter;
    int deviceList= 0;
    private ListView listView;
    private ArrayList<String> mDeviceList = new ArrayList<String>();
    private BluetoothAdapter mBluetoothAdapter;
    private UUID myUUID;
    private final String UUID_STRING_WELL_KNOWN_SPP = "00001101-0000-1000-8000-00805F9B34FB";
//    static ThreadConnectBTdevice myThreadConnectBTdevice;
//    static ThreadConnected myThreadConnected;
    private BluetoothDevice bluetoothDevice;
    private BluetoothLeScanner leScanner;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    //액션바 액션타이틀 이름바꾸기
    public Menu mMenu;
    //탭바 이미지 바꾸기
    private ViewPager mViewPager;
    private int[] imageResId = {R.drawable.lightwhite, R.drawable.updowngrey, R.drawable.settinggrey};
    static int LightNumber;

    //블루투스 변수들


    static int[] arr = new int[35];
    static byte[] temp = new byte[35];
    static byte[] tempControl = new byte[35];
    static int arraySize = 0;
    static int arraySizeforgroup = 0;
    static byte[] tempforgroup = new byte[35];
    static byte[] tempControlforgroup = new byte[35];

    static byte[] OnArray = new byte[39];
    static byte[] OffArray = new byte[39];
    static byte[] UpArray = new byte[39];
    static byte[] DownArray = new byte[39];
    static byte[] StopArray = new byte[39];
    static byte[] SetStopArray = new byte[39];
    static byte[] SetStoreArray = new byte[39];

    static int[] group = new int[8];
    static int groupSize = 0;

    static int[] btng = new int[35];



    private static Context context;

    static int[] groupControl = new int[8];

    static Animation alphaAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("조명");


        // ActionBar의 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF1F2025));
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        alphaAni = AnimationUtils.loadAnimation(this, R.anim.alpha);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        //탭바 이미지 바꾸는거

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(imageResId[i]);
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) tab.setCustomView(R.layout.view_home_tab);
        }
        int colors[] = {Color.RED, Color.YELLOW};
        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors);

        tabLayout.setOnTabSelectedListener(
            new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    switch(tab.getPosition()) {
                        case 0:
                            getSupportActionBar().setTitle("조명");
                            break;
                        case 1:
                            getSupportActionBar().setTitle("승하강");
                            break;
                        case 2:
                            getSupportActionBar().setTitle("설정");
                            break;

                    }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    tab.getIcon().setColorFilter(Color.parseColor("#a8a8a8"), PorterDuff.Mode.SRC_IN);
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
        });

//
//        listView = (ListView) findViewById(R.id.listView);
//        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, mDeviceList);
//        listView.setAdapter(adapter);
        myUUID = UUID.fromString(UUID_STRING_WELL_KNOWN_SPP);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        //블루투스 변수
        //컨트롤 부분 조명
        BlueFragment.dbHelper3 = new DBHelper(getApplicationContext(), "bb1a.db", null, 1);



        //serial control
        // get service
        mSerial = new PL2303Driver((UsbManager) getSystemService(Context.USB_SERVICE),
                this, ACTION_USB_PERMISSION);
        // check USB host function.
        if (!mSerial.PL2303USBFeatureSupported()) {
            Toast.makeText(this, "No Support USB host API", Toast.LENGTH_SHORT)
                    .show();
            Log.d(TAG, "No Support USB host API");
            mSerial = null;
        }
        //serial control

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permissionCheck== PackageManager.PERMISSION_DENIED){
            Log.d(ACTIVITY_SERVICE, "블루투스 권한 없음");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            // 권한 없음
        }else{
            Log.d(ACTIVITY_SERVICE, "블루투스 권한 있음");

            // 권한 있음
        }
        //Red, Green 조명 갯수 바꿔주는것;;
        dbHelper3.searchLightNumber();
        //


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

//    public static void setContext(Context context){
//        DBHelper.context = context;
//    }

    //serial function

    public void onResume() {
        Log.d(TAG, "Enter onResume");
        super.onResume();
        String action =  getIntent().getAction();
        Log.d(TAG, "onResume:"+action);

        //if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action))
        if(!mSerial.isConnected()) {
            if (SHOW_DEBUG) {
                Log.d(TAG, "New instance : " + mSerial);
            }

            if( !mSerial.enumerate() ) {

//                Toast.makeText(this, "no more devices found", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Log.d(TAG, "onResume:enumerate succeeded!");
            }
        }//if isConnected
        Toast.makeText(this, "attached", Toast.LENGTH_SHORT).show();

        Log.d(TAG, "Leave onResume");
    }
    public static void SerialSendOn() {
        if (!mSerial.InitByBaudRate(mBaudrate,700)) {
            if(!mSerial.PL2303Device_IsHasPermission()) {
//                Toast.makeText(this, "cannot open, maybe no permission", Toast.LENGTH_SHORT).show();
            }
            if(mSerial.PL2303Device_IsHasPermission() && (!mSerial.PL2303Device_IsSupportChip())) {
//                Toast.makeText(this, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", Toast.LENGTH_SHORT).show();
            }
        } else {
        }
        mBaudrate = PL2303Driver.BaudRate.B9600;
        if(null==mSerial)
            return;
        if(!mSerial.isConnected())
            return;
        if(arraySize >0){
            int res = mSerial.write(OnArray, arraySize+4);
        }
        if(arraySizeforgroup>0){
            int res = mSerial.write(OnArray, arraySizeforgroup+4);
        }

    }
    public static void SerialSendOff() {
        if (!mSerial.InitByBaudRate(mBaudrate,700)) {
            if(!mSerial.PL2303Device_IsHasPermission()) {
//                Toast.makeText(this, "cannot open, maybe no permission", Toast.LENGTH_SHORT).show();
            }
            if(mSerial.PL2303Device_IsHasPermission() && (!mSerial.PL2303Device_IsSupportChip())) {
//                Toast.makeText(this, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", Toast.LENGTH_SHORT).show();
            }
        } else {
        }
        mBaudrate = PL2303Driver.BaudRate.B9600;
        if(null==mSerial)
            return;
        if(!mSerial.isConnected())
            return;
        if(arraySize >0){
            int res = mSerial.write(OffArray, arraySize+4);
        }
        if(arraySizeforgroup>0){
            int res = mSerial.write(OffArray, arraySizeforgroup+4);
        }
    }
    public static void SerialSendUp() {
        if (!mSerial.InitByBaudRate(mBaudrate,700)) {
            if(!mSerial.PL2303Device_IsHasPermission()) {
//                Toast.makeText(this, "cannot open, maybe no permission", Toast.LENGTH_SHORT).show();
            }
            if(mSerial.PL2303Device_IsHasPermission() && (!mSerial.PL2303Device_IsSupportChip())) {
//                Toast.makeText(this, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", Toast.LENGTH_SHORT).show();
            }
        } else {
        }
        mBaudrate = PL2303Driver.BaudRate.B9600;
        if(null==mSerial)
            return;
        if(!mSerial.isConnected())
            return;
        if(arraySize >0){
            int res = mSerial.write(UpArray, arraySize+4);
        }
        if(arraySizeforgroup>0){
            int res = mSerial.write(UpArray, arraySizeforgroup+4);
        }
    }
    public static void SerialSendDown() {
        if (!mSerial.InitByBaudRate(mBaudrate,700)) {
            if(!mSerial.PL2303Device_IsHasPermission()) {
//                Toast.makeText(this, "cannot open, maybe no permission", Toast.LENGTH_SHORT).show();
            }
            if(mSerial.PL2303Device_IsHasPermission() && (!mSerial.PL2303Device_IsSupportChip())) {
//                Toast.makeText(this, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", Toast.LENGTH_SHORT).show();
            }
        } else {
        }
        mBaudrate = PL2303Driver.BaudRate.B9600;
        if(null==mSerial)
            return;
        if(!mSerial.isConnected())
            return;
        if(arraySize >0){
            int res = mSerial.write(DownArray, arraySize+4);
        }
        if(arraySizeforgroup>0){
            int res = mSerial.write(DownArray, arraySizeforgroup+4);
        }
    }
    public static void SerialSendStop() {
        if (!mSerial.InitByBaudRate(mBaudrate,700)) {
            if(!mSerial.PL2303Device_IsHasPermission()) {
//                Toast.makeText(this, "cannot open, maybe no permission", Toast.LENGTH_SHORT).show();
            }
            if(mSerial.PL2303Device_IsHasPermission() && (!mSerial.PL2303Device_IsSupportChip())) {
//                Toast.makeText(this, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", Toast.LENGTH_SHORT).show();
            }
        } else {
        }
        mBaudrate = PL2303Driver.BaudRate.B9600;
        if(null==mSerial)
            return;
        if(!mSerial.isConnected())
            return;
        if(arraySize >0){
            int res = mSerial.write(StopArray, arraySize+4);
        }
        if(arraySizeforgroup>0){
            int res = mSerial.write(StopArray, arraySizeforgroup+4);
        }
    }
    public static void SerialSendSetStop() {
        if (!mSerial.InitByBaudRate(mBaudrate,700)) {
            if(!mSerial.PL2303Device_IsHasPermission()) {
//                Toast.makeText(this, "cannot open, maybe no permission", Toast.LENGTH_SHORT).show();
            }
            if(mSerial.PL2303Device_IsHasPermission() && (!mSerial.PL2303Device_IsSupportChip())) {
//                Toast.makeText(this, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", Toast.LENGTH_SHORT).show();
            }
        } else {
        }
        mBaudrate = PL2303Driver.BaudRate.B9600;
        if(null==mSerial)
            return;
        if(!mSerial.isConnected())
            return;
        if(arraySize >0){
            int res = mSerial.write(SetStopArray, arraySize+4);
        }
        if(arraySizeforgroup>0){
            int res = mSerial.write(SetStopArray, arraySizeforgroup+4);
        }
    }
    public static void SerialSendSetStore() {
        if (!mSerial.InitByBaudRate(mBaudrate,700)) {
            if(!mSerial.PL2303Device_IsHasPermission()) {
//                Toast.makeText(this, "cannot open, maybe no permission", Toast.LENGTH_SHORT).show();
            }
            if(mSerial.PL2303Device_IsHasPermission() && (!mSerial.PL2303Device_IsSupportChip())) {
//                Toast.makeText(this, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", Toast.LENGTH_SHORT).show();
            }
        } else {
        }
        mBaudrate = PL2303Driver.BaudRate.B9600;
        if(null==mSerial)
            return;
        if(!mSerial.isConnected())
            return;
        if(arraySize >0){
            int res = mSerial.write(SetStoreArray, arraySize+4);
        }
        if(arraySizeforgroup>0){
            int res = mSerial.write(SetStoreArray, arraySizeforgroup+4);
        }
    }

    //serial function


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        //  액션바 액션버튼 타이틀 바꾸기위한것 !
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.SM){
            Intent i = new Intent(this, ConnectActivity.class);
            startActivity(i);
        } else if(id == R.id.lightNumber){
            changeLightNumber();
            Log.d(ACTIVITY_SERVICE, "asddsadsadddadadsadddsa");
        }
        return super.onOptionsItemSelected(item);
    }



//    private ScanCallback scanCallback = new ScanCallback()
//    {
//        @Override
//        public void onScanResult(int callbackType, ScanResult result)
//        {
//            super.onScanResult(callbackType, result);
//            Log.d(ACTIVITY_SERVICE, "|" + "111111111111111" + "|" + result.getDevice().getName() + "|" + result.getDevice().getAddress());
//        }
//
//
//        @Override
//        public void onScanFailed(int errorCode)
//        {
//            super.onScanFailed(errorCode);
//            Log.d(ACTIVITY_SERVICE, "|" + "2222222222222" + "|" + errorCode);
//        }
//
//
//        @Override
//        public void onBatchScanResults(List<ScanResult> results)
//        {
//            super.onBatchScanResults(results);
//            for (ScanResult result : results)
//            {
//                Log.d(ACTIVITY_SERVICE, "33333333333333|" + result.getDevice().getName() + "|" + result.getDevice().getAddress() + "|");
//            }
//        }
//    };




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new RedFragment();
            else if(position==1)
                return new GreenFragment();
            else
                return new BlueFragment();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "";
                case 1:
                    return "";
                case 2:
                    return "";
            }
            return null;
        }
    }


    @Override
    protected void onDestroy() {
        if(mSerial!=null) {
            mSerial.end();
            mSerial = null;
        }
//        unregisterReceiver(mReceiver);
//        unregisterReceiver(mReceiver2);
        super.onDestroy();
//        if(myThreadConnectBTdevice!=null){
//            myThreadConnectBTdevice.cancel();
//        }
    }

    //컨틀롤 부분에서 조명버튼입니다...
    static public void SendOnArray(View view) {
        buttonAnimation(RedFragment.Group, RedFragment.btn);
        if (myThreadConnected2 != null) {
            myThreadConnected2.write(OnArray);
        }
    }
    static public void SendOffArray(View view) {
        buttonAnimation(RedFragment.Group, RedFragment.btn);
        if (myThreadConnected2 != null) {
            myThreadConnected2.write(OffArray);
        }
    }
    static public void SendUpArray(View view) {
        buttonAnimation(GreenFragment.Group, GreenFragment.btn);
        if (myThreadConnected2 != null) {
            myThreadConnected2.write(UpArray);
        }
    }
    static public void SendDownArray(View view) {
        buttonAnimation(GreenFragment.Group, GreenFragment.btn);
        if (myThreadConnected2 != null) {
            myThreadConnected2.write(DownArray);
        }
    }
    static public void SendStopArray(View view) {
        buttonAnimation(GreenFragment.Group, GreenFragment.btn);
        if (myThreadConnected2 != null) {
            myThreadConnected2.write(StopArray);
        }
    }
    static public void SendSetStopArray(View view) {
        buttonAnimation(GreenFragment.Group, GreenFragment.btn);
        if (myThreadConnected2 != null) {
            myThreadConnected2.write(SetStopArray);
        }
    }
    static public void SendSetStoreArray(View view) {
        buttonAnimation(GreenFragment.Group, GreenFragment.btn);
        if (myThreadConnected2 != null) {
            myThreadConnected2.write(SetStoreArray);
        }
    }



    static public void checkLightState() {
        if(arraySize >0 ){
            for (int i = 0; i < 35; i++) {
                if (arr[i] != 0) {
                    arr[i] = 0;
                    temp[i] = 0;
                    tempControl[i] = 0;
                }
                RedFragment.btn[i].setBackgroundResource(R.drawable.buttonforgroupoff);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            }
            arraySize = 0;
        }
    }

    static public void checkGroupState() {

        if(groupControl[0] == 1 || groupControl[1] == 1 || groupControl[2] == 1 || groupControl[3] == 1 || groupControl[4] == 1 || groupControl[5] == 1 || groupControl[6] == 1 || groupControl[7] == 1){
            for (int i = 0; i < 8; i++) {
                if (MainActivity.groupControl[i] != 0) {
                    MainActivity.groupControl[i] = 0;
                }
                RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            }
            for (int i = 0; i < 35; i++) {
                temp[i] = 0;
                tempControl[i] = 0;
                tempforgroup[i]=0;
                tempControlforgroup[i]=0;
                RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);

            }
            arraySizeforgroup=0;
        }


    }

    static public void btn1Onclick(View view) {
        checkGroupState();
        if (arr[0] == 0) {
            arr[0] = 1;
            temp[0] = 0x01;
            tempControl[0] = 0x01;
            arraySize++;
            RedFragment.btn[0].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[0].setBackgroundResource(R.drawable.buttonforgroupon);


        } else {
            arr[0] = 0;
            temp[0] = 0;
            tempControl[0] = 0;
            arraySize--;
            RedFragment.btn[0].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[0].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }

    static public void btn2Onclick(View v) {
        checkGroupState();
        if (arr[1] == 0) {
            arr[1] = 1;
            temp[1] = 0x02;
            tempControl[1] = 0x02;
            arraySize++;
            RedFragment.btn[1].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[1].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[1] = 0;
            temp[1] = 0;
            tempControl[1] = 0;
            arraySize--;
            RedFragment.btn[1].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[1].setBackgroundResource(R.drawable.buttonforgroupoff);
        }
    }

    static public void btn3Onclick(View view) {
        checkGroupState();
        if (arr[2] == 0) {
            arr[2] = 1;
            temp[2] = 0x03;
            tempControl[2] = 0x03;
            arraySize++;
            RedFragment.btn[2].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[2].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[2] = 0;
            temp[2] = 0;
            tempControl[2] = 0;
            arraySize--;
            RedFragment.btn[2].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[2].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }

    static public void btn4Onclick(View view) {
checkGroupState();
        if (arr[3] == 0) {
            arr[3] = 1;
            temp[3] = 0x04;
            tempControl[3] = 0x04;
            arraySize++;
            RedFragment.btn[3].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[3].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[3] = 0;
            temp[3] = 0;
            tempControl[3] = 0;
            arraySize--;
            RedFragment.btn[3].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[3].setBackgroundResource(R.drawable.buttonforgroupoff);
        }
    }

    static public void btn5Onclick(View view) {
checkGroupState();
        if (arr[4] == 0) {
            arr[4] = 1;
            temp[4] = 0x05;
            tempControl[4] = 0x05;
            arraySize++;
            RedFragment.btn[4].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[4].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[4] = 0;
            temp[4] = 0;
            tempControl[4] = 0;
            arraySize--;
            RedFragment.btn[4].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[4].setBackgroundResource(R.drawable.buttonforgroupoff);
        }
    }

    static public void btn6Onclick(View view) {
checkGroupState();
        if (arr[5] == 0) {
            arr[5] = 1;
            temp[5] = 0x06;
            tempControl[5] = 0x06;
            arraySize++;
            RedFragment.btn[5].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[5].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[5] = 0;
            temp[5] = 0;
            tempControl[5] = 0;
            arraySize--;
            RedFragment.btn[5].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[5].setBackgroundResource(R.drawable.buttonforgroupoff);
        }
    }

    static public void btn7Onclick(View view) {
checkGroupState();
        if (arr[6] == 0) {
            arr[6] = 1;
            temp[6] = 0x07;
            tempControl[6] = 0x07;
            arraySize++;
            RedFragment.btn[6].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[6].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[6] = 0;
            temp[6] = 0;
            tempControl[6] = 0;
            arraySize--;
            RedFragment.btn[6].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[6].setBackgroundResource(R.drawable.buttonforgroupoff);
        }
    }
    static public void btn8Onclick(View view) {
checkGroupState();
        if (arr[7] == 0) {
            arr[7] = 1;
            temp[7] = 0x08;
            tempControl[7] = 0x08;
            arraySize++;
            RedFragment.btn[7].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[7].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[7] = 0;
            temp[7] = 0;
            tempControl[7] = 0;
            arraySize--;
            RedFragment.btn[7].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[7].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn9Onclick(View view) {
checkGroupState();
        if (arr[8] == 0) {
            arr[8] = 1;
            temp[8] = 0x09;
            tempControl[8] = 0x09;
            arraySize++;
            RedFragment.btn[8].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[8].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[8] = 0;
            temp[8] = 0;
            tempControl[8] = 0;
            arraySize--;
            RedFragment.btn[8].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[8].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn10Onclick(View view) {
checkGroupState();
        if (arr[9] == 0) {
            arr[9] = 1;
            temp[9] = 0xA;
            tempControl[9] = 0xA;
            arraySize++;
            RedFragment.btn[9].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[9].setBackgroundResource(R.drawable.buttonforgroupon);

        } else {
            arr[9] = 0;
            temp[9] = 0;
            tempControl[9] = 0;
            arraySize--;
            RedFragment.btn[9].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[9].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn11Onclick(View view) {
checkGroupState();
        if (arr[10] == 0) {
            arr[10] = 1;
            temp[10] = 0xB;
            tempControl[10] = 0xB;
            arraySize++;
            RedFragment.btn[10].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[10].setBackgroundResource(R.drawable.buttonforgroupon);

        } else {
            arr[10] = 0;
            temp[10] = 0;
            tempControl[10] = 0;
            arraySize--;
            RedFragment.btn[10].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[10].setBackgroundResource(R.drawable.buttonforgroupoff);


        }
    }
    static public void btn12Onclick(View view) {
checkGroupState();
        if (arr[11] == 0) {
            arr[11] = 1;
            temp[11] = 0xC;
            tempControl[11] = 0xC;
            arraySize++;
            RedFragment.btn[11].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[11].setBackgroundResource(R.drawable.buttonforgroupon);

        } else {
            arr[11] = 0;
            temp[11] = 0;
            tempControl[11] = 0;
            arraySize--;
            RedFragment.btn[11].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[11].setBackgroundResource(R.drawable.buttonforgroupoff);


        }
    }
    static public void btn13Onclick(View view) {
checkGroupState();
        if (arr[12] == 0) {
            arr[12] = 1;
            temp[12] = 0xD;
            tempControl[12] = 0xD;
            arraySize++;
            RedFragment.btn[12].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[12].setBackgroundResource(R.drawable.buttonforgroupon);

        } else {
            arr[12] = 0;
            temp[12] = 0;
            tempControl[12] = 0;
            arraySize--;
            RedFragment.btn[12].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[12].setBackgroundResource(R.drawable.buttonforgroupoff);


        }
    }
    static public void btn14Onclick(View view) {
checkGroupState();
        if (arr[13] == 0) {
            arr[13] = 1;
            temp[13] = 0xE;
            tempControl[13] = 0xE;
            arraySize++;
            RedFragment.btn[13].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[13].setBackgroundResource(R.drawable.buttonforgroupon);

        } else {
            arr[13] = 0;
            temp[13] = 0;
            tempControl[13] = 0;
            arraySize--;
            RedFragment.btn[13].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[13].setBackgroundResource(R.drawable.buttonforgroupoff);


        }
    }
    static public void btn15Onclick(View view) {
checkGroupState();
        if (arr[14] == 0) {
            arr[14] = 1;
            temp[14] = 0xF;
            tempControl[14] = 0xF;
            arraySize++;
            RedFragment.btn[14].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[14].setBackgroundResource(R.drawable.buttonforgroupon);

        } else {
            arr[14] = 0;
            temp[14] = 0;
            tempControl[14] = 0;
            arraySize--;
            RedFragment.btn[14].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[14].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn16Onclick(View view) {
checkGroupState();
        if (arr[15] == 0) {
            arr[15] = 1;
            temp[15] = 0x10;
            tempControl[15] = 0x10;
            arraySize++;
            RedFragment.btn[15].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[15].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[15] = 0;
            temp[15] = 0;
            tempControl[15] = 0;
            arraySize--;
            RedFragment.btn[15].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[15].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn17Onclick(View view) {
checkGroupState();
        if (arr[16] == 0) {
            arr[16] = 1;
            temp[16] = 0x11;
            tempControl[16] = 0x11;
            arraySize++;
            RedFragment.btn[16].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[16].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[16] = 0;
            temp[16] = 0;
            tempControl[16] = 0;
            arraySize--;
            RedFragment.btn[16].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[16].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn18Onclick(View view) {
checkGroupState();
        if (arr[17] == 0) {
            arr[17] = 1;
            temp[17] = 0x12;
            tempControl[17] = 0x12;
            arraySize++;
            RedFragment.btn[17].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[17].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[17] = 0;
            temp[17] = 0;
            tempControl[17] = 0;
            arraySize--;
            RedFragment.btn[17].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[17].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn19Onclick(View view) {
checkGroupState();
        if (arr[18] == 0) {
            arr[18] = 1;
            temp[18] = 0x13;
            tempControl[18] = 0x13;
            arraySize++;
            RedFragment.btn[18].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[18].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[18] = 0;
            temp[18] = 0;
            tempControl[18] = 0;
            arraySize--;
            RedFragment.btn[18].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[18].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn20Onclick(View view) {
checkGroupState();
        if (arr[19] == 0) {
            arr[19] = 1;
            temp[19] = 0x14;
            tempControl[19] = 0x14;
            arraySize++;
            RedFragment.btn[19].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[19].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[19] = 0;
            temp[19] = 0;
            tempControl[19] = 0;
            arraySize--;
            RedFragment.btn[19].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[19].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn21Onclick(View view) {
checkGroupState();
        if (arr[20] == 0) {
            arr[20] = 1;
            temp[20] = 0x15;
            tempControl[20] = 0x15;
            arraySize++;
            RedFragment.btn[20].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[20].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[20] = 0;
            temp[20] = 0;
            tempControl[20] = 0;
            arraySize--;
            RedFragment.btn[20].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[20].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn22Onclick(View view) {
checkGroupState();
        if (arr[21] == 0) {
            arr[21] = 1;
            temp[21] = 0x16;
            tempControl[21] = 0x16;
            arraySize++;
            RedFragment.btn[21].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[21].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[21] = 0;
            temp[21] = 0;
            tempControl[21] = 0;
            arraySize--;
            RedFragment.btn[21].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[21].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn23Onclick(View view) {
checkGroupState();
        if (arr[22] == 0) {
            arr[22] = 1;
            temp[22] = 0x17;
            tempControl[22] = 0x17;
            arraySize++;
            RedFragment.btn[22].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[22].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[22] = 0;
            temp[22] = 0;
            tempControl[22] = 0;
            arraySize--;
            RedFragment.btn[22].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[22].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn24Onclick(View view) {
checkGroupState();
        if (arr[23] == 0) {
            arr[23] = 1;
            temp[23] = 0x18;
            tempControl[23] = 0x18;
            arraySize++;
            RedFragment.btn[23].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[23].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[23] = 0;
            temp[23] = 0;
            tempControl[23] = 0;
            arraySize--;
            RedFragment.btn[23].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[23].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn25Onclick(View view) {
checkGroupState();
        if (arr[24] == 0) {
            arr[24] = 1;
            temp[24] = 0x19;
            tempControl[24] = 0x19;
            arraySize++;
            RedFragment.btn[24].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[24].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[24] = 0;
            temp[24] = 0;
            tempControl[24] = 0;
            arraySize--;
            RedFragment.btn[24].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[24].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn26Onclick(View view) {
checkGroupState();
        if (arr[25] == 0) {
            arr[25] = 1;
            temp[25] = 0x1A;
            tempControl[25] = 0x1A;
            arraySize++;
            RedFragment.btn[25].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[25].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[25] = 0;
            temp[25] = 0;
            tempControl[25] = 0;
            arraySize--;
            RedFragment.btn[25].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[25].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn27Onclick(View view) {
checkGroupState();
        if (arr[26] == 0) {
            arr[26] = 1;
            temp[26] = 0x1B;
            tempControl[26] = 0x1B;
            arraySize++;
            RedFragment.btn[26].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[26].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[26] = 0;
            temp[26] = 0;
            tempControl[26] = 0;
            arraySize--;
            RedFragment.btn[26].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[26].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn28Onclick(View view) {
checkGroupState();
        if (arr[27] == 0) {
            arr[27] = 1;
            temp[27] = 0x1C;
            tempControl[27] = 0x1C;
            arraySize++;
            RedFragment.btn[27].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[27].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[27] = 0;
            temp[27] = 0;
            tempControl[27] = 0;
            arraySize--;
            RedFragment.btn[27].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[27].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn29Onclick(View view) {
checkGroupState();
        if (arr[28] == 0) {
            arr[28] = 1;
            temp[28] = 0x1D;
            tempControl[28] = 0x1D;
            arraySize++;
            RedFragment.btn[28].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[28].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[28] = 0;
            temp[28] = 0;
            tempControl[28] = 0;
            arraySize--;
            RedFragment.btn[28].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[28].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static  public void btn30Onclick(View view) {
checkGroupState();
        if (arr[29] == 0) {
            arr[29] = 1;
            temp[29] = 0x1E;
            tempControl[29] = 0x1E;
            arraySize++;
            RedFragment.btn[29].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[29].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[29] = 0;
            temp[29] = 0;
            tempControl[29] = 0;
            arraySize--;
            RedFragment.btn[29].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[29].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn31Onclick(View view) {
checkGroupState();
        if (arr[30] == 0) {
            arr[30] = 1;
            temp[30] = 0x1F;
            tempControl[30] = 0x1F;
            arraySize++;
            RedFragment.btn[30].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[30].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[30] = 0;
            temp[30] = 0;
            tempControl[30] = 0;
            arraySize--;
            RedFragment.btn[30].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[30].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn32Onclick(View view) {
checkGroupState();
        if (arr[31] == 0) {
            arr[31] = 1;
            temp[31] = 0x20;
            tempControl[31] = 0x20;
            arraySize++;
            RedFragment.btn[31].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[31].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[31] = 0;
            temp[31] = 0;
            tempControl[31] = 0;
            arraySize--;
            RedFragment.btn[31].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[31].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn33Onclick(View view) {
checkGroupState();
        if (arr[32] == 0) {
            arr[32] = 1;
            temp[32] = 0x21;
            tempControl[32] = 0x21;
            arraySize++;
            RedFragment.btn[32].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[32].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[32] = 0;
            temp[32] = 0;
            tempControl[32] = 0;
            arraySize--;
            RedFragment.btn[32].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[32].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn34Onclick(View view) {
checkGroupState();
        if (arr[33] == 0) {
            arr[33] = 1;
            temp[33] = 0x22;
            tempControl[33] = 0x22;
            arraySize++;
            RedFragment.btn[33].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[33].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[33] = 0;
            temp[33] = 0;
            tempControl[33] = 0;
            arraySize--;
            RedFragment.btn[33].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[33].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }
    static public void btn35Onclick(View view) {
checkGroupState();
        if (arr[34] == 0) {
            arr[34] = 1;
            temp[34] = 0x23;
            tempControl[34] = 0x23;
            arraySize++;
            RedFragment.btn[34].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[34].setBackgroundResource(R.drawable.buttonforgroupon);
        } else {
            arr[34] = 0;
            temp[34] = 0;
            tempControl[34] = 0;
            arraySize--;
            RedFragment.btn[34].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[34].setBackgroundResource(R.drawable.buttonforgroupoff);

        }
    }

    //컨틀롤 부분에서 조명버튼입니다...

    //컨트롤 부분에서 그룹버튼입니다...
    static public void Group1Onclick(View view){
                checkLightState();
        if (MainActivity.groupControl[0] == 0) {
            MainActivity.groupControl[0] = 1;
            dbHelper3.searchGroup1();
            RedFragment.Group[0].setBackgroundResource(R.drawable.groupbutton1);
            GreenFragment.Group[0].setBackgroundResource(R.drawable.groupbutton1);
            for (int i = 0; i < 35; i++) {
                if (dbgroup1[i+1] == 1) {
                    MainActivity.tempforgroup[i] = (byte) (i + 1);
                    MainActivity.tempControlforgroup[i] = (byte) (i + 1);
                    MainActivity.arraySizeforgroup++;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton1);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.groupbutton1);
                }
            }

        } else{
            MainActivity.groupControl[0] = 0;
            dbHelper3.searchGroup1();
            RedFragment.Group[0].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[0].setBackgroundResource(R.drawable.smbutton);
            for (int i = 0; i < 35; i++) {
                if (dbgroup1[i+1] == 1) {
                    MainActivity.tempforgroup[i] = 0;
                    MainActivity.tempControlforgroup[i] = 0;
                    MainActivity.arraySizeforgroup--;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                }
            }
        }
    }
    static public void Group2Onclick(View view){
                checkLightState();
        if (MainActivity.groupControl[1] == 0) {
            MainActivity.groupControl[1] = 1;
            dbHelper3.searchGroup2();
            RedFragment.Group[1].setBackgroundResource(R.drawable.groupbutton2);
            GreenFragment.Group[1].setBackgroundResource(R.drawable.groupbutton2);
            for (int i = 0; i < 35; i++) {
                if (dbgroup2[i+1] == 1) {
                    MainActivity.tempforgroup[i] = (byte) (i + 1);
                    MainActivity.tempControlforgroup[i] = (byte) (i + 1);
                    MainActivity.arraySizeforgroup++;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton2);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.groupbutton2);
                }
            }

        } else{
            MainActivity.groupControl[1] = 0;
            dbHelper3.searchGroup2();
            RedFragment.Group[1].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[1].setBackgroundResource(R.drawable.smbutton);
            for (int i = 0; i < 35; i++) {
                if (dbgroup2[i+1] == 1) {
                    MainActivity.tempforgroup[i] = 0;
                    MainActivity.tempControlforgroup[i] = 0;
                    MainActivity.arraySizeforgroup--;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                }
            }
        }
    }
    static public void Group3Onclick(View view){
                checkLightState();
        if (MainActivity.groupControl[2] == 0) {
            MainActivity.groupControl[2] = 1;
            dbHelper3.searchGroup3();
            RedFragment.Group[2].setBackgroundResource(R.drawable.groupbutton3);
            GreenFragment.Group[2].setBackgroundResource(R.drawable.groupbutton3);
            for (int i = 0; i < 35; i++) {
                if (dbgroup3[i+1] == 1) {
                    MainActivity.tempforgroup[i] = (byte) (i + 1);
                    MainActivity.tempControlforgroup[i] = (byte) (i + 1);
                    MainActivity.arraySizeforgroup++;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton3);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.groupbutton3);
                }
            }

        } else{
            MainActivity.groupControl[2] = 0;
            dbHelper3.searchGroup3();
            RedFragment.Group[2].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[2].setBackgroundResource(R.drawable.smbutton);
            for (int i = 0; i < 35; i++) {
                if (dbgroup3[i+1] == 1) {
                    MainActivity.tempforgroup[i] = 0;
                    MainActivity.tempControlforgroup[i] = 0;
                    MainActivity.arraySizeforgroup--;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                }
            }
        }
    }
    static public void Group4Onclick(View view){
                checkLightState();
        if (MainActivity.groupControl[3] == 0) {
            MainActivity.groupControl[3] = 1;
            dbHelper3.searchGroup4();
            RedFragment.Group[3].setBackgroundResource(R.drawable.groupbutton4);
            GreenFragment.Group[3].setBackgroundResource(R.drawable.groupbutton4);
            for (int i = 0; i < 35; i++) {
                if (dbgroup4[i+1] == 1) {
                    MainActivity.tempforgroup[i] = (byte) (i + 1);
                    MainActivity.tempControlforgroup[i] = (byte) (i + 1);
                    MainActivity.arraySizeforgroup++;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton4);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.groupbutton4);

                }
            }

        } else{
            MainActivity.groupControl[3] = 0;
            dbHelper3.searchGroup4();
            RedFragment.Group[3].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[3].setBackgroundResource(R.drawable.smbutton);
            for (int i = 0; i < 35; i++) {
                if (dbgroup4[i+1] == 1) {
                    MainActivity.tempforgroup[i] = 0;
                    MainActivity.tempControlforgroup[i] = 0;
                    MainActivity.arraySizeforgroup--;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                }
            }
        }
    }
    static public void Group5Onclick(View view){
                checkLightState();
        if (MainActivity.groupControl[4] == 0) {
            MainActivity.groupControl[4] = 1;
            dbHelper3.searchGroup5();
            RedFragment.Group[4].setBackgroundResource(R.drawable.groupbutton5);
            GreenFragment.Group[4].setBackgroundResource(R.drawable.groupbutton5);
            for (int i = 0; i < 35; i++) {
                if (dbgroup5[i+1] == 1) {
                    MainActivity.tempforgroup[i] = (byte) (i + 1);
                    MainActivity.tempControlforgroup[i] = (byte) (i + 1);
                    MainActivity.arraySizeforgroup++;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton5);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.groupbutton5);
                }
            }

        } else{
            MainActivity.groupControl[4] = 0;
            dbHelper3.searchGroup5();
            RedFragment.Group[4].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[4].setBackgroundResource(R.drawable.smbutton);
            for (int i = 0; i < 35; i++) {
                if (dbgroup5[i+1] == 1) {
                    MainActivity.tempforgroup[i] = 0;
                    MainActivity.tempControlforgroup[i] = 0;
                    MainActivity.arraySizeforgroup--;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                }
            }
        }
    }
    static public void Group6Onclick(View view){
                checkLightState();
        if (MainActivity.groupControl[5] == 0) {
            MainActivity.groupControl[5] = 1;
            dbHelper3.searchGroup6();
            RedFragment.Group[5].setBackgroundResource(R.drawable.groupbutton6);
            GreenFragment.Group[5].setBackgroundResource(R.drawable.groupbutton6);
            for (int i = 0; i < 35; i++) {
                if (dbgroup6[i+1] == 1) {
                    MainActivity.tempforgroup[i] = (byte) (i + 1);
                    MainActivity.tempControlforgroup[i] = (byte) (i + 1);
                    MainActivity.arraySizeforgroup++;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton6);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.groupbutton6);
                }
            }

        } else{
            MainActivity.groupControl[5] = 0;
            dbHelper3.searchGroup6();
            RedFragment.Group[5].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[5].setBackgroundResource(R.drawable.smbutton);
            for (int i = 0; i < 35; i++) {
                if (dbgroup6[i+1] == 1) {
                    MainActivity.tempforgroup[i] = 0;
                    MainActivity.tempControlforgroup[i] = 0;
                    MainActivity.arraySizeforgroup--;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                }
            }
        }
    }
    static public void Group7Onclick(View view){
                checkLightState();
        if (MainActivity.groupControl[6] == 0) {
            MainActivity.groupControl[6] = 1;
            dbHelper3.searchGroup7();
            RedFragment.Group[6].setBackgroundResource(R.drawable.groupbutton7);
            GreenFragment.Group[6].setBackgroundResource(R.drawable.groupbutton7);
            for (int i = 0; i < 35; i++) {
                if (dbgroup7[i+1] == 1) {
                    MainActivity.tempforgroup[i] = (byte) (i + 1);
                    MainActivity.tempControlforgroup[i] = (byte) (i + 1);
                    MainActivity.arraySizeforgroup++;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton7);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.groupbutton7);
                }
            }

        } else{
            MainActivity.groupControl[6] = 0;
            dbHelper3.searchGroup7();
            RedFragment.Group[6].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[6].setBackgroundResource(R.drawable.smbutton);
            for (int i = 0; i < 35; i++) {
                if (dbgroup7[i+1] == 1) {
                    MainActivity.tempforgroup[i] = 0;
                    MainActivity.tempControlforgroup[i] = 0;
                    MainActivity.arraySizeforgroup--;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                }
            }
        }
    }
    static public void Group8Onclick(View view){
                checkLightState();
        if (MainActivity.groupControl[7] == 0) {
            MainActivity.groupControl[7] = 1;
            dbHelper3.searchGroup8();
            RedFragment.Group[7].setBackgroundResource(R.drawable.groupbutton8);
            GreenFragment.Group[7].setBackgroundResource(R.drawable.groupbutton8);
            for (int i = 0; i < 35; i++) {
                if (dbgroup8[i+1] == 1) {
                    MainActivity.tempforgroup[i] = (byte) (i + 1);
                    MainActivity.tempControlforgroup[i] = (byte) (i + 1);
                    MainActivity.arraySizeforgroup++;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.groupbutton8);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.groupbutton8);
                }
            }

        } else{
            MainActivity.groupControl[7] = 0;
            dbHelper3.searchGroup8();
            RedFragment.Group[7].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[7].setBackgroundResource(R.drawable.smbutton);
            for (int i = 0; i < 35; i++) {
                if (dbgroup8[i+1] == 1) {
                    MainActivity.tempforgroup[i] = 0;
                    MainActivity.tempControlforgroup[i] = 0;
                    MainActivity.arraySizeforgroup--;
                    RedFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                    GreenFragment.btn[i].setBackgroundResource(R.drawable.smbutton);
                }
            }
        }
    }
//컨트롤 부분에서 그룹버튼입니다.
    static public void setupOn() {
        if(arraySize>0){
            OnArray[0] = (byte) 0xFA;
            OnArray[1] = (byte) 0xEF;
            OnArray[2] = (byte) arraySize;
            OnArray[arraySize + 3] = 0x03;
            for (int i = 3; i < arraySize + 3; i++) {
                for(int k=0; k<35; k++){
                    if(temp[k] > 0){
                        OnArray[i] = temp[k];
                        temp[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupOn2() {
        if(groupControl[0] == 1 || groupControl[1] == 1 || groupControl[2] == 1 || groupControl[3] == 1 || groupControl[4] == 1|| groupControl[5] == 1 || groupControl[6] == 1|| groupControl[7] == 1){
            OnArray[0] = (byte) 0xFA;
            OnArray[1] = (byte) 0xEF;
            OnArray[2] = (byte) arraySizeforgroup;
            OnArray[arraySizeforgroup + 3] = 0x03;
            for (int i = 3; i < arraySizeforgroup + 3; i++) {
                for(int k=0; k<35; k++){
                    if(tempforgroup[k] > 0){
                        OnArray[i] = tempforgroup[k];
                        tempforgroup[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupOff() {
        if(arraySize >0){
            OffArray[0] = (byte) 0xFA;
            OffArray[1] = (byte) 0xEF;
            OffArray[2] = (byte) arraySize;
            OffArray[arraySize + 3] = 0x04;
            for (int i = 3; i < arraySize + 3; i++) {
                for(int k=0; k<35; k++){
                    if(temp[k] > 0x00){
                        OffArray[i] = temp[k];
                        temp[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupOff2() {
        if(groupControl[0] == 1 || groupControl[1] == 1 || groupControl[2] == 1 || groupControl[3] == 1 || groupControl[4] == 1|| groupControl[5] == 1 || groupControl[6] == 1|| groupControl[7] == 1){
            OffArray[0] = (byte) 0xFA;
            OffArray[1] = (byte) 0xEF;
            OffArray[2] = (byte) arraySizeforgroup;
            OffArray[arraySizeforgroup + 3] = 0x04;
            for (int i = 3; i < arraySizeforgroup + 3; i++) {
                for(int k=0; k<35; k++){
                    if(tempforgroup[k] > 0x00){
                        OffArray[i] = tempforgroup[k];
                        tempforgroup[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupUp() {
        if(arraySize >0){
            UpArray[0] = (byte) 0xFA;
            UpArray[1] = (byte) 0xEF;
            UpArray[2] = (byte) arraySize;
            UpArray[arraySize + 3] = 0x01;
            for (int i = 3; i < arraySize + 3; i++) {
                for(int k=0; k<35; k++){
                    if(temp[k] > 0x00){
                        UpArray[i] = temp[k];
                        temp[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupUp2() {
        if(groupControl[0] == 1 || groupControl[1] == 1 || groupControl[2] == 1 || groupControl[3] == 1 || groupControl[4] == 1|| groupControl[5] == 1 || groupControl[6] == 1|| groupControl[7] == 1){
            UpArray[0] = (byte) 0xFA;
            UpArray[1] = (byte) 0xEF;
            UpArray[2] = (byte) arraySizeforgroup;
            UpArray[arraySizeforgroup + 3] = 0x01;
            for (int i = 3; i < arraySizeforgroup + 3; i++) {
                for(int k=0; k<35; k++){
                    if(tempforgroup[k] > 0x00){
                        UpArray[i] = tempforgroup[k];
                        tempforgroup[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupDown() {
        if(arraySize >0){
            DownArray[0] = (byte) 0xFA;
            DownArray[1] = (byte) 0xEF;
            DownArray[2] = (byte) arraySize;
            DownArray[arraySize + 3] = 0x02;
            for (int i = 3; i < arraySize + 3; i++) {
                for(int k=0; k<35; k++){
                    if(temp[k] > 0x00){
                        DownArray[i] = temp[k];
                        temp[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupDown2() {
        if(groupControl[0] == 1 || groupControl[1] == 1 || groupControl[2] == 1 || groupControl[3] == 1 || groupControl[4] == 1|| groupControl[5] == 1 || groupControl[6] == 1|| groupControl[7] == 1){
            DownArray[0] = (byte) 0xFA;
            DownArray[1] = (byte) 0xEF;
            DownArray[2] = (byte) arraySizeforgroup;
            DownArray[arraySizeforgroup + 3] = 0x02;
            for (int i = 3; i < arraySizeforgroup + 3; i++) {
                for(int k=0; k<35; k++){
                    if(tempforgroup[k] > 0x00){
                        DownArray[i] = tempforgroup[k];
                        tempforgroup[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupStop() {
        if(arraySize >0){
            StopArray[0] = (byte) 0xFA;
            StopArray[1] = (byte) 0xEF;
            StopArray[2] = (byte) arraySize;
            StopArray[arraySize + 3] = 0x00;
            for (int i = 3; i < arraySize + 3; i++) {
                for(int k=0; k<35; k++){
                    if(temp[k] > 0x00){
                        StopArray[i] = temp[k];
                        temp[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupStop2() {
        if(groupControl[0] == 1 || groupControl[1] == 1 || groupControl[2] == 1 || groupControl[3] == 1 || groupControl[4] == 1|| groupControl[5] == 1 || groupControl[6] == 1|| groupControl[7] == 1){
            StopArray[0] = (byte) 0xFA;
            StopArray[1] = (byte) 0xEF;
            StopArray[2] = (byte) arraySizeforgroup;
            StopArray[arraySizeforgroup + 3] = 0x00;
            for (int i = 3; i < arraySizeforgroup + 3; i++) {
                for(int k=0; k<35; k++){
                    if(tempforgroup[k] > 0x00){
                        StopArray[i] = tempforgroup[k];
                        tempforgroup[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupSetStop() {
        if(arraySize >0){
            SetStopArray[0] = (byte) 0xFA;
            SetStopArray[1] = (byte) 0xEF;
            SetStopArray[2] = (byte) arraySize;
            SetStopArray[arraySize + 3] = 0x07;
            for (int i = 3; i < arraySize + 3; i++) {
                for(int k=0; k<35; k++){
                    if(temp[k] > 0x00){
                        SetStopArray[i] = temp[k];
                        temp[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupSetStop2() {
        if(groupControl[0] == 1 || groupControl[1] == 1 || groupControl[2] == 1 || groupControl[3] == 1 || groupControl[4] == 1|| groupControl[5] == 1 || groupControl[6] == 1|| groupControl[7] == 1){
            SetStopArray[0] = (byte) 0xFA;
            SetStopArray[1] = (byte) 0xEF;
            SetStopArray[2] = (byte) arraySizeforgroup;
            SetStopArray[arraySizeforgroup + 3] = 0x07;
            for (int i = 3; i < arraySizeforgroup + 3; i++) {
                for(int k=0; k<35; k++){
                    if(tempforgroup[k] > 0x00){
                        SetStopArray[i] = tempforgroup[k];
                        tempforgroup[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupSetStore() {
        if(arraySize >0){
            SetStoreArray[0] = (byte) 0xFA;
            SetStoreArray[1] = (byte) 0xEF;
            SetStoreArray[2] = (byte) arraySize;
            SetStoreArray[arraySize + 3] = 0x08;
            for (int i = 3; i < arraySize + 3; i++) {
                for(int k=0; k<35; k++){
                    if(temp[k] > 0x00){
                        SetStoreArray[i] = temp[k];
                        temp[k]=0;
                        break;
                    }
                }
            }
        }
    }
    static public void setupSetStore2() {
        if(groupControl[0] == 1 || groupControl[1] == 1 || groupControl[2] == 1 || groupControl[3] == 1 || groupControl[4] == 1|| groupControl[5] == 1 || groupControl[6] == 1|| groupControl[7] == 1){
            SetStoreArray[0] = (byte) 0xFA;
            SetStoreArray[1] = (byte) 0xEF;
            SetStoreArray[2] = (byte) arraySizeforgroup;
            SetStoreArray[arraySizeforgroup + 3] = 0x08;
            for (int i = 3; i < arraySizeforgroup + 3; i++) {
                for(int k=0; k<35; k++){
                    if(tempforgroup[k] > 0x00){
                        SetStoreArray[i] = tempforgroup[k];
                        tempforgroup[k]=0;
                        break;
                    }
                }
            }
        }
    }

    static public void resetOnArray() {
        for (int i = 0; i < 35; i++) {
            temp[i]=tempControl[i];
            tempforgroup[i]=tempControlforgroup[i];
        }
        for (int i=0; i < 39; i++){
          OnArray[i] = 0;
        }
    }
    static public void resetOffArray() {
        for (int i = 0; i < 35; i++) {
            temp[i]=tempControl[i];
            tempforgroup[i]=tempControlforgroup[i];
        }
        for (int i=0; i < 39; i++){
            OffArray[i] = 0;
        }
    }
    static public void resetUpArray() {
        for (int i = 0; i < 35; i++) {
            temp[i]=tempControl[i];
            tempforgroup[i]=tempControlforgroup[i];
        }
        for (int i=0; i < 39; i++){
            UpArray[i] = 0;
        }
    }
    static public void resetDownArray() {
        for (int i = 0; i < 35; i++) {
            temp[i]=tempControl[i];
            tempforgroup[i]=tempControlforgroup[i];
        }
        for (int i=0; i < 39; i++){
            DownArray[i] = 0;
        }
    }
    static public void resetStopArray() {
        for (int i = 0; i < 35; i++) {
            temp[i]=tempControl[i];
            tempforgroup[i]=tempControlforgroup[i];
        }
        for (int i=0; i < 39; i++){
            StopArray[i] = 0;
        }
    }
    static public void resetSetStopArray() {
        for (int i = 0; i < 35; i++) {
            temp[i]=tempControl[i];
            tempforgroup[i]=tempControlforgroup[i];
        }
        for (int i=0; i < 39; i++){
            SetStopArray[i] = 0;
        }
    }
    static public void resetSetStoreArray() {
        for (int i = 0; i < 35; i++) {
            temp[i]=tempControl[i];
            tempforgroup[i]=tempControlforgroup[i];
        }
        for (int i=0; i < 39; i++){
            SetStoreArray[i] = 0;
        }
    }

    //전체선택하는거
    static public void AllSelectOnclick(View view) {

        for(int i=0; i<8; i++){
            MainActivity.groupControl[i] = 0;
            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
        }

        for (int i = 0; i < 35; i++) {
            arr[i] = 1;
            RedFragment.btn[i].setBackgroundResource(R.drawable.buttonforgroupon);
            GreenFragment.btn[i].setBackgroundResource(R.drawable.buttonforgroupon);

            //그룹도 초기화를 위해서
            MainActivity.tempforgroup[i] = 0;
            MainActivity.tempControlforgroup[i] = 0;

        }
        MainActivity.arraySizeforgroup = 0;
        arraySize = 35;
        temp[0] = 0x01;
        temp[1] = 0x02;
        temp[2] = 0x03;
        temp[3] = 0x04;
        temp[4] = 0x05;
        temp[5] = 0x06;
        temp[6] = 0x07;
        temp[7] = 0x08;
        temp[8] = 0x09;
        temp[9] = 0xA;
        temp[10] = 0xB;
        temp[11] = 0xC;
        temp[12] = 0xD;
        temp[13] = 0xE;
        temp[14] = 0xF;
        temp[15] = 0x10;
        temp[16] = 0x11;
        temp[17] = 0x12;
        temp[18] = 0x13;
        temp[19] = 0x14;
        temp[20] = 0x15;
        temp[21] = 0x16;
        temp[22] = 0x17;
        temp[23] = 0x18;
        temp[24] = 0x19;
        temp[25] = 0x1A;
        temp[26] = 0x1B;
        temp[27] = 0x1C;
        temp[28] = 0x1D;
        temp[29] = 0x1E;
        temp[30] = 0x1F;
        temp[31] = 0x20;
        temp[32] = 0x21;
        temp[33] = 0x22;
        temp[34] = 0x23;
        for(int i =0; i < 35; i++){

            tempControl[i] = temp[i];
        }
    }
    static public void AllSelectOnclickGroup(View v){
        for(int i=0; i<35; i++){
            btng[i] =1;
            btngg[i].setBackgroundResource(R.drawable.buttonforgroupon);
        }
    }
    static public void AllCancelOnclickGroup(View v){
        for(int i=0; i<35; i++){
            btng[i] =0;
            btngg[i].setBackgroundResource(R.drawable.buttonforgroupoff);
        }
    }
    //전체해체 하느거
    static public void AllCancelOnclick(View view) {
//        for(int i=0; i<8; i++){
//            MainActivity.groupControl[i] = 0;
//            RedFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
//            GreenFragment.Group[i].setBackgroundResource(R.drawable.smbutton);
//        }

        for (int i = 0; i < 35; i++) {
            arr[i] = 0;
            RedFragment.btn[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            GreenFragment.btn[i].setBackgroundResource(R.drawable.buttonforgroupoff);
            temp[i]=0;
            tempControl[i] = temp[i];
//            MainActivity.tempforgroup[i] = 0;
//            MainActivity.tempControlforgroup[i] = 0;
//            MainActivity.arraySizeforgroup--;
        }
//        MainActivity.arraySizeforgroup = 0;
        arraySize = 0;
    }

    static public void buttonAnimation(Button[] Group, Button[] btn) {
        for(int i=0; i<8; i++) {
            if(MainActivity.groupControl[i] > 0) {
                //해당하는 그룹을 애니메이션 효과
                Group[i].startAnimation(MainActivity.alphaAni);
                int[] dbgroup = new int[36];
                switch (i) {
                    case 0: dbgroup = dbgroup1; break;
                    case 1: dbgroup = dbgroup2; break;
                    case 2: dbgroup = dbgroup3; break;
                    case 3: dbgroup = dbgroup4; break;
                    case 4: dbgroup = dbgroup5; break;
                    case 5: dbgroup = dbgroup6; break;
                    case 6: dbgroup = dbgroup7; break;
                    case 7: dbgroup = dbgroup8; break;
                }
                for(int j=1; j<dbgroup1.length; j++) {
                    if(dbgroup[j] > 0) {
                        // 그룹에 딸린 조명리스트 들을 켤 때
                        btn[j-1].startAnimation(MainActivity.alphaAni);
                    }
                }
            }
        }
        for(int i=0; i<35; i++) {
            if(MainActivity.tempControl[i] > 0) {
                // 그룹을 사용하지않은 조명만 사용할 떄
                btn[i].startAnimation(MainActivity.alphaAni);
                Log.d(ACTIVITY_SERVICE, "alpha" + i);
            }
        }

    }
    //Light Number Change buutton
     public void changeLightNumber(){
        final CharSequence[] items = {"35", "34" ,"33" ,"32" , "31" ,"30" , "29" ,"28" , "27" ,"26" , "25" , "24" ,
                "23" ,"22" , "21" , "20" ,"19" , "18" , "17" ,"16" ,"15" , "14" , "13" ,"12" , "11" , "10" ,"9" , "8", "7", "6", "5"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this
// 여기서 부터는 알림창의 속성 설정
        builder.setTitle("조명 개수를 선택하세요")        // 제목 설정
                .setItems(items, new DialogInterface.OnClickListener(){    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index){

                        dbHelper3.insert(35, 1, index, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                                , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                        dbHelper3.searchLightNumber();
                         for(int i=0; i<DB_LightNumber+1; i++){
                             RedFragment.btn[34-i].setVisibility(View.GONE);
                             GreenFragment.btn[34-i].setVisibility(View.GONE);
                         }
                         for(int i=0; i<35-DB_LightNumber; i++){
                            RedFragment.btn[i].setVisibility(View.VISIBLE);
                            GreenFragment.btn[i].setVisibility(View.VISIBLE);
                         }
                        DB_LightNumberfotTitle = 35 -DB_LightNumber;
                        MenuItem item = mMenu.findItem(R.id.lightNumber);
//                        item.setTitle(""+ DB_LightNumberfotTitle +"");
                        mMenu.getItem(0).setTitle(""+ DB_LightNumberfotTitle +"");

                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }




    @Override
    protected void onStart() {
        super.onStart();
        //블루투스가 꺼져있을때 켜는것
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }    }


    //DB 부분
    public class DBHelper extends SQLiteOpenHelper {

        // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // DB를 새로 생성할 때 호출되는 함수
        @Override
        public void onCreate(SQLiteDatabase db) {
            // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
            db.execSQL("CREATE TABLE bb1a (_id INTEGER PRIMARY KEY AUTOINCREMENT,  title INTEGER, onoff INTEGER, item1 INTEGER, item2 INTEGER, item3 INTEGER, item4 INTEGER, item5 INTEGER, item6 INTEGER, item7 INTEGER, item8 INTEGER, item9 INTEGER, item10 INTEGER, item11 INTEGER, item12 INTEGER, item13 INTEGER, item14 INTEGER, item15 INTEGER, item16 INTEGER, item17 INTEGER, item18 INTEGER, item19 INTEGER, item20 INTEGER, item21 INTEGER, item22 INTEGER, item23 INTEGER, item24 INTEGER, item25 INTEGER, item26 INTEGER, item27 INTEGER, item28 INTEGER, item29 INTEGER, item30 INTEGER, item31 INTEGER, item32 INTEGER, item33 INTEGER, item34 INTEGER, item35 INTEGER);");
        }

        // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public void insert(int title, int onoff, int item1, int item2, int item3, int item4, int item5, int item6, int item7, int item8, int item9, int item10, int item11, int item12, int item13, int item14, int item15, int item16, int item17, int item18, int item19, int item20, int item21, int item22, int item23, int item24, int item25, int item26, int item27, int item28, int item29, int item30, int item31, int item32, int item33, int item34, int item35) {
            // 읽고 쓰기가 가능하게 DB 열기
            SQLiteDatabase db = getWritableDatabase();
            // DB에 입력한 값으로 행 추가
            db.execSQL("INSERT INTO bb1a VALUES(null, " + title + ", " + onoff + ", " + item1 + ", " + item2 + ", " + item3 + ", " + item4 + ", " + item5 + ", " + item6 + ", " + item7 + ", " + item8 + ", " + item9 + ", " + item10 + ", " + item11 + ", " + item12 + ", " + item13 + ", " + item14 + ", " + item15 + ", " + item16 + ", " + item17 + ", " + item18 + ", " + item19 + ", " + item20 + ", " + item21 + ", " + item22 + ", " + item23 + ", " + item24 + ", " + item25 + ", " + item26 + ", " + item27 + ", " + item28 + ", " + item29 + ", " + item30 + ", " + item31 + ", " + item32 + ", " + item33 + ", " + item34 + ", " + item35 + ");");
            db.close();
        }



        public  void searchLightNumber() {
            // 읽기가 가능하게 DB 열기
            SQLiteDatabase db = getReadableDatabase();
            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            Cursor cursor = db.rawQuery("SELECT * FROM bb1a where title='35'", null);
            while (cursor.moveToNext()) {
                DB_LightNumber = cursor.getInt(3);
            }
        }

        public  void searchGroup1() {
            // 읽기가 가능하게 DB 열기
            SQLiteDatabase db = getReadableDatabase();
            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            Cursor cursor = db.rawQuery("SELECT * FROM bb1a where title='11'", null);
            while (cursor.moveToNext()) {
                for (int i = 0; i < 36; i++) {
                    dbgroup1[i] = cursor.getInt(i+2);
                }
            }
        }

        public void searchGroup2() {
            // 읽기가 가능하게 DB 열기
            SQLiteDatabase db = getReadableDatabase();
            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            Cursor cursor = db.rawQuery("SELECT * FROM bb1a where title='22'", null);
            while (cursor.moveToNext()) {
                for (int i = 0; i < 36; i++) {
                    dbgroup2[i] = cursor.getInt(i + 2);
                }
            }
        }
        public void searchGroup3() {
            // 읽기가 가능하게 DB 열기
            SQLiteDatabase db = getReadableDatabase();
            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            Cursor cursor = db.rawQuery("SELECT * FROM bb1a where title='33'", null);
            while (cursor.moveToNext()) {
                for (int i = 0; i < 36; i++) {
                    dbgroup3[i] = cursor.getInt(i + 2);
                }
            }
        }
        public void searchGroup4() {
            // 읽기가 가능하게 DB 열기
            SQLiteDatabase db = getReadableDatabase();
            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            Cursor cursor = db.rawQuery("SELECT * FROM bb1a where title='44'", null);
            while (cursor.moveToNext()) {
                for (int i = 0; i < 36; i++) {
                    dbgroup4[i] = cursor.getInt(i + 2);
                }
            }
        }
        public void searchGroup5() {
            // 읽기가 가능하게 DB 열기
            SQLiteDatabase db = getReadableDatabase();
            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            Cursor cursor = db.rawQuery("SELECT * FROM bb1a where title='55'", null);
            while (cursor.moveToNext()) {
                for (int i = 0; i < 36; i++) {
                    dbgroup5[i] = cursor.getInt(i + 2);
                }
            }
        }
        public void searchGroup6() {
            // 읽기가 가능하게 DB 열기
            SQLiteDatabase db = getReadableDatabase();
            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            Cursor cursor = db.rawQuery("SELECT * FROM bb1a where title='66'", null);
            while (cursor.moveToNext()) {
                for (int i = 0; i < 36; i++) {
                    dbgroup6[i] = cursor.getInt(i + 2);
                }
            }
        }
        public void searchGroup7() {
            // 읽기가 가능하게 DB 열기
            SQLiteDatabase db = getReadableDatabase();
            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            Cursor cursor = db.rawQuery("SELECT * FROM bb1a where title='77'", null);
            while (cursor.moveToNext()) {
                for (int i = 0; i < 36; i++) {
                    dbgroup7[i] = cursor.getInt(i + 2);
                }
            }
        }
        public void searchGroup8() {
            // 읽기가 가능하게 DB 열기
            SQLiteDatabase db = getReadableDatabase();
            // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
            Cursor cursor = db.rawQuery("SELECT * FROM bb1a where title='88'", null);
            while (cursor.moveToNext()) {
                for (int i = 0; i < 36; i++) {
                    dbgroup8[i] = cursor.getInt(i + 2);
                }
            }
        }

        public void delete(int z) {
            SQLiteDatabase db = getWritableDatabase();
            // 입력한 항목과 일치하는 행 삭제
            db.execSQL("DELETE FROM bb1a WHERE title='"+ z +"';");
            for (int i = 0; i < 36; i++) {
                dbgroup1[i] = 0;
            }
            db.close();
        }
        public void delete2(int z) {
            SQLiteDatabase db = getWritableDatabase();
            // 입력한 항목과 일치하는 행 삭제
            db.execSQL("DELETE FROM bb1a WHERE title='"+ z +"';");
            for (int i = 0; i < 36; i++) {
                dbgroup2[i] = 0;
            }
            db.close();
        }
        public void delete3(int z) {
            SQLiteDatabase db = getWritableDatabase();
            // 입력한 항목과 일치하는 행 삭제
            db.execSQL("DELETE FROM bb1a WHERE title='"+ z +"';");
            for (int i = 0; i < 36; i++) {
                dbgroup3[i] = 0;
            }
            db.close();
        }
        public void delete4(int z) {
            SQLiteDatabase db = getWritableDatabase();
            // 입력한 항목과 일치하는 행 삭제
            db.execSQL("DELETE FROM bb1a WHERE title='"+ z +"';");
            for (int i = 0; i < 36; i++) {
                dbgroup4[i] = 0;
            }
            db.close();
        }
        public void delete5(int z) {
            SQLiteDatabase db = getWritableDatabase();
            // 입력한 항목과 일치하는 행 삭제
            db.execSQL("DELETE FROM bb1a WHERE title='"+ z +"';");
            for (int i = 0; i < 36; i++) {
                dbgroup5[i] = 0;
            }
            db.close();
        }
        public void delete6(int z) {
            SQLiteDatabase db = getWritableDatabase();
            // 입력한 항목과 일치하는 행 삭제
            db.execSQL("DELETE FROM bb1a WHERE title='"+ z +"';");
            for (int i = 0; i < 36; i++) {
                dbgroup6[i] = 0;
            }
            db.close();
        }
        public void delete7(int z) {
            SQLiteDatabase db = getWritableDatabase();
            // 입력한 항목과 일치하는 행 삭제
            db.execSQL("DELETE FROM bb1a WHERE title='"+ z +"';");
            for (int i = 0; i < 36; i++) {
                dbgroup7[i] = 0;
            }
            db.close();
        }
        public void delete8(int z) {
            SQLiteDatabase db = getWritableDatabase();
            // 입력한 항목과 일치하는 행 삭제
            db.execSQL("DELETE FROM bb1a WHERE title='"+ z +"';");
            for (int i = 0; i < 36; i++) {
                dbgroup8[i] = 0;
            }
            db.close();
        }

    }
//DB 부분



}
