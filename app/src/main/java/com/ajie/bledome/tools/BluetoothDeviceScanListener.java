package com.ajie.bledome.tools;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothDeviceScanListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        // 这里可以把我们的将我们的设备添加到一个列表中
        System.out.println(device.getName() + " : " + device.getAddress());
    }
}