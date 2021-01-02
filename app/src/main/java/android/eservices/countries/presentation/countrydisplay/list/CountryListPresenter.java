package android.eservices.countries.presentation.countrydisplay.list;

import android.eservices.countries.data.api.model.Country;
import android.eservices.countries.data.repository.countrydisplay.CountryDisplayRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountryListPresenter implements CountryListContract.Presenter {

    private CountryDisplayRepository countryDisplayRepository;
    private CountryListContract.View view;
    private CompositeDisposable compositeDisposable;


    public CountryListPresenter(CountryDisplayRepository countryDisplayRepository) {
        this.countryDisplayRepository = countryDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void searchCountries() {
        compositeDisposable.clear();
        compositeDisposable.add(countryDisplayRepository.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Country>>() {

                    @Override
                    public void onSuccess(List<Country> countries) {
                        // work with the resulting todo
                        view.displayCountries(countries);

                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        System.out.println(e.toString());
                    }
                }));

    }
    
    @Override
    public void attachView(CountryListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }




}
