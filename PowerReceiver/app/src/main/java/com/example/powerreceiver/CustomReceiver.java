package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private final static String ACTION_CUSTOM_BROADCAST=
            BuildConfig.APPLICATION_ID+".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
       String intentAction=intent.getAction();
       if(intentAction!=null){
           String toastMessage="Unknown intent action";
           switch (intentAction){
               case Intent.ACTION_POWER_CONNECTED:
                   toastMessage="Power is connected";
                   break;
               case  Intent.ACTION_POWER_DISCONNECTED:
                   toastMessage="Power is disconnected";
                   break;
               case  ACTION_CUSTOM_BROADCAST:
                   toastMessage="custom broadcast received";
                   break;
           }
           Toast.makeText(context,toastMessage,Toast.LENGTH_SHORT).show();
       }
    }
}
