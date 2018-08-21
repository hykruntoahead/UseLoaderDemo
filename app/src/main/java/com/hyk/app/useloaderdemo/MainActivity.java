package com.hyk.app.useloaderdemo;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 1.获取LoaderManager对象
 * 2.用LoaderManager 来初始化Loader
 * 3.实现loaderCallbacks接口
 * 4.在onCreateLoader 方法中返回CursorLoader,在onLoadFinished方法中获得加载的数据
 */

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaderManager = getSupportLoaderManager();
        //初始化Loader，参数1 Loader的ID，参数2 给Loader传递的参数Bundle,
        // 参数3 LoaderCallBacks接口
        loaderManager.initLoader(0,null,this);


    }


    //创建Loader对象
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    /**
     * 加载数据完成
     * @param loader
     * @param data
     */
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor>loader, Cursor data) {

    }


    /**
     * 重置loader
     * @param loader
     */

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
