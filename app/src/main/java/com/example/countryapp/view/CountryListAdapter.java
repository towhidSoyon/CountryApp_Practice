package com.example.countryapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countryapp.R;
import com.example.countryapp.model.CountryModel;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private List<CountryModel> countries;

    public CountryListAdapter(List<CountryModel> countries) {
        this.countries = countries;
    }

    public void updateCountries(List<CountryModel> newCountries){
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country,parent,false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        ImageView countryImage;
        TextView countryName;
        TextView capitalName;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            countryImage = itemView.findViewById(R.id.imageView);
            countryName = itemView.findViewById(R.id.name);
            capitalName = itemView.findViewById(R.id.capital);
        }

        void bind(CountryModel country){
            countryName.setText(country.getCountryName());
            capitalName.setText(country.getCapital());
            Util.loadImage(countryImage,country.getFlag(),Util.getProgressDrawable(countryImage.getContext()));
        }
    }
}
