package com.example.countryapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.countryapp.R;
import com.example.countryapp.model.CountryModel;
import com.example.countryapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView countryListRecyclerView;

    TextView errorText;

    ProgressBar progressBar;

    SwipeRefreshLayout layout;

    private ListViewModel viewModel;
    private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryListRecyclerView = findViewById(R.id.countriesList);
        errorText = findViewById(R.id.listErrorText);
        progressBar = findViewById(R.id.loadingView);

        layout = findViewById(R.id.swipeRefreshLayout);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();

        countryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countryListRecyclerView.setAdapter(adapter);

        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
                layout.setRefreshing(false);
            }
        });
        
        observerViewModel();

    }

    private void observerViewModel() {
        viewModel.countries.observe(this, countryModels -> {
            if(countryModels != null){
                countryListRecyclerView.setVisibility(View.VISIBLE);
                adapter.updateCountries(countryModels);
            }
        });

        viewModel.countryLoadError.observe(this, isError -> {
            if (isError != null){
                errorText.setVisibility(isError ? View.VISIBLE: View.GONE);
            }
        });

        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null ){
                progressBar.setVisibility( isLoading ? View.VISIBLE:View.GONE);
                if (isLoading){
                    errorText.setVisibility(View.GONE);
                    countryListRecyclerView.setVisibility(View.GONE);
                }
            }
        });
    }
}