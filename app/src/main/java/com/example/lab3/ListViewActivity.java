package com.example.lab3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {
    // 数据源
    private final List<Map<String, Object>> dataList = new ArrayList<>();
    // 控件
    private final int[] viewIds = {R.id.text, R.id.icon};
    // 键名映射
    private final String[] keys = {"text", "icon"};
    // 渠道ID
    private static final String CHANNEL_ID = "channel_1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);
        ListView listView = findViewById(R.id.list);

        // 创建通知渠道
        createNotificationChannel();

        // 添加列表所需元素
        addItem("Lion", R.drawable.lion);
        addItem("Tiger", R.drawable.tiger);
        addItem("Monkey", R.drawable.monkey);
        addItem("Cat", R.drawable.cat);
        addItem("Dog", R.drawable.dog);
        addItem("Elephant", R.drawable.elephant);

        SimpleAdapter simpleAdapter
                = new SimpleAdapter(this, dataList, R.layout.list_view, keys, viewIds);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            // 获取点击的项的text
            String text = (String)dataList.get(position).get("text");

            // 自定义Toast
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast, null, false);

            // 设置text
            TextView textView = layout.findViewById(R.id.text);
            textView.setText(text);
            textView.setGravity(Gravity.CENTER);

            // 创建Toast
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            // 发送通知
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info) // 这里使用的系统内置图标
                    .setContentTitle(text)
                    .setContentText("啊啊啊啊啊")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true); // 点击后消失
            notificationManager.notify(1, notification.build());
        });
    }

    private void addItem(String text, int icon) {
        Map<String, Object> item = new HashMap<>();
        item.put("text", text);
        item.put("icon", icon);
        dataList.add(item);
    }

    // 创建渠道
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 渠道名称及描述
            CharSequence name = "1";
            String description = "2";

            // 渠道重要性
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            // 创建渠道
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // 注册渠道，添加管理
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}