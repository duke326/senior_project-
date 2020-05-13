package com.example.findyourlove;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.SQLException;

import com.netease.nim.uikit.api.NimUIKit;


import android.widget.Button;
import android.os.Bundle;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.impl.NimUIKitImpl;

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

    private int id = Integer.parseInt(loginactivity.accid);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=this.getIntent().getExtras();
        //System.out.println("ACCID IS " + bundle.getInt("accid"));
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
        Button back=findViewById(R.id.Back);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NimUIKit.startP2PSession(getApplicationContext(),Integer.toString(id));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Main.class));;
            }
        });

        //使箭头不可见
        ig_name.invisible();
        ig_gender.invisible();
        ig_region.invisible();
        ig_brithday.invisible();

        ig_id.getContentEdt().setText(String.valueOf(id));

        //从数据读取数据
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            Initial_Thread.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    Thread Initial_Thread =new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                System.out.println("Ready to upload");
                //ig_name.setTuser_db.getName2(id);
                ig_name.getContentEdt().setText(user_db.getName2(id));
                ig_brithday.getContentEdt().setText(user_db.getBirth2(id));
                ig_gender.getContentEdt().setText(user_db.getGender2(id));
                ig_region.getContentEdt().setText(user_db.getRegion2(id));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    });


 public void chat(View view){
        Bundle bundle=new Bundle();
        bundle.putInt("accid",id);
        Intent intent=new Intent(this,message.class);
        startActivity(intent,bundle);

        NimUIKit.startP2PSession(getApplicationContext(),Integer.toString(id));
    }

}
