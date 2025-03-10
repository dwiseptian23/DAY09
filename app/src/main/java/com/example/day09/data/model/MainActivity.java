package com.example.day09.data.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.day09.R;

public class MainActivity extends AppCompatActivity {

    sessionMananger sessionmananger ;
    TextView tvname, tvusername;
    String username,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvusername = findViewById(R.id.tvusername);
        tvname = findViewById(R.id.tvnama);

        sessionmananger = new sessionMananger(this);
        if (!sessionmananger.isLogIng()){
            goLogin();
        }

        username = sessionmananger.getUser().get(sessionMananger.USERNAME);
        name = sessionmananger.getUser().get(sessionMananger.NAME);

        tvusername.setText(username);
        tvname.setText(name);
    }

    private void goLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout){
            sessionmananger.logout();
            goLogin();
        }
        return super.onOptionsItemSelected(item);
    }
}