package com.example.statesinfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.caverock.androidsvg.SVGImageView;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private Context context;
    private List<Country> countryList;

    public CountryAdapter(Context context, List<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countryList.get(position);

        // Bind data to the views
        holder.svgImageView.setSVG(country.getSvgImage());
        holder.textViewName.setText(country.getName());
        holder.textViewCapital.setText("Capital: " + country.getCapital());
        holder.textViewPopulation.setText("Population: " + country.getPopulation());
        holder.textViewRegion.setText("Region: " + country.getRegion());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        SVGImageView svgImageView;
        TextView textViewName;
        TextView textViewCapital;
        TextView textViewPopulation;
        TextView textViewRegion;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            svgImageView = itemView.findViewById(R.id.svgImageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCapital = itemView.findViewById(R.id.textViewCapital);
            textViewPopulation = itemView.findViewById(R.id.textViewPopulation);
            textViewRegion = itemView.findViewById(R.id.textViewRegion);
        }
    }
}
