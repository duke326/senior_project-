package com.example.findyourlove;

import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class person_info_edit extends Fragment {
    private ItemGroup ig_id,ig_name,ig_gender,ig_region,ig_brithday;
    private int id =Integer.parseInt(loginactivity.accid);
    private TitleLayout titleLayout;
    private TextView tv_forward,tv_title;

    //选择器
    private OptionsPickerView pvOptions;
    //性别选择器
    private ArrayList<String> optionsItems_gender = new ArrayList<>();
    //日期选择器
    private TimePickerView pvTime;



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

        ig_id = view.findViewById(R.id.ig_id);
        ig_name = view.findViewById(R.id.ig_name);
        ig_gender = view.findViewById(R.id.ig_gender);
        ig_region = view.findViewById(R.id.ig_region);
        ig_brithday = view.findViewById(R.id.ig_brithday);
        titleLayout = view.findViewById(R.id.tl_title);

        //使EditTextView可以编辑
        ig_name.editable();
        //ig_brithday.editable();
        ig_region.editable();
        //ig_gender.editable();

        //为性别选择器添加数据
        optionsItems_gender.add(new String("Unknown"));
        optionsItems_gender.add(new String("Male"));
        optionsItems_gender.add(new String("Female"));

        //设置TitleLayoOut文本
        tv_forward = titleLayout.findViewById(R.id.tv_forward);
        tv_forward.setText("Save");
        tv_title = titleLayout.findViewById(R.id.tv_title);
        tv_title.setText("Edit Person Info");


        //设置监听
        ig_id.setOnClickListener(this::onClick);
        titleLayout.findViewById(R.id.tv_forward).setOnClickListener(this::onClick);
        ig_brithday.setOnClickListener(this::onClick);
        ig_gender.setOnClickListener(this::onClick);

        //根据全局变量设置id
        ig_id.getContentEdt().setText(String.valueOf(id));

    }

    public void onClick(View v){
        switch (v.getId()){
            //测试用点击事件
            case R.id.ig_id:

                break;

            //Navigation 跳转
            case R.id.tv_forward:
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try{
                    Update_Thread.run();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Navigation.findNavController(v).navigate(R.id.action_navigation_person_info_edit_to_navigation_person_info);
                break;

            //点击后调出日期选择器
            case R.id.ig_brithday:
                TimePickerView pvTime = new TimePickerBuilder(this.getActivity(), new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date,View v) {//选中事件回调
                       // tvTime.setText(getTime(date));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        ig_brithday.getContentEdt().setText(sdf.format(date));
                    }
                }).build();
                pvTime.show();
                break;

            //性别选择器
            case R.id.ig_gender:
                pvOptions = new OptionsPickerBuilder(this.getActivity(), new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        String tx = optionsItems_gender.get(options1);
                        ig_gender.getContentEdt().setText(tx);
                    }
                }).setCancelColor(Color.GRAY).build();
                pvOptions.setPicker(optionsItems_gender);
                pvOptions.show();
                break;

            default:
                break;

        }
    }

    //数据库UPDATE方法
    Thread Update_Thread = new Thread(new Runnable(){
        public void run() {
            try {
                //System.out.println("Update Thread tends to run");
               // System.out.println("读取的数据为 name: " + ig_name.getText() + " region: " + ig_region.getText() + " birth: "+ig_brithday.getText() + " gender: "+ig_gender.getText());
                user_db.Update2(id,ig_name.getText(),ig_region.getText(),ig_brithday.getText(),ig_gender.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    });

}
