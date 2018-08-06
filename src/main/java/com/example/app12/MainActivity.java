package com.example.app12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout testOnC = findViewById(R.id.testOnC);
        LinearLayout testOnC1 = findViewById(R.id.testOnC1);
        LinearLayout testOnC2 = findViewById(R.id.testOnC2);
        testOnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "c", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
        //在这里只有在setOnTouchListener()中返回的值为true才能屏蔽点击，如过是setEnable(false)和setClickable（false）并不生效，
        //这是应为点击的是ViewGroup的缘故，好像并没有消费事件，并没有消费的对象，如果是换成Button 那么就不需要这些操作。
//       testOnC1.setOnTouchListener(new View.OnTouchListener() {
//           @Override
//           public boolean onTouch(View view, MotionEvent motionEvent) {
//               return true;
//           }
//       });
//        testOnC2.setOnTouchListener(new View.OnTouchListener() {
//           @Override
//           public boolean onTouch(View view, MotionEvent motionEvent) {
//               return true;
//           }
//       });
//        testOnC1.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               Toast.makeText(MainActivity.this, "c1", Toast.LENGTH_SHORT).show();
//           }
//       });
//        testOnC2.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               Toast.makeText(MainActivity.this, "c2", Toast.LENGTH_SHORT).show();
//           }
//       });
    }

}
