package com.example.vinylrecordstore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView mRecyclerView;
    ProductsAdapter mAdapter;

    int image_id[] ={R.drawable.harry, R.drawable.damienrice, R.drawable.gipsykings, R.drawable.ledzeppelin, R.drawable.queen, R.drawable.rollingstones, R.drawable.thebeatles};
    List<String> artist = Arrays.asList("Harry Styles", "Damien Rice", "Gipsy Kings", "Led Zeppelin", "Queen", "The Rolling Stones", "The Beatles" );
    List<String> album = Arrays.asList("Fine Line", "9", "Gipsy Kings", "Physical Graffiti", "The Game", "Bridges to Babylon", "Let It Be" );
    List<String> price = Arrays.asList("23.51", "18.99", "29.99", "31.20", "21.49", "33.49", "20.71" );
    //List<Object> songs = Arrays.asList(R.raw.Falling, R.raw.CoconutSkins, R.raw.Bamboléo, R.raw.Kashmir, R.raw.AnotherOneBitesTheDust, R.raw.AnybodySeenMyBaby, R.raw.LetItBe);


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.products);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ProductsAdapter(getActivity(), image_id, artist, album, price, R.layout.products_row);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }
}