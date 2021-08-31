package com.example.fullweatherapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

public class FrontPage extends AppCompatActivity {
    private TextView cityV, tempV, weatherV;
    private RelativeLayout choice;
    private Button logout;
    private ImageButton menubut;
    private Button today, svnday, ftnday;
    private FirebaseAuth mAuth;
    private View forecast_daily;
    private View forecast_hourly;
    private View page_front;

    private hourly_frag hourlyFrag;
    private weekly_frag weeklyFrag;
    private biweekly_frag biweeklyFrag;

    private TabLayout tabLayout;

    private final String url = "api.openweathermap.org/data/2.5/weather";
    private final String appid = "5e2876a1ab98ffba46622cd4c5cf2d00";
    DecimalFormat df = new DecimalFormat("#0.#");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_front);

        logout = findViewById(R.id.signout);
        menubut = findViewById(R.id.menu_button);

        tabLayout = findViewById(R.id.tab_bar);

        cityV = findViewById(R.id.city_name);
        tempV = findViewById(R.id.current_temp);
        weatherV = findViewById(R.id.current_weather);

        page_front = findViewById(R.id.weatherbg);


        mAuth = mAuth.getInstance();

        new retrieveWeather().execute();

        menubut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), menubut);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent i;
                        switch (item.getItemId()) {
                            case R.id.signout:
                                mAuth.signOut();
                                i = new Intent(FrontPage.this, MainActivity.class);
                                startActivity(i);
                                return true;
                        }


                        return false;

                    }
                });popupMenu.show();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = new Fragment();
                switch(tab.getPosition()) {
                    case 0:
                        fragment = new hourly_frag();
                        break;
                    case 1:
                        fragment =  new weekly_frag();
                        break;
                    case 2:
                        fragment = new biweekly_frag();
                        break;
                }

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frag_cont, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public String converter(String k) {
        double kvalue = Double.parseDouble(k);
        kvalue = (kvalue-273.15);
        String cstring = df.format(kvalue);

        return cstring;
    }

    @SuppressWarnings("deprecation")
    private class retrieveWeather extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            try {
                URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=5e2876a1ab98ffba46622cd4c5cf2d00");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    StringBuilder sb = new StringBuilder();
                    String temp;

                    while ((temp = reader.readLine()) != null) {
                        sb.append(temp);
                    }
                    result = sb.toString();
                }
                else {
                    result = "error";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String temperature = "";
            String weather = "";
            String city = "";
            try {
                JSONObject object = new JSONObject(s);
                object = object.getJSONObject("main");
                temperature = object.getString("temp");
                temperature = converter(temperature);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                JSONObject object = new JSONObject(s);
                city = object.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("weather");
                JSONObject innerobject = array.getJSONObject(0);
                weather = innerobject.getString("main");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            weatherV.setText(weather);
            tempV.setText(temperature);
            cityV.setText(city);

        }
    }
}