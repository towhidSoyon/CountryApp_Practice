package com.example.countryapp.di;

import com.example.countryapp.model.CountriesService;
import com.example.countryapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountriesService service);

    void inject(ListViewModel viewModel);
}
