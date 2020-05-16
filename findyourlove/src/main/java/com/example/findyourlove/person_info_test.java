package com.example.findyourlove;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import java.sql.SQLException;
import androidx.fragment.app.Fragment;

public class person_info_test extends Fragment {
    private ItemGroup ig_id,ig_name,ig_gender,ig_region,ig_brithday;
    private int id = Integer.parseInt(loginactivity.accid);
    private TextView tv_forward;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.activity_person_info_basic, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ig_id = view.findViewById(R.id.ig_id);
        ig_name = view.findViewById(R.id.ig_name);
        ig_gender = view.findViewById(R.id.ig_gender);
        ig_region = view.findViewById(R.id.ig_region);
        ig_brithday = view.findViewById(R.id.ig_brithday);
        tv_forward = view.findViewById(R.id.edit_save);

        //使箭头不可见
        ig_name.invisible();
        ig_gender.invisible();
        ig_region.invisible();
        ig_brithday.invisible();


        //设置监听
        //titleLayout.findViewById(R.id.tv_forward).setOnClickListener(this::onClick);
        tv_forward.setOnClickListener(this::onClick);
        ig_name.setOnClickListener(this::onClick);
        ig_brithday.setOnClickListener(this::onClick);

        //显示id 空格手动调整对齐
        ig_id.getContentEdt().setText(String.valueOf(id) + "               ");


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
            //navigation跳转
            case R.id.edit_save:
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
