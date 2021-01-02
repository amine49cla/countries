package android.eservices.countries.data.repository.countrydisplay;

import android.eservices.countries.data.api.model.Country;
import android.eservices.countries.data.repository.countrydisplay.mapper.CountryToCountryEntityMapper;
import android.eservices.countries.data.repository.countrydisplay.remote.CountryDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;


/**
 * It is an implementation of our Data repository
 */
public class CountryDisplayDataRepository implements CountryDisplayRepository {

    private CountryDisplayRemoteDataSource countryDisplayRemoteDataSource;
    private CountryToCountryEntityMapper countryToCountryEntityMapper;


    public CountryDisplayDataRepository(CountryDisplayRemoteDataSource countryDisplayRemoteDataSource,
                                        CountryToCountryEntityMapper countryToCountryEntityMapper) {
        this.countryDisplayRemoteDataSource = countryDisplayRemoteDataSource;
        this.countryToCountryEntityMapper = countryToCountryEntityMapper;
    }

    /**
     * @return RrJs Type that return list of country from remote database
     */
    @Override
    public Single<List<Country>> getCountries() {
        return countryDisplayRemoteDataSource.getCountries();
    }

}
