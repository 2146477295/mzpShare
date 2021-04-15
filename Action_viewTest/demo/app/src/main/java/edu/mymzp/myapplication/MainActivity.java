package edu.mymzp.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.app.ComponentActivity;
import androidx.core.view.MenuItemCompat;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button contextFloatBtn;
    private Button contextActionBtn;
    private ActionMode actionMode;
    private Button popupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取控件
        contextFloatBtn = findViewById(R.id.main_btn_contextFloatBtn);
        contextActionBtn = findViewById(R.id.main_btn_contextActionBtn);
        popupBtn = findViewById(R.id.main_btn_popupMenu);


        // 通过给按钮设置点击事件进行弹出菜单的设置和绑定
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_hello:
                                Toast.makeText(MainActivity.this, "HELLO", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_world:
                                Toast.makeText(MainActivity.this, "WORLD", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        // 注册上下文菜单(浮动模式)
        registerForContextMenu(contextFloatBtn);

        // 设置上下文菜单(动作模式)
        contextActionBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                actionMode = null;
                actionMode = startActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        mode.getMenuInflater().inflate(R.menu.main_context_float, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_year:
                                Toast.makeText(MainActivity.this, "YEAR", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_no:
                                Toast.makeText(MainActivity.this, "NO", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_OK:
                                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_IS:
                                Toast.makeText(MainActivity.this, "IS", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_MY:
                                Toast.makeText(MainActivity.this, "MY", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_HE:
                                Toast.makeText(MainActivity.this, "HE", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {

                    }
                });
                return true;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main_context_float, menu);

//        menu.setGroupVisible(R.id.group_one, false);

//        menu.setGroupCheckable(R.id.group_tow, false, true);

//        menu.setGroupEnabled(R.id.group_tow, false);

        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_year:
                Toast.makeText(this, "YEAR", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_no:
                Toast.makeText(this, "NO", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_OK:
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_IS:
                Toast.makeText(this, "IS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_MY:
                Toast.makeText(this, "MY", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_HE:
                Toast.makeText(this, "HE", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mai, menu);

        MenuItem item = menu.findItem(R.id.action_search);

        MenuItem shareItem = menu.findItem(R.id.action_share);

        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);

        SearchView actionView = (SearchView) item.getActionView();

        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this, "展开", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        actionView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "提交文本: "+  query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, "文本发生改变: " + newText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        actionView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(MainActivity.this, "正在聚焦", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "正在失焦", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Intent intent = new Intent();
        intent.setType("img/*");

        shareActionProvider.setShareIntent(intent);


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_normal:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "我是好人");
                intent.putExtra(Intent.EXTRA_TEXT, "你是坏人");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, "成龙"));
                startActivity(Intent.createChooser(intent, "成龙"));
                break;
            case R.id.action_settings:
                Toast.makeText(this, "settings.....", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                Toast.makeText(this, "search....", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterForContextMenu(contextFloatBtn);
    }
}