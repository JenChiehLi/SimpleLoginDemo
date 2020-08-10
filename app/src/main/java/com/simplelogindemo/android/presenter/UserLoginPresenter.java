package com.simplelogindemo.android.presenter;

import android.os.Handler;

import com.simplelogindemo.android.bean.User;
import com.simplelogindemo.android.biz.IUserBiz;
import com.simplelogindemo.android.biz.OnLoginListener;
import com.simplelogindemo.android.biz.UserBiz;
import com.simplelogindemo.android.view.IUserLoginView;

public class UserLoginPresenter {

    private IUserBiz userBiz;

    private IUserLoginView muserLoginView;

    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        muserLoginView = userLoginView;
        userBiz = new UserBiz();
    }

    public void login() {
        muserLoginView.showLoading();
        userBiz.login(muserLoginView.getUserName(), muserLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
//                UserLoginActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        muserLoginView.toMainActivity(user);
//                        muserLoginView.hideLoading();
//                    }
//                });
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        muserLoginView.toMainActivity(user);
                        muserLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        muserLoginView.showFailedError();
                        muserLoginView.hideLoading();
                    }
                });
//                UserLoginActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        muserLoginView.showFailedError();
//                        muserLoginView.hideLoading();
//                    }
//                });
            }
        });

    }

    public void clear() {
        muserLoginView.clearUserName();
        muserLoginView.clearPassword();
    }
}
