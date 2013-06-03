package com.engine.util.bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class BluetoothSrvice {

private static final String NAME_SECURE = "BluetoothGameSecure";
public static String getNameSecure(){
	return NAME_SECURE;
}

private static final String NAME_INSECURE = "BluetoothGameInsecure";
public static String getNameInsecure(){
	return NAME_INSECURE;
}

private static final UUID MY_UUID_SECURE =
UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
public static UUID getUUIDSecure(){
	return MY_UUID_SECURE;
}

private static final UUID MY_UUID_INSECURE =
UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");
public static UUID getUUIDINsecure(){
	return MY_UUID_INSECURE;
}

public static final int STATE_NONE = 0;
public static final int STATE_LISTEN = 1;
public static final int STATE_CONNECTING = 2;
public static final int STATE_CONNECTED = 3;

private final BluetoothAdapter mAdapter;
private ServerThread mSecureAcceptThread;
private ServerThread mInsecureAcceptThread;
private ConnectThread mConnectThread;
private ConnectedThread mConnectedThread;
private final Handler mHandler;
private static int mState;
public synchronized static int getState(){
	return mState;
}
public synchronized void setState(int st){
	mState = st;
	mHandler.obtainMessage().sendToTarget();
}

public BluetoothSrvice(Context context, Handler handler){
	mAdapter = BluetoothAdapter.getDefaultAdapter();
	mHandler = handler;
	setState(STATE_NONE);
}

private class ServerThread extends Thread{
	private final BluetoothServerSocket mmServerSocket;
	private String mSocketType;
	
	public ServerThread(boolean secure){
		BluetoothServerSocket tmp = null;
		mSocketType = secure ? "Secure":"Insecure";
		
		try{
			if (secure){
				tmp = mAdapter.listenUsingRfcommWithServiceRecord(NAME_SECURE,
                        MY_UUID_SECURE);
			}else {
                tmp = mAdapter.listenUsingRfcommWithServiceRecord(
                        NAME_INSECURE, MY_UUID_INSECURE);
            }
		}catch (IOException e) {
            //Log.e(TAG, "Socket Type: " + mSocketType + "listen() failed", e);
        }
		mmServerSocket = tmp;
	}
	public void run(){
		setName("ServerThread" + mSocketType);
		BluetoothSocket socket = null;
		while(BluetoothSrvice.getState() != STATE_CONNECTED){
			try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                //Log.e(TAG, "Socket Type: " + mSocketType + "accept() failed", e);
                break;
            }


            if (socket != null) {
                synchronized (BluetoothSrvice.this) {
                    switch (mState) {
                    case STATE_LISTEN:
                    case STATE_CONNECTING:
                        connected(socket, socket.getRemoteDevice(),
                                mSocketType);
                        break;
                    case STATE_NONE:
                    case STATE_CONNECTED:
                        try {
                            socket.close();
                        } catch (IOException e) {
                           // Log.e(TAG, "Could not close unwanted socket", e);
                        }
                        break;
                    }
                }
            }
		}
		
	}
	
	public void cancel() {
        //if (D) Log.d(TAG, "Socket Type" + mSocketType + "cancel " + this);
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            //Log.e(TAG, "Socket Type" + mSocketType + "close() of server failed", e);
        }
    }
}

private class ConnectThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private String mSocketType;

    public ConnectThread(BluetoothDevice device, boolean secure) {
        mmDevice = device;
        BluetoothSocket tmp = null;
        mSocketType = secure ? "Secure" : "Insecure";


        try {
            if (secure) {
                tmp = device.createRfcommSocketToServiceRecord(
                        MY_UUID_SECURE);
            } else {
                tmp = device.createRfcommSocketToServiceRecord(
                        MY_UUID_INSECURE);
            }
        } catch (IOException e) {
            //Log.e(TAG, "Socket Type: " + mSocketType + "create() failed", e);
        }
        mmSocket = tmp;
    }

    public void run() {
        //Log.i(TAG, "BEGIN mConnectThread SocketType:" + mSocketType);
        setName("ConnectThread" + mSocketType);


        mAdapter.cancelDiscovery();


        try {

            mmSocket.connect();
        } catch (IOException e) {
            // Close the socket
            try {
                mmSocket.close();
            } catch (IOException e2) {
                //Log.e(TAG, "unable to close() " + mSocketType +
                //        " socket during connection failure", e2);
            }
            connectionFailed();
            return;
        }


        synchronized (BluetoothSrvice.this) {
            mConnectThread = null;
        }


        connected(mmSocket, mmDevice, mSocketType);
    }

    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
            //Log.e(TAG, "close() of connect " + mSocketType + " socket failed", e);
        }
    }
}

private class ConnectedThread extends Thread {
    private final BluetoothSocket mmSocket;
    private final InputStream mmInStream;
    private final OutputStream mmOutStream;

    public ConnectedThread(BluetoothSocket socket, String socketType) {
       //Log.d(TAG, "create ConnectedThread: " + socketType);
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;

        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        } catch (IOException e) {
            //Log.e(TAG, "temp sockets not created", e);
        }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        byte[] buffer = new byte[BluetoothUIManager.getBuffer_size()+32];
        int bytes;


