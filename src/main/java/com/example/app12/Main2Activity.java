package com.example.app12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private DragViewGroup2 dragViewGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dragViewGroup2 = findViewById(R.id.dragViewGroup2);
    }

    public void close(View view) {
        Toast.makeText(Main2Activity.this, "应该关闭", Toast.LENGTH_SHORT).show();
        dragViewGroup2.closeDrawer();
    }
}
