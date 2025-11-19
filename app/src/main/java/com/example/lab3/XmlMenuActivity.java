package com.example.lab3;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class XmlMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 调用菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // 菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.font_size_ten) {
            changeFontSize(10);
        } else if (id == R.id.font_size_sixteen) {
            changeFontSize(16);
        } else if (id == R.id.font_size_twenty) {
            changeFontSize(20);
        } else if (id == R.id.xml_menu_item2) {
            Toast.makeText(this, "这是一个普通菜单项", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.font_color_red) {
            changeFontColor(Color.RED);
        } else if (id == R.id.font_color_black) {
            changeFontColor(Color.BLACK);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    // 更改Hello World字体大小
    public void changeFontSize(int size) {
        TextView textView = findViewById(R.id.text_view);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    // 更改Hello World字体颜色
    public void changeFontColor(int color) {
        TextView textView = findViewById(R.id.text_view);
        textView.setTextColor(color);
    }
}
