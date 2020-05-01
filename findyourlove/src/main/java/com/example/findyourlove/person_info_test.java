package com.example.findyourlove;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.findyourlove.R;

import java.util.ArrayList;

public class person_info_test extends Fragment {
    private ItemGroup ig_id,ig_name,ig_gender,ig_region,ig_brithday;
    private int id = 1;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_person_info, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

/*        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(person_info_test.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/

        //setContentView(R.layout.activity_person_info);

        ig_id = view.findViewById(R.id.ig_id);
        ig_name = view.findViewById(R.id.ig_name);
        ig_gender = view.findViewById(R.id.ig_gender);
        ig_region = view.findViewById(R.id.ig_region);
        ig_brithday = view.findViewById(R.id.ig_brithday);
        //ll_portrait = (LinearLayout)findViewById(R.id.ll_portrait);
        //ri_portrati = (RoundImageView)findViewById(R.id.ri_portrait);

        ig_id.getContentEdt().setText(String.valueOf(id));
        getName(id);
        getBirth(id);
        getRegion(id);
        getGender(id);
    }

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

}
