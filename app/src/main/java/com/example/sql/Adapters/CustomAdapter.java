package com.example.sql.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sql.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList user_email, user_password;

    public CustomAdapter(Context context, ArrayList user_email, ArrayList user_password) {
        this.context = context;
        this.user_email = user_email;
        this.user_password = user_password;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.user_email_text.setText(String.valueOf(user_email.get(position)));
        holder.user_password_text.setText(String.valueOf(user_password.get(position)));

    }



    @Override
    public int getItemCount() {
        return user_email.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_email_text, user_password_text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_email_text = itemView.findViewById(R.id.user_email_text);
            user_password_text = itemView.findViewById(R.id.user_password_text);

        }


    }
}
