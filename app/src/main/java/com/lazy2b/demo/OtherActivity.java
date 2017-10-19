package com.lazy2b.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity {
    int position = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        position = getIntent().getIntExtra("position", position);
    }

    public void uState(View view) {
        Toast.makeText(this, "position->" + position, Toast.LENGTH_SHORT).show();
        MainActivity.inst.uState(position);
    }

}
