package com.ue.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ue.login.util.UserInfoUtil;

import java.util.Map;


public class MainActivity extends Activity implements OnClickListener{

    //便于login方法调用
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_rem;
    private Button bt_login;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_rem = (CheckBox) findViewById(R.id.cb_rem);
        bt_login = (Button) findViewById(R.id.bt_login);
        //b.设置按钮的点击事件
        bt_login.setOnClickListener(this);

        //f.回显用户名密码
        Map<String, String> map = UserInfoUtil.getUserInfo_android(mContext);//获取用户名密码
        if(map != null){
            String username = map.get("username");
            String password = map.get("password");
            et_username.setText(username);//设置用户名
            et_password.setText(password);
            cb_rem.setChecked(true);//设置复选框选中状态
        }

    }

    private void login(){
        //c.在onclick方法中，获取用户输入的用户名密码和是否记住密码
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        boolean isrem = cb_rem.isChecked();
        //d.判断用户名密码是否为空，不为空请求服务器（省略，默认请求成功）
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(mContext, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return ;
        }

        //请求服务器，后面讲。。。。。。。。。。

        //e.判断是否记住密码，如果记住，将用户名密码保存本地
        if(isrem){
            boolean result = UserInfoUtil.saveUserInfo_android(mContext,username,password);
            if(result){
                Toast.makeText(mContext, "用户名密码保存成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContext, "用户名密码保存失败", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(mContext, "无需保存", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                login();
                break;

            default:
                break;
        }
    }


}
