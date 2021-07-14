package com.ajie.bledome.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ajie.bledome.controller.BlueToothController;
import com.ajie.bledome.R;

/**
 * 蓝牙控制界面
 *
 */
public class MainActivity extends AppCompatActivity {
    //请求码
    public static final int REQUEST_CODE = 0;
    private BlueToothController mController = new BlueToothController();
    private Toast mToast;
    Intent intent = new Intent();

    /**
     * 监听蓝牙开关的广播 监听蓝牙状态的改变
     */
//    当蓝牙的状态发生改变时，系统是会发出一个为 BluetoothAdapter.ACTION_STATE_CHANGED 的广播。
//    该广播携带两个参数，一个是BluetoothAdapter.EXTRA_PREVIOUS_STATE，表示之前的蓝牙状态。
//    另一个是BluetoothAdapter.EXTRA_STATE，表示当前的蓝牙状态。而它们的值为以下四个：
//    BluetoothAdapter.STATE_TURNING_ON;
//    BluetoothAdapter.STATE_ON;
//    BluetoothAdapter.STATE_TURNING_OFF;
//    BluetoothAdapter.STATE_OFF;
//    分别代表，打开中，已打开，关闭中，已关闭。
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    showToast("STATE_OFF");
                    break;
                case BluetoothAdapter.STATE_ON:
                    showToast("STATE_ON");
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    showToast("STATE_TURNING_ON");
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    showToast("STATE_TURNING_OFF");
                    break;
                default:
                    showToast("Unkown STATE");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //意图过滤器
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        //receiver监听filter广播
        registerReceiver(receiver, filter);
    }
    @Override
    protected void onDestroy() {
        //注销广播
        unregisterReceiver(receiver);
        super.onDestroy();
    }
    //是否支持蓝牙
    public void isSupportBlueTooth(View view) {
        boolean ret = mController.isSupportBlueTooth();
        showToast("support Bluetooth? " + ret);
    }
    //蓝牙是否能用（打开还是关闭）
    public void isBlueToothEnable(View view) {
        boolean ret = mController.getBlueToothStatus();
        showToast("Bluetooth enable？" + ret);
    }
    //打开蓝牙
    public void requestTurnOnBlueTooth(View view) {
        mController.turnOnBlueTooth(this, REQUEST_CODE);
    }
    //关闭蓝牙
    public void turnOffBlueTooth(View view) {
        mController.turnOffBlueTooth();
    }

    /**
     * 提示窗显示文本
     * @param text 显示文本
     */
    private void showToast(String text) {
        if( mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        }
        else {
            mToast.setText(text);
        }
        mToast.show();
    }

    /**
     * 检测是否打开成功
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //当发起请求蓝牙打开事件时，会告诉你用户选择的结果
        if( resultCode == RESULT_OK) {
            showToast("打开成功");
        }
        else {
            showToast("打开失败");
        }
    }
}