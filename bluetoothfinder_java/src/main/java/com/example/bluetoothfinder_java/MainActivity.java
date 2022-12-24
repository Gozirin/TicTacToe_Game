package com.example.bluetoothfinder_java;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // DOWNLOAD A VYSOR TO VIEW & SHARE
    // DOWNLOAD A VYSOR TO VIEW & SHARE
    // DOWNLOAD A VYSOR TO VIEW & SHARE

    // VARIABLES DECLARED
    ListView listView;
    TextView statusTextView;
    Button searchButton;
    ArrayList<String> bluetoothDevices = new ArrayList<>();
    ArrayList<String> addresses = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("Action",action);

            if (bluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                statusTextView.setText("Finished....");
                searchButton.setEnabled(true);
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                @SuppressLint("MissingPermission")
                String name = device.getName();
                String address = device.getAddress();
                String rssi = Integer.toString(intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE));
                //Log.i( "Device Found" , "Name"+ name + "Address:" + address + "RSSI:" + rssi);

                // WITH VYSOR THE BLUETOOTH ADDRESSES SHOULD APPEAR HERE {}
                if (!addresses.contains(address)) {
                    addresses.add(address);
                    String deviceString = "";
                    if ( name == null || name.equals("")) {
                        deviceString = address + "- RSSI " + rssi + "dBm";
                    } else {
                        deviceString = name + "- RSSI " + rssi + "dBm";
                    }
                    bluetoothDevices.add(deviceString);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
            // DOWNLOAD A VYSOR TO VIEW & SHARE
        }
    };

    // ADAPTER
    BluetoothAdapter bluetoothAdapter;

    @SuppressLint("MissingPermission")
    public void searchClicked (View view) {
        statusTextView.setText("Searching.....");
        searchButton.setEnabled(false);
        bluetoothDevices.clear();
        addresses.clear();
        bluetoothAdapter.startDiscovery();

    }

    // FIND VIEWS BY IDS.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id. listView);
        statusTextView = findViewById(R.id.statusTextView);
        searchButton = findViewById(R.id.searchButton);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);

        // BLUETOOTH ACTION
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        registerReceiver(broadcastReceiver , intentFilter);


    }
}