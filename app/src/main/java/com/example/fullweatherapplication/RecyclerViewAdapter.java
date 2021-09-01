package com.example.fullweatherapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> temps = new ArrayList<>();
    private ArrayList<String> weather = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> temps, ArrayList<String> weather) {
        this.temps = temps;
        this.weather = weather;
        this.context = context;
    }

    public RecyclerViewAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.texttemp.setText(temps.get(position));
        viewHolder.textweat.setText(weather.get(position));
    }

    @Override
    public int getItemCount() {

        return temps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView texttemp;
        private TextView textweat;


        public ViewHolder(View itemView) {
            super(itemView);
            texttemp = (TextView)itemView.findViewById(R.id.hrly_temp);
            textweat = (TextView)itemView.findViewById(R.id.hrly_weather);

        }
    }
}
