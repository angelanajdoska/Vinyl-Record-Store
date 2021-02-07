package com.example.vinylrecordstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinylrecordstore.models.Products;

import java.util.List;

public class CartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView mRecyclerView;
    CartAdapter mAdapter;
    List<Products> productsList;
    SQLiteDatabase sql;
    Button button;
    TextView textView;
    MyDatabase myDatabase;
    Double price = 0.0;
    Products product;
    AlertDialog.Builder builder;

    public CartFragment() {
        // Required empty public constructor
    }


    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        builder = new AlertDialog.Builder(getContext());

        productsList = getAllProducts();
        textView = (TextView) view.findViewById(R.id.total);
        button = (Button) view.findViewById(R.id.order);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.cart);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new CartAdapter(getActivity(), R.layout.cart_row, productsList, button, textView, price);
        mRecyclerView.setAdapter(mAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productsList.isEmpty()){
                    Toast.makeText(v.getContext(), "Your cart is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                builder.setMessage(R.string.dialog)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MyDatabase myDatabase = new MyDatabase(v.getContext());
                                myDatabase.deleteAll();
                                myDatabase.close();
                                mAdapter.notifyDataSetChanged();
                                Toast.makeText(v.getContext(), "You have successfully placed your order", Toast.LENGTH_LONG).show();
                                productsList.clear();
                                mAdapter.getPrice();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                }
            }
        });
       mAdapter.getPrice();
        return view;
    }
    private List<Products> getAllProducts() {
        MyDatabase handler = new MyDatabase(getContext());
        return handler.getProducts();
    }
}