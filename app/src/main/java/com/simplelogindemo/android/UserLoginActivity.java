package com.simplelogindemo.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.simplelogindemo.android.bean.User;
import com.simplelogindemo.android.presenter.UserLoginPresenter;
import com.simplelogindemo.android.view.IUserLoginView;

public class UserLoginActivity extends AppCompatActivity implements IUserLoginView {

    private EditText edit_userName;

    private EditText edit_password;

    private Button login;

    private Button clear;

    private ProgressBar mPbLoading;

    private UserLoginPresenter userLoginPresenter = new UserLoginPresenter(UserLoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Log.i("UserLoginActivity", "onCreate: " + Thread.currentThread().getId());
        initViews();
    }

    private void initViews() {
        edit_userName = (EditText) findViewById(R.id.edit_userName);
        edit_password = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.login);
        clear = (Button) findViewById(R.id.clear);
        mPbLoading = (ProgressBar) findViewById(R.id.pb_loading);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLoginPresenter.login();
//                Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
//                intent.putExtra("userName", getUserName());
//                startActivity(intent);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return edit_userName.getText().toString();
    }

    @Override
    public String getPassword() {
        return edit_password.getText().toString();
    }

    @Override
    public void clearUserName() {
        edit_userName.setText("");
    }

    @Override
    public void clearPassword() {
        edit_password.setText("");
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
        intent.putExtra("userName", getUserName());
        startActivity(intent);
        Toast.makeText(this, user.getUserName() +
                "login success, to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
    }
}