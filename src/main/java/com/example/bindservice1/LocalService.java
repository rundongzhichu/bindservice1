package com.example.bindservice1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;

import java.security.Provider;
import java.sql.Time;


/**
 * Created by 孙守财 on 2018/8/6.
 */

public class LocalService extends Service{

    private final IBinder binder = new LocalBinder();
    public class LocalBinder extends Binder{
        //返回当前服务的示例
        LocalService getService()
        {
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg0){
        return binder;
    }

    public String getCurrentTime(){
        android.text.format.Time time = new android.text.format.Time();
        time.setToNow();
        String currentTime = time.format("%Y-%m-%d %H:%M:%S");
        return currentTime;

    }
}
