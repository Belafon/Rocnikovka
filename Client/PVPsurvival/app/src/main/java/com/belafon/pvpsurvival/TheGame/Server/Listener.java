package com.belafon.pvpsurvival.TheGame.Server;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Listener {
  /*  private static final String TAG = "Listener";
    public static void setListener(){
        // TODO Auto-generated method stub
        Log.d(TAG, "startListener: Listener started!");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while(true) {
                    BufferedReader in = null;
                    Socket clientSocket = null;
                    try {
                        clientSocket = new Socket(Client.server_ip, Client.server_port);
                    } catch (IOException e) {
                        Log.d(TAG, "run: PROBLEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEMMMMMMMMMMMMMMMMMMMMMMM");
                        e.printStackTrace();
                    }
                    try {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String s = null;
                    try{
                        if(clientSocket.isClosed()) Log.d(TAG, "run: EEEEEEEEEEEEEEEEEEEEEEEEEE2");else
                            Log.d(TAG, "setListener: IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII2");
                        s = in.readLine();
                        Log.d(TAG, "run: KOKOSKA");
                    }catch(Exception e){
                        Log.d(TAG, "run: WIERD PROBLEM " + e);
                    }
                    if(s != null){
                        Log.d(TAG, "run: The Message = " + s);
                    }
                }
            }
        });
        thread.start();

    }*/
}
