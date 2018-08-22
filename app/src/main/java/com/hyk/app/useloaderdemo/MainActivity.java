package com.hyk.app.useloaderdemo;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.net.URI;

/**
 * 1.获取LoaderManager对象
 * 2.用LoaderManager 来初始化Loader
 * 3.实现loaderCallbacks接口
 * 4.在onCreateLoader 方法中返回CursorLoader,
 * 在onLoadFinished方法中获得加载的数据
 */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private LoaderManager loaderManager;

    private ListView mContactsListView;
    private SimpleCursorAdapter mCursorAdapter;
    private EditText mEditText;
    private String filterName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContactsListView = findViewById(R.id.contacts_list_view);
        //设置游标适配器
        mCursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                null,
                new String[]{ContactsContract.Contacts.DISPLAY_NAME},
                new int[]{android.R.id.text1},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mContactsListView.setAdapter(mCursorAdapter);

        mEditText = findViewById(R.id.contacts_name_et);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterName = s.toString();
                //重新创建Loader
                loaderManager.restartLoader(0, null, MainActivity.this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        loaderManager = getSupportLoaderManager();
        //初始化Loader，参数1 Loader的ID，参数2 给Loader传递的参数Bundle,
        // 参数3 LoaderCallBacks接口
        loaderManager.initLoader(0, null, this);


    }


    //创建Loader对象
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        if (!TextUtils.isEmpty(filterName)){
            uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI,
                    Uri.encode(filterName));
        }
        CursorLoader cursorLoader =
                new CursorLoader(this, uri,
                        null, null, null, null);
        return cursorLoader;
    }

    /**
     * 加载数据完成
     *
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        //在主线程完成的，更新UI
        Cursor oldCursor = mCursorAdapter.swapCursor(data);
        if (oldCursor != null) {
            oldCursor.close();
        }

    }


    /**
     * 重置loader
     *
     * @param loader
     */

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        //释放当前游标
        Cursor oldCursor = mCursorAdapter.swapCursor(null);
        if (oldCursor != null) {
            oldCursor.close();
        }

    }
}
