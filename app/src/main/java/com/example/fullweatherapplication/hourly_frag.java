package com.example.fullweatherapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class hourly_frag extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<String> temps = new ArrayList<>();
    private ArrayList<String> weather = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hourly_frag, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);

        weather.add("test1");
        temps.add("temp1");

        weather.add("test2");
        temps.add("temps2");

        weather.add("test4");
        temps.add("temps4");

        weather.add("test5");
        temps.add("temps5");

        weather.add("test6");
        temps.add("temps6");

        weather.add("test7");
        temps.add("temps7");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), weather, temps);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}