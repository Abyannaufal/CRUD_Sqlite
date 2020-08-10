package com.example.crud_a11201710791;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void first_move(View view) {
        Intent intent = new Intent(MainActivity.this, ShowListview.class);
        startActivity(intent);
    }
}
