package com.example.vinylrecordstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinylrecordstore.models.Orders;
import com.example.vinylrecordstore.models.Products;
import com.example.vinylrecordstore.models.User;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private Context mContext;
    private int rowLayout;
    List<Orders> ordersList;
    List<User> userList;

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_row, parent, false);
        return new OrdersAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
        Orders entry = ordersList.get(position);
        User us = userList.get(0);
        holder.album.setText(entry.getAlbum());
        holder.price.setText(entry.getPrice());
        holder.name.setText(us.getFullName());
        holder.address.setText(us.getAddress());
        holder.city.setText(us.getCity());
        holder.img.setImageResource(entry.getImage());
    }

    @Override
    public int getItemCount() {
        return ordersList == null ? 0 : ordersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView album, price, dollar, name, address, city;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            album = (TextView) itemView.findViewById(R.id.album);
            dollar = (TextView) itemView.findViewById(R.id.dollar);
            price = (TextView) itemView.findViewById(R.id.price);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            city = (TextView) itemView.findViewById(R.id.city);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
    public OrdersAdapter(Context context, int rowLayout, List<Orders> ordersList, List<User> userList) {
        this.mContext = context;
        this.rowLayout = rowLayout;
        this.ordersList = ordersList;
        this.userList = userList;
    }
}
