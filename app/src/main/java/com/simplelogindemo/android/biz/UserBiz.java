package com.simplelogindemo.android.biz;

import android.util.Log;

import com.simplelogindemo.android.bean.User;

public class UserBiz implements IUserBiz {
    @Override
    public void login(final String userName, final String password, final OnLoginListener loginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 模拟登录成功
                if ("JenChiehLi".equals(userName) && "123456".equals(password)) {
                    User user = new User();
                    user.setUserName(userName);
                    user.setPassword(password);
                    loginListener.loginSuccess(user);
                } else {
                    loginListener.loginFailed();
                }
            }
        }).start();
    }
}
