package com.example.a20190327_httpconn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserRecyclerView extends RecyclerView.Adapter<UserRecyclerView.UserViewHolder> {

    private List<User> mUserList;
    private LayoutInflater layoutInflater;




    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView password;
        private TextView contact;
        private TextView country;

        public UserViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.uname);
            password = (TextView) view.findViewById(R.id.upassword);
            contact = (TextView) view.findViewById(R.id.ucontact);
            country = (TextView) view.findViewById(R.id.ucountry);
        }
    }

    UserRecyclerView(Context context, List<User> mUserList){
        this.layoutInflater = LayoutInflater.from(context);
        this.mUserList = mUserList;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.user_recycler_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder viewHolder, int position) {
        User user = mUserList.get(position);
        viewHolder.name.setText(user.getmName());
        viewHolder.password.setText(user.getmPassword());
        viewHolder.contact.setText(user.getmContact());
        viewHolder.country.setText(user.getmCountry());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
