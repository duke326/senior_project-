package com.example.findyourlove;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import androidx.navigation.fragment.NavHostFragment;

import com.example.findyourlove.R;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import static android.provider.MediaStore.EXTRA_OUTPUT;

public class person_info_test extends Fragment {
    private ItemGroup ig_id,ig_name,ig_gender,ig_region,ig_brithday;
    private int id = 1;
    private TitleLayout titleLayout;

    private PopupWindow popupWindow;


    private FragmentManager fmanager;
    private FragmentTransaction ftransaction;

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
        titleLayout = view.findViewById(R.id.tl_title);
        //ll_portrait = (LinearLayout)findViewById(R.id.ll_portrait);
        //ri_portrati = (RoundImageView)findViewById(R.id.ri_portrait);

        ig_id.getContentEdt().setText(String.valueOf(id));
        getName(id);
        getBirth(id);
        getRegion(id);
        getGender(id);
    }

    public void onClick(View v){
        switch (v.getId()){
        }
    }



    public void gotoDownloadFragment() {    //去下载页面
        fmanager = getSupportFragmentManager();
        ftransaction = fmanager.beginTransaction();
        mDownloadFragment = new DownloadFragment();
        ftransaction.replace(R.id.rl_fragment_container, mDownloadFragment);
        ftransaction.commit();
    }



/*    private void show_popup_windows(){
        RelativeLayout layout_photo_selected = (RelativeLayout) getLayoutInflater().inflate(R.layout.photo_select,null);
        if(popupWindow==null){
            popupWindow = new PopupWindow(layout_photo_selected, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        }
        //显示popupwindows
        popupWindow.showAtLocation(layout_photo_selected, Gravity.CENTER, 0, 0);
        //设置监听器
        TextView take_photo =  (TextView) layout_photo_selected.findViewById(R.id.take_photo);
        TextView from_albums = (TextView)  layout_photo_selected.findViewById(R.id.from_albums);
        LinearLayout cancel = (LinearLayout) layout_photo_selected.findViewById(R.id.cancel);
        //拍照按钮监听
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(popupWindow != null && popupWindow.isShowing()) {
                    imageUri = photoUtils.take_photo_util(PersonInfo.this, "com.foodsharetest.android.fileprovider", "output_image.jpg");
                    //调用相机，拍摄结果会存到imageUri也就是outputImage中
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra(EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, TAKE_PHOTO);
                    //去除选择框
                    popupWindow.dismiss();
                }
            }
        });
        //相册按钮监听
        from_albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //申请权限
                if(ContextCompat.checkSelfPermission(PersonInfo.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PersonInfo.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else {
                    //打开相册
                    Intent intent = new Intent("android.intent.action.GET_CONTENT");
                    intent.setType("image/*");
                    startActivityForResult(intent, FROM_ALBUMS);
                }
                //去除选择框
                popupWindow.dismiss();
            }
        });
        //取消按钮监听
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
    }*/

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
