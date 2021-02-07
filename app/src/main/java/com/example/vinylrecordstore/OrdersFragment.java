package com.example.vinylrecordstore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinylrecordstore.models.Orders;
import com.example.vinylrecordstore.models.Products;
import com.example.vinylrecordstore.models.User;

import java.util.List;

public class OrdersFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView mRecyclerView;
    OrdersAdapter mAdapter;
    List<Orders> ordersList;
    List<User> userList;

    public OrdersFragment() {
        // Required empty public constructor
    }


    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        ordersList = getAllOrders();
        userList = getUser();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.cart);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new OrdersAdapter(getActivity(), R.layout.orders_row, ordersList, userList);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
    private List<Orders> getAllOrders() {
        MyDatabase handler = new MyDatabase(getContext());
        return handler.getOrders();
    }
    private List<User> getUser() {
        MyDatabase handler = new MyDatabase(getContext());
        return handler.getUser();
    }

}