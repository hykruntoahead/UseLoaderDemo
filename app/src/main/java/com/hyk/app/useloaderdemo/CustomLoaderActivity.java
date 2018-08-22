package com.hyk.app.useloaderdemo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomLoaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<UserBean>> {

    private List<UserBean> users = new ArrayList<>();
    private ListView mListView;
    private UserAdapter adapter;
    private LoaderManager loaderManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_loader);
        mListView = findViewById(R.id.custom_list_view);
        adapter = new UserAdapter();
        mListView.setAdapter(adapter);

        loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<List<UserBean>> onCreateLoader(int id, @Nullable Bundle args) {

        return new UserLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<UserBean>> loader, List<UserBean> data) {
        users.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }


    class UserAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder ;
            if (view == null) {
                view = LayoutInflater.from(CustomLoaderActivity.this).inflate(R.layout.use_item, parent, false);
                holder = new ViewHolder();
                holder.usernameTv = view.findViewById(R.id.username_tv);
                holder.passwordTv = view.findViewById(R.id.password_tv);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            UserBean userBean = users.get(position);
            holder.passwordTv.setText(userBean.getPassword());
            holder.usernameTv.setText(userBean.getUsername());
            return view;
        }


        class ViewHolder {
            TextView usernameTv;
            TextView passwordTv;
        }

    }
}
