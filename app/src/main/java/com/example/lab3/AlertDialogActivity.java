package com.example.lab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AlertDialogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog_button);

        findViewById(R.id.alert_dialog_button).setOnClickListener(v -> showAlertDialog());
    }

    private void showAlertDialog() {
        // 加载布局
        LayoutInflater inflater = LayoutInflater.from(this);
        View alertDialogView = inflater.inflate(R.layout.alert_dialog, null);

        // 获取布局控件
        EditText etUsername = alertDialogView.findViewById(R.id.et_username); // 没用上
        EditText etPassword = alertDialogView.findViewById(R.id.et_password); // 没用上
        Button btnCancel = alertDialogView.findViewById(R.id.btn_cancel);
        Button btnSignIn = alertDialogView.findViewById(R.id.btn_sign_in); // 没用上

        // 构建AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(alertDialogView);
        AlertDialog alertDialog = builder.create();

        // Cancel关闭对话框
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        // 显示对话框
        alertDialog.show();
    }
}