        while (true) {
            try {
            	
                bytes = mmInStream.read(buffer);

                if(verifyHash(buffer)){
                	mHandler.obtainMessage(BluetoothUIManager.MESSAGE_READ, bytes, -1, buffer)
                        .sendToTarget();
                }
            } catch (IOException e) {
                //Log.e(TAG, "disconnected", e);
                connectionLost();
                break;
            }
        }
    }

    //�������� ����������� �����
	public boolean verifyHash(byte[] b){
    	String hash = new String(b).substring(BluetoothUIManager.getBuffer_size());
    	byte[] outByte = new byte[BluetoothUIManager.getBuffer_size()];
        
    	for(int i = 0; i < BluetoothUIManager.getBuffer_size(); i++){
    		outByte[i] = b[i];
        }
    	return (hash.equals(new String(MD5Hash.md5(outByte))));
    }

    public void write(byte[] buffer) {
        try {
            mmOutStream.write(buffer);
            mHandler.obtainMessage(BluetoothUIManager.MESSAGE_WRITE, -1, -1, buffer)
                    .sendToTarget();
        } catch (IOException e) {
           // Log.e(TAG, "Exception during write", e);
        }
    }

    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
            //Log.e(TAG, "close() of connect socket failed", e);
        }
    }
}

private void connectionLost() {

    Message msg = mHandler.obtainMessage(BluetoothUIManager.MESSAGE_TOAST);
    Bundle bundle = new Bundle();
    bundle.putString(BluetoothUIManager.TOAST, "Device connection was lost");
    msg.setData(bundle);
    mHandler.sendMessage(msg);


    BluetoothSrvice.this.start();
}

public synchronized void start() {

    if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}


    if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

    setState(STATE_LISTEN);


    if (mSecureAcceptThread == null) {
        mSecureAcceptThread = new ServerThread(true);
        mSecureAcceptThread.start();
    }
    if (mInsecureAcceptThread == null) {
        mInsecureAcceptThread = new ServerThread(false);
        mInsecureAcceptThread.start();
    }
}

public synchronized void connected(BluetoothSocket socket, BluetoothDevice
        device, final String socketType) {
    

    if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}


    if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}


    if (mSecureAcceptThread != null) {
        mSecureAcceptThread.cancel();
        mSecureAcceptThread = null;
    }
    if (mInsecureAcceptThread != null) {
        mInsecureAcceptThread.cancel();
        mInsecureAcceptThread = null;
    }


    mConnectedThread = new ConnectedThread(socket, socketType);
    mConnectedThread.start();

   
    Message msg = mHandler.obtainMessage(BluetoothUIManager.MESSAGE_DEVICE_NAME);
    Bundle bundle = new Bundle();
    bundle.putString(BluetoothUIManager.DEVICE_NAME, device.getName());
    msg.setData(bundle);
    mHandler.sendMessage(msg);

    setState(STATE_CONNECTED);
}

public synchronized void connect(BluetoothDevice device, boolean secure) {


    if (mState == STATE_CONNECTING) {
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}
    }


    if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}


    mConnectThread = new ConnectThread(device, secure);
    mConnectThread.start();
    setState(STATE_CONNECTING);
}

private void connectionFailed() {

    Message msg = mHandler.obtainMessage(BluetoothUIManager.MESSAGE_TOAST);
    Bundle bundle = new Bundle();
    bundle.putString(BluetoothUIManager.TOAST, "Unable to connect device");
    msg.setData(bundle);
    mHandler.sendMessage(msg);


    BluetoothSrvice.this.start();
}
public synchronized void stop() {

    if (mConnectThread != null) {
        mConnectThread.cancel();
        mConnectThread = null;
    }

    if (mConnectedThread != null) {
        mConnectedThread.cancel();
        mConnectedThread = null;
    }

    if (mSecureAcceptThread != null) {
        mSecureAcceptThread.cancel();
        mSecureAcceptThread = null;
    }

    if (mInsecureAcceptThread != null) {
        mInsecureAcceptThread.cancel();
        mInsecureAcceptThread = null;
    }
    setState(STATE_NONE);
}

public void write(byte[] out) {

    ConnectedThread r;

    synchronized (this) {
        if (mState != STATE_CONNECTED) return;
        r = mConnectedThread;
    }
    r.write(addHashToBytes(out));
}

//��������� ����������� ����� md5 � ����� ���������
private byte[] addHashToBytes(byte[] b){
	byte[] hashBytes = MD5Hash.md5(b).getBytes();
	byte[] outBytes = new byte[BluetoothUIManager.getBuffer_size()+hashBytes.length];
	for(int i = 0; i < BluetoothUIManager.getBuffer_size(); i++){
		outBytes[i] = b[i];
		
	}
	for(int k = BluetoothUIManager.getBuffer_size(); k < BluetoothUIManager.getBuffer_size()+hashBytes.length; k++){
		outBytes[k]=hashBytes[k-BluetoothUIManager.getBuffer_size()];
		//Toast.makeText(getApplicationContext(), (new String(outBytes)), Toast.LENGTH_SHORT).show();
	}
	return (outBytes);
	}
}
