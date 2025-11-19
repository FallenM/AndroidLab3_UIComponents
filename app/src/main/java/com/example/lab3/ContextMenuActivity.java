package com.example.lab3;

import android.os.Bundle;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ContextMenuActivity extends AppCompatActivity {
    // 数据源
    private List<String> dataList = new ArrayList<>();

    // ActionMode实例
    private ActionMode actionMode;

    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.context_menu);
        listView = findViewById(R.id.context_menu_list);

        for (int i = 1; i < 11; i++) {
            dataList.add("选我 " + i);
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_activated_1, // 选中项高亮
                dataList);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            // 创建ActionMode
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                actionMode = mode;

                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);

                mode.setTitle("0 selected");
                return true;
            }

            // 统计选中项数
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                int count = listView.getCheckedItemCount();
                mode.setTitle(count + " selected");
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {return false;}

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    int selectedCount = listView.getCheckedItemCount();

                    for (int i = listView.getCount() - 1; i >= 0; i--) { // 倒序避免下标错乱
                        if (listView.isItemChecked(i)) {
                            dataList.remove(i);
                        }
                    }

                    adapter.notifyDataSetChanged(); // 刷新
                    mode.finish();
                    Toast.makeText(ContextMenuActivity.this, "删除了 " + selectedCount + " 项", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                if (actionMode != null) {
                    actionMode.finish();
                }
            }
        });
    }
}
