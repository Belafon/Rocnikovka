package com.belafon.pvpsurvival.TheGame.Server.Chat;


import android.os.AsyncTask;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.belafon.pvpsurvival.TheGame.Server.Client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Write_text extends AsyncTask<Void, Void, Void>{
    private static final String TAG = "Write_text";
    private String message;
    public Write_text(String message){
        this.message = message;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.d(TAG, "doInBackground: Lets create new SOCKET");
            Socket socket = createSocket();
            Log.d(TAG, "doInBackground: new SOCKET created!!!");
            BufferedWriter out =  new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(message + "\r\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
      return  null;
    }
    private Socket createSocket() {
        Socket socket = null;
        try {
            //"192.168.0.111"
            socket = new Socket("192.168.0.126", 25565);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }
}
