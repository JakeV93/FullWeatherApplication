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

    public RecyclerViewAdapter(ArrayList<String> temps, ArrayList<String> weather, Context context) {
        this.temps = temps;
        this.weather = weather;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hourly_frag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.temp.setText(temps.get(position));
        holder.weather.setText(weather.get(position));
    }

    @Override
    public int getItemCount() {
        return temps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView temp;
        TextView weather;

        public ViewHolder(View itemView) {
            super(itemView);

            temp = itemView.findViewById(R.id.hrly_temp);
            weather = itemView.findViewById(R.id.hrly_weather);
        }
    }
}
