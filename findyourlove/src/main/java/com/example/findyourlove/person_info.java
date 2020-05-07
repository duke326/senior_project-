package com.example.findyourlove;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
//import com.personinfo.util.ActivityCollector;
//import com.example.findyourlove.ItemGroup;
//import com.example.findyourlove.RoundImageView;
//import com.example.findyourlove.TitleLayout;
import java.util.ArrayList;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.impl.NimUIKitImpl;

import android.os.Handler;


public class person_info extends AppCompatActivity {
    private ItemGroup ig_id,ig_name,ig_gender,ig_region,ig_brithday;
    private LinearLayout ll_portrait;
    private RoundImageView ri_portrati;
    //private TitleLayout titleLayout;
    private int id = 1;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 0x12:
                    String s = (String) msg.obj;
                    ig_name.getContentEdt().setText(s);
                    break;
                case 0x13:
                    String ss = (String) msg.obj;
                    ig_region.getContentEdt().setText(ss);
                    break;
                case 0x14:
                    String sss = (String) msg.obj;
                    ig_brithday.getContentEdt().setText(sss);
                    break;
                case 0x15:
                    String ssss = (String) msg.obj;
                    ig_gender.getContentEdt().setText(ssss);
                    break;
                case 0x11:
                    ArrayList<String> l = (ArrayList)msg.obj;
                    ig_name.getContentEdt().setText(l.get(0));
                    break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityCollector.addActivity(this);
        Bundle bundle=this.getIntent().getExtras();
        System.out.println("ACCID IS " + bundle.getInt("accid"));
        id=bundle.getInt("accid");
        setContentView(R.layout.activity_person_info);

        ig_id = (ItemGroup)findViewById(R.id.ig_id);
        ig_name = (ItemGroup)findViewById(R.id.ig_name);
        ig_gender = (ItemGroup)findViewById(R.id.ig_gender);
        ig_region = (ItemGroup)findViewById(R.id.ig_region);
        ig_brithday = (ItemGroup)findViewById(R.id.ig_brithday);
        ll_portrait = (LinearLayout)findViewById(R.id.ll_portrait);
        ri_portrati = (RoundImageView)findViewById(R.id.ri_portrait);
        //titleLayout = (TitleLayout)findViewById(R.id.tl_title);
        Button chatbutton=findViewById(R.id.Chat);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NimUIKit.startP2PSession(getApplicationContext(),Integer.toString(id));
            }
        });

        ig_id.getContentEdt().setText(String.valueOf(id));
        getName(id);
        getBirth(id);
        getRegion(id);
        getGender(id);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


    private void getName(final int id){

        new Thread(new Runnable() {
            @Override

            public void run() {
                Message message = handler.obtainMessage();
                String ss;
                ss = com.example.findyourlove.user_db.db_getName2(id);
                message.what = 0x12;
                message.obj = ss;
                //System.out.println("开出的线程中ss = " +ss);
                handler.sendMessage(message);
            }
        }).start();

    }

    private void getRegion(final int id){

        new Thread(new Runnable() {
            @Override

            public void run() {
                Message message = handler.obtainMessage();
                String ss;
                ss = com.example.findyourlove.user_db.db_getRegion(id);
                message.what = 0x13;
                message.obj = ss;
                //System.out.println("开出的线程中ss = " +ss);
                handler.sendMessage(message);
            }
        }).start();

    }

    private void getBirth(final int id){

        new Thread(new Runnable() {
            @Override

            public void run() {
                Message message = handler.obtainMessage();
                String ss;
                ss = com.example.findyourlove.user_db.db_getBirth(id);
                message.what = 0x14;
                message.obj = ss;
                //System.out.println("开出的线程中ss = " +ss);
                handler.sendMessage(message);
            }
        }).start();

    }

    private void getGender(final int id){

        new Thread(new Runnable() {
            @Override

            public void run() {
                Message message = handler.obtainMessage();
                String ss;
                ss = com.example.findyourlove.user_db.db_getGender(id);
                message.what = 0x15;
                message.obj = ss;
                //System.out.println("开出的线程中ss = " +ss);
                handler.sendMessage(message);
            }
        }).start();

    }

    public void chat(View view){
        Bundle bundle=new Bundle();
        bundle.putInt("accid",id);
       // Intent intent=new Intent(this,message.class);
       // startActivity(intent,bundle);

        NimUIKit.startP2PSession(getApplicationContext(),Integer.toString(id));
    }

}
