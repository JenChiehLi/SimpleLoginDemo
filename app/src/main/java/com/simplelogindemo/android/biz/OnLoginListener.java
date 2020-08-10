package com.simplelogindemo.android.biz;

import com.simplelogindemo.android.bean.User;

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginFailed();
}
