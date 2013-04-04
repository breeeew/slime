package ru.SlimeEngine;

import java.util.Random;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class BluetoothUIManager extends Activity{
	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothService mBtService = null;
	private String mConnectedDeviceName = null;
	
	private static final int BUTTON_CLIENT = 1;
	private static final int BUTTON_SERVER = 2;
	
	private static final int buffer_size = 20; 
	public static int getBuffer_size(){
		return buffer_size;
	}
	byte[] buffer = null;
	
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
	
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    public LinearLayout mainLayout = null;
	//���� ��� Intent`��
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null){
        	Toast.makeText(this, "Bluetooth �� ��������������", Toast.LENGTH_LONG).show();
        	finish();
        	return;
        }
        mainLayout = new LinearLayout(this);
        LayoutParams mainLayoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        mainLayout.setBackgroundColor(Color.BLACK);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        android.widget.RelativeLayout.LayoutParams relativeLayoutParams = 
        		new android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.FILL_PARENT,
        													   android.widget.RelativeLayout.LayoutParams.FILL_PARENT);
        mainLayout.addView(relativeLayout, relativeLayoutParams);
        Button serverButton = new Button(getApplicationContext());
        Button clientButton = new Button(getApplicationContext());
        serverButton.setId(BUTTON_SERVER);
        clientButton.setId(BUTTON_CLIENT);
        serverButton.setText("������������ � ����");
        clientButton.setText("������� ����");

        
        RelativeLayout.LayoutParams viewParams1 = new RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT, android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
        viewParams1.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayout.addView(clientButton,viewParams1);
        
        RelativeLayout.LayoutParams viewParams = new RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT, android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
        viewParams.addRule(RelativeLayout.ABOVE, BUTTON_CLIENT);
        viewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        relativeLayout.addView(serverButton,viewParams);
        serverButton.setOnClickListener(buttonClickListener);
        clientButton.setOnClickListener(buttonClickListener);
        setContentView(mainLayout, mainLayoutParams);
        
        mainLayout.setId(111);
        mainLayout.setOnClickListener(buttonClickListener);
	}

	OnClickListener buttonClickListener = new OnClickListener() {
		
		public void onClick(View v) {
			Intent serverIntent = null;
			// TODO Auto-generated method stub
			
			switch(v.getId()){
			case BUTTON_SERVER:
				serverIntent = new Intent(getApplicationContext(), BluetoothDeviceListActivity.class);
	            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
	            break;
			case BUTTON_CLIENT:
				ensureDiscoverable();
			break;
			case 111:
				buffer = new byte[buffer_size];
				buffer[0] = (byte) (0 + (new Random().nextInt(255)));
				buffer[1] = (byte) (0 + (new Random().nextInt(255)));
				buffer[2] = (byte) (0 + (new Random().nextInt(255)));
				//buffer[3] = (byte) (0 + (new Random().nextInt(255)));
				//v.setBackgroundColor(Color.rgb(a[1], a[2], a[3]));
				
				sendMessage(buffer);
				//verifyHash(addHashToBytes(buffer));
//				if (verifyHash(addHashToBytes(buffer))) Toast.makeText(getApplicationContext(), "TRUE", Toast.LENGTH_LONG).show();
//				Toast.makeText(getApplicationContext(), MD5Hash.md5(buffer).toString(), Toast.LENGTH_LONG).show();
//				Toast.makeText(getApplicationContext(), new String(addHashToBytes(buffer)), Toast.LENGTH_LONG).show();
//				Toast.makeText(getApplicationContext(), new String(addHashToBytes(buffer)).substring(getBuffer_size()), Toast.LENGTH_LONG).show();
				break;
			}
		}
	};


	
	@Override
    public void onStart() {
        super.onStart();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
	}else {
        if (mBtService == null) setupBtService();
    }
	}
	
    @Override
    public synchronized void onResume() {
        super.onResume();

        if (mBtService != null) {

            if (mBtService.getState() == BluetoothService.STATE_NONE) {

              mBtService.start();
            }
        }
    }
	
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBtService != null) mBtService.stop();
    }
    
    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
            BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case REQUEST_CONNECT_DEVICE_SECURE:

            if (resultCode == Activity.RESULT_OK) {
                connectDevice(data, true);
            }
            break;
        case REQUEST_CONNECT_DEVICE_INSECURE:

            if (resultCode == Activity.RESULT_OK) {
                connectDevice(data, false);
            }
            break;
        case REQUEST_ENABLE_BT:

            if (resultCode == Activity.RESULT_OK) {

                setupBtService();
            } else {

                Toast.makeText(this, "Bluetooth �� �������!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    
    private void connectDevice(Intent data, boolean secure) {

        String address = data.getExtras()
            .getString(BluetoothDeviceListActivity.EXTRA_DEVICE_ADDRESS);

        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);

        mBtService.connect(device, secure);
    }
    
    private void setupBtService() {

 
        mBtService = new BluetoothService(this, mHandler);

    }
    
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_STATE_CHANGE:
                switch (msg.arg1) {
                case BluetoothService.STATE_CONNECTED:
                    //mTitle.setText(R.string.title_connected_to);
                    //mTitle.append(mConnectedDeviceName);
                    //mConversationArrayAdapter.clear();
                	Toast.makeText(getApplicationContext(),"��������� � " + mConnectedDeviceName, Toast.LENGTH_LONG).show();
                    break;
                case BluetoothService.STATE_CONNECTING:
                    //mTitle.setText(R.string.title_connecting);
                	Toast.makeText(getApplicationContext(), "���� �����������..", Toast.LENGTH_LONG).show();
                    break;
                case BluetoothService.STATE_LISTEN:
                	break;
                case BluetoothService.STATE_NONE:
                    //mTitle.setText(R.string.title_not_connected);
                    break;
                }
                break;
            case MESSAGE_WRITE:
                byte[] writeBuf = (byte[]) msg.obj;
                //writeBuf[1] = (byte) 255;
                //writeBuf[2] = (byte) 255;
                //writeBuf[3] = (byte) 255;
                mainLayout.setBackgroundColor(Color.rgb(writeBuf[0], writeBuf[1], writeBuf[2]));
                Toast.makeText(getApplicationContext(), Byte.toString(writeBuf[0]) + " / " + Byte.toString(writeBuf[1])+ " / "+Byte.toString(writeBuf[2]),Toast.LENGTH_LONG).show();
                break;
            case MESSAGE_READ:
                byte[] readBuf = (byte[]) msg.obj;
                mainLayout.setBackgroundColor(Color.rgb(readBuf[0],readBuf[1],readBuf[2]));
                Toast.makeText(getApplicationContext(), Byte.toString(readBuf[0])+ " / "+ Byte.toString(readBuf[1])+ " / "+Byte.toString(readBuf[2]),Toast.LENGTH_LONG).show();
                break;
            case MESSAGE_DEVICE_NAME:
                mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                Toast.makeText(getApplicationContext(), "Connected to "
                               + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                break;
            case MESSAGE_TOAST:
                Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
                               Toast.LENGTH_SHORT).show();
                break;
            }
        }
    };
    
    private void sendMessage(byte[] message) {
        if (mBtService.getState() != BluetoothService.STATE_CONNECTED) {
            Toast.makeText(this, "��� �����������", Toast.LENGTH_SHORT).show();
            return;
        }
     
            mBtService.write(message);
            buffer = null;
        }
    }
    

	

