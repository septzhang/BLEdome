package com.ajie.bledome.adapter;


import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ajie.bledome.R;

import java.util.List;

public class DeviceAdapter extends BaseAdapter {

    private Context mContext;
    private List<BluetoothDevice> mDate;

    public  DeviceAdapter(List<BluetoothDevice> Date, Context context){
        mDate = Date;
        mContext = context;

    }
    @Override
    public int getCount() {
        return mDate.size();
    }

    @Override
    public Object getItem(int position) {
        return mDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


    public class ViewHolder{
        public TextView textView1;
        public  TextView textView2;

    }
    public void refresh(List<BluetoothDevice> data){
        mDate = data;
        notifyDataSetChanged();
    }
}
