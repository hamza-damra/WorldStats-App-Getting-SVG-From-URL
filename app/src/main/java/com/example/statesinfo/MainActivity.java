package com.example.statesinfo;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.caverock.androidsvg.SVG;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    private final List<Country> countryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        countryAdapter = new CountryAdapter(this, countryList);
        recyclerView.setAdapter(countryAdapter);

        fetchCountryDataFromUrl();
    }

    private void fetchCountryDataFromUrl() {
        String url = "https://myapis.fra1.digitaloceanspaces.com/api.json";

        new FetchJsonTask().execute(url);
    }

    @SuppressLint("StaticFieldLeak")
    private class FetchJsonTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String jsonResponse = null;

            try {
                URL urlObj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    StringBuilder stringBuilder = new StringBuilder();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }

                    jsonResponse = stringBuilder.toString();
                    reader.close();
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String json) {
            if (json != null) {
                parseJsonData(json);
            }
        }
    }

    private void parseJsonData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray informationArray = jsonObject.getJSONArray("information");

            for (int i = 0; i < informationArray.length(); i++) {
                JSONObject countryObject = informationArray.getJSONObject(i);
                String name = countryObject.getString("name");
                String capital = countryObject.getString("capital");
                int population = countryObject.getInt("population");
                String region = countryObject.getString("region");
                String flagUrl = countryObject.getJSONObject("flags").getString("svg");

                new LoadSvgTask(name, capital, population, region, flagUrl).execute(flagUrl);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadSvgTask extends AsyncTask<String, Void, SVG> {
        private final String name;
        private final String capital;
        private final int population;
        private final String region;
        private final String flagUrl;

        public LoadSvgTask(String name, String capital, int population, String region, String flagUrl) {
            this.name = name;
            this.capital = capital;
            this.population = population;
            this.region = region;
            this.flagUrl = flagUrl;
        }

        @Override
        protected SVG doInBackground(String... params) {
            String svgUrl = params[0];
            try {
                URL url = new URL(svgUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                return SVG.getFromInputStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(SVG svg) {
            if (svg != null) {
                countryList.add(new Country(name, svg, capital, population, region, flagUrl));
                countryAdapter.notifyDataSetChanged();
            }
        }
    }
}
