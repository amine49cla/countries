package android.eservices.countries.presentation.countrydisplay.list.fragment;

import android.eservices.countries.data.api.model.Country;
import android.eservices.countries.data.di.FakeDependencyInjection;
import android.eservices.countries.presentation.countrydisplay.list.CountryListContract;
import android.eservices.countries.presentation.countrydisplay.list.CountryListPresenter;
import android.eservices.countries.presentation.countrydisplay.list.adapter.CountryAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.eservices.countries.R;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements CountryListContract.View {
    public static String TAB_NAME = "COUNTRIES";
    private View rootView;
    CountryListContract.Presenter countryListPresenter;
    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    List<Country> countries = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;

    public ListFragment() {
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }
    /**
     * This method allows us to display a message when a country is deleted
     *
     * @param message to display
     */
    public void displaySnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    /**
     * This method allows us to change display format for listing countries
     */
    public void changeLayout() {
        if (layoutManager instanceof GridLayoutManager) {
            layoutManager = new LinearLayoutManager(getContext());
        } else {
            layoutManager = new GridLayoutManager(getContext(), 2);
        }
        recyclerView.setLayoutManager(layoutManager);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        countryAdapter = new CountryAdapter();
        recyclerView.setAdapter(countryAdapter);
        countryListPresenter = new CountryListPresenter(FakeDependencyInjection.getCountryDisplayRepository());
        countryListPresenter.attachView(this);
        coordinatorLayout = rootView.findViewById(R.id.coordinator_layout);
        setupRecyclerView();

    }

    public void setupRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        countryListPresenter.searchCountries();
    }

    @Override
    public void displayCountries(List<Country> countryItemViewModelList) {
        countries = countryItemViewModelList;
        countryAdapter.bindViewModels(countryItemViewModelList);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        countryListPresenter.detachView();
    }
}