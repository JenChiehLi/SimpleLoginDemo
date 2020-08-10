package com.simplelogindemo.android.biz;

import android.content.Context;

public interface IUserBiz {
    public void login (String userName, String password, OnLoginListener loginListener);
}
