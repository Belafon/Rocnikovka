package com.belafon.pvpsurvival.TheGame.Server;


import android.icu.text.LocaleDisplayNames;
import android.util.Log;

import com.belafon.pvpsurvival.TheGame.Communication.Getters;
import com.belafon.pvpsurvival.TheGame.GameActivity;
import com.belafon.pvpsurvival.TheGame.Server.Chat.Write_text;
import com.belafon.pvpsurvival.TheGame.SetActivity.SetActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable{
    private static final String TAG = "Client";
    private Socket clientSocket;

    @Override
    public void run() {
            Log.d(TAG, "run: Lets set Socket...");
            while (true){
                try{
                    this.clientSocket = new Socket("192.168.0.126", 25565);
                }catch (UnknownHostException e){
                    SetActivity.makeToastText("Network exception invoked");
                    Log.d(TAG, "run:  " + e);
                } catch (IOException i) {
                    SetActivity.makeToastText("Network exception invoked");
                    Log.d(TAG, "run:  " + i);
                }
                if(clientSocket != null)break;
            }
        try {
            //BufferedWriter out =  new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())),
                    true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            startListener(in);
            try {
                String string = "chat kokoska";
                out.println(string/* + "\r\n"*/);
               // out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startListener(final BufferedReader in) {
        // TODO Auto-generated method stub
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                GameActivity.setNumberOfCurrentThreads(1, "listener", -1);
                // TODO Auto-generated method stub
                while(true) {
                    String string = null;
                    try {
                        string = in.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(string != null) {
                        Log.d(TAG, "run: " + string + " MESSAGE ACEPTED MESSAGE ACEPTED");
                        makeThreadWorker(string);
                    }
                }
            }

            private void makeThreadWorker(final String string) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: Client: makeThreadWorker: new Thread created");
                        decomposeTheString(string);
                        Log.d(TAG, "run: Client: Listener continue");
                    }
                }).start();
            }
        });
        thread.start();
        System.out.println("Client: Listener strated!");
    }

    public static void write(String message){
        Log.d(TAG, "write: Lets write text!!!!!!!!!!!!!");
        Write_text write_text = new Write_text(message);
        Log.d(TAG, "write: new object write text set");
        write_text.execute();
        Log.d(TAG, "write: text was written");
    }


    // nejprve zjišťujeme typ zprávy, pak od koho to přišlo
    public static void decomposeTheString(String value) {
        // TODO Auto-generated method stub
        String[] cisla = value.split(" ");
        String typeAction;
        typeAction = cisla[0];
        switch(typeAction) {
            case "getter":// getter
                    Getters.findGetter(cisla);
                break;
            case "setter_multiple":  // setter multiple

                break;
            case "chat": // chat
                showMessage(cisla[1]);
                break;
            case "get_time": // get time
                break;
            case "beginning_info": // beginning info
                setOnTheBeginning(cisla);
                break;
            case "defaultStartInfo":
                defaultStartInfo(cisla);
                break;
            default:
                Log.d(TAG, "decomposeTheString: Some wierd default!!!!");
                break;
        }
        System.out.println("Server: rewriteVariable: The Thread is ending...");
    }

    private static void defaultStartInfo(String[] cisla) {
        GameActivity.PlayersId = Integer.parseInt(cisla[1]);
        GameActivity.GameId = Integer.parseInt(cisla[2]);
        Log.d(TAG, "defaultStartInfo: defaultStartInfo seted");
    }

    private static void showMessage(String message) {
        GameActivity.gameActivit.showForeignMessage(message);
    }

    private static void setOnTheBeginning(String[] cisla) {

    }
}
