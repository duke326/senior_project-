package com.example.findyourlove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;

public class loginactivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       Button loginButton=findViewById(R.id.buttonlogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {doLogin();
            }
        });

    }
    public void doLogin() {
        EditText loginemail=findViewById(R.id.loginemail);
        EditText loginpsw= findViewById(R.id.loginpsw);
        LoginInfo info = new LoginInfo(loginemail.getText().toString(),loginpsw.getText().toString()); // config...
        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo param) {
                        NimUIKitImpl.setAccount(param.getAccount());
                        NimUIKit.startP2PSession(getApplicationContext(), "test");
                        finish();
                    }

                    @Override
                    public void onFailed(int code) {
                    startActivity(new Intent(getApplicationContext(), signup.class));
                    finish();
                    }

                    @Override
                    public void onException(Throwable exception) {

                    }
                    // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                };
        NIMClient.getService(AuthService.class).login(info)
                .setCallback(callback);
    }
}
