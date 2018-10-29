package com.example.bindservice1;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class bindservice1 extends Activity {

    LocalService ls;
    boolean bound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindservice1);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Button button = (Button)findViewById(R.id.current_time);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bindservice1.this,LocalService.class);
                bindService(intent,sc,BIND_AUTO_CREATE);//绑定服务
                if(bound){
                    Toast.makeText(bindservice1.this,ls.getCurrentTime(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected  void onStop()
    {
        super.onStop();
        if(bound){
            bound =false;
            unbindService(sc);//解除绑定
        }
    }

    //该对象用于判定是否绑定，或者绑定后进行的操作
    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //获得自定义的localbinder对象
            LocalService.LocalBinder binder = (LocalService.LocalBinder)iBinder;
            //获取local service对象
            ls = binder.getService();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    };
}
