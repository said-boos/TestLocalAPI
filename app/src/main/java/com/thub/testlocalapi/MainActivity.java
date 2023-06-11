package com.thub.testlocalapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //EditTexts
    @BindView(R.id.email_log_et)
    EditText email_log_et;

    @BindView(R.id.psd_log_et)
    EditText psd_log_et;

    //Variables
    public static String emailV;
    private String psdV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        email_log_et.setText("muna@gmail.com");
        psd_log_et.setText("test@123");
    }

    private boolean validateLogin() {
        emailV = email_log_et.getText().toString();
        psdV = psd_log_et.getText().toString();

        if (emailV.isEmpty()) {
            email_log_et.setError("Enter your email");
            email_log_et.requestFocus();
            return false;
        }

        if (psdV.isEmpty()) {
            psd_log_et.setError("Enter password");
            psd_log_et.requestFocus();
            return false;
        }

        return true;
    }


    @OnClick(R.id.login_btn)
    void loginApp() {
        if (validateLogin()) {
            appLogin();
        }
    }

    private void appLogin() {
        CommonRequest request = new CommonRequest();
        request.setEmail(emailV);
        request.setPassword(psdV);

        Call<CommonResponse> call = ServiceGenerator.getInstance().getApi().login(request);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                LoginResponse loginResponse = response.body().getLoginResponse();
                showToast(loginResponse.getFirstName()+" "+loginResponse.getMiddleName()+" "+loginResponse.getLastName());
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                showToast(t.getMessage());
            }
        });
    }


    private void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}