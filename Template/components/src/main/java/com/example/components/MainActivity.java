package com.example.components;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_ToSec)
    Button btToSec;

    private  static  final String TAG = "bai";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "MainActivity  onCreate: ");
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "MainActivity  onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "MainActivity  onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "MainActivity  onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "MainActivity  onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "MainActivity  onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "MainActivity  onRestart: ");
    }

    @OnClick(R.id.bt_ToSec)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
