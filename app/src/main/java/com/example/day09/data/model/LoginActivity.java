package com.example.day09.data.model;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.day09.data.api.ApiConfig;
import com.example.day09.data.api.ApiService;
import com.example.day09.data.model.login.Login;
import com.example.day09.data.model.login.LoginData;
import com.example.day09.R;
import com.example.day09.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private String Username;
    private String Password;
    private sessionMananger sessionmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvCreateAcc.setOnClickListener(v -> {
            if (v.getId() == R.id.tvCreateAcc){
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(v -> {
            Username = binding.etUsername.getText().toString();
            Password = binding.etPassword.getText().toString();
            login(Username, Password);
        });
    }

    public void login(String Username, String Password){
        ApiService apiService = ApiConfig.getConfig().create(ApiService.class);
        Call<Login> call = apiService.LoginResponse(Username,Password);
        call.enqueue(new Callback<Login>(){

            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isStatus()){

                    sessionmanager = new sessionMananger(LoginActivity.this);
                    LoginData loginData = response.body().getData();
                    sessionmanager.loginSession(loginData);

                    Snackbar.make(findViewById(android.R.id.content), "Berhasil Login", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Snackbar.make(findViewById(android.R.id.content), "Gagal Login : " + response.message(), Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), "Gagal Login : " + t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}