package com.example.findyourlove;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.findyourlove.R;
import com.microsoft.maps.Geopoint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import static android.provider.MediaStore.EXTRA_OUTPUT;

public class person_info_test extends Fragment {
    private ItemGroup ig_id,ig_name,ig_gender,ig_region,ig_brithday;
    //private int id = Integer.parseInt(loginactivity.accid);
    private int id = Integer.parseInt(loginactivity.accid);
    private TitleLayout titleLayout;
    private TextView tv_forward,tv_title;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_person_info_basic, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ig_id = view.findViewById(R.id.ig_id);
        ig_name = view.findViewById(R.id.ig_name);
        ig_gender = view.findViewById(R.id.ig_gender);
        ig_region = view.findViewById(R.id.ig_region);
        ig_brithday = view.findViewById(R.id.ig_brithday);
        titleLayout = view.findViewById(R.id.tl_title);

        //设置TitleLayout文本
        tv_forward = titleLayout.findViewById(R.id.tv_forward);
        tv_forward.setText("Edit");
        tv_title = titleLayout.findViewById(R.id.tv_title);
        tv_title.setText("Person Info");

        //使箭头不可见
        ig_name.invisible();
        ig_gender.invisible();
        ig_region.invisible();
        ig_brithday.invisible();


        //设置监听
        titleLayout.findViewById(R.id.tv_forward).setOnClickListener(this::onClick);
        ig_name.setOnClickListener(this::onClick);
        ig_brithday.setOnClickListener(this::onClick);

        //显示id
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


    public void onClick(View v){
        switch (v.getId()){
            //测试用点击事件
            case R.id.ig_name:

                //String s = ig_name.getText();
                //.out.println("ig_name中的文本是"+s);
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try{
                    Initial_Thread.run();
                }catch (Exception e){
                    e.printStackTrace();
                }
                //System.out.println("监听到了点击");
                break;
            //测试用点击事件
            case R.id.ig_brithday:
/*                StrictMode.ThreadPolicy policy1 = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy1);
                try{
                    Update_Thread.run();
                }catch (Exception e){
                    e.printStackTrace();
                }*/
                System.out.println("ig_birthday监听到了点击");
                break;
            //navigation跳转
            case R.id.tv_forward:
                System.out.println("person_info_test tv_forward 监听到了点击");
                Navigation.findNavController(v).navigate(R.id.action_navigation_person_info_to_navigation_person_info_edit);
                break;
            default:
                break;

        }
    }

    //新数据库读取方法
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

}
