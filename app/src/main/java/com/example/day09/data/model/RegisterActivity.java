package com.example.day09.data.model;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.day09.data.api.ApiConfig;
import com.example.day09.data.api.ApiService;
import com.example.day09.data.model.register.Register;
import com.example.day09.databinding.ActivityRegisterBinding;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


        binding.btnRegister.setOnClickListener(v -> {
            String Username = binding.etUsername.getText().toString().trim();
            String Name = binding.etName.getText().toString().trim();
            String Password = binding.etPassword.getText().toString().trim();
            register(Username,Name,Password);
        });

    }

    private void register(String username, String name, String password){
        ApiService apiService = ApiConfig.getConfig().create(ApiService.class);
        Call<Register> call = apiService.RegisterResponse(username, name, password);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.isSuccessful() && response.body() != null){
//                    Log.d("tes", response.toString());
                    Snackbar.make(findViewById(android.R.id.content), "Akun berhasil dibuat", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Snackbar.make(findViewById(android.R.id.content), response.body().toString(), Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), "Gagal Register : " + t.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

}