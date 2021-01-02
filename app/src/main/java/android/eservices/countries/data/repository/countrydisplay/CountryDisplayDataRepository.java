package android.eservices.countries.data.repository.countrydisplay;

import android.eservices.countries.data.api.model.Country;
import android.eservices.countries.data.entity.CountryEntity;
import android.eservices.countries.data.repository.countrydisplay.local.CountryDisplayLocalDataSource;
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

    private CountryDisplayLocalDataSource countryDisplayLocalDataSource;
    private CountryDisplayRemoteDataSource countryDisplayRemoteDataSource;
    private CountryToCountryEntityMapper countryToCountryEntityMapper;


    public CountryDisplayDataRepository(CountryDisplayLocalDataSource countryDisplayLocalDataSource,
                                        CountryDisplayRemoteDataSource countryDisplayRemoteDataSource,
                                        CountryToCountryEntityMapper countryToCountryEntityMapper) {
        this.countryDisplayLocalDataSource = countryDisplayLocalDataSource;
        this.countryDisplayRemoteDataSource = countryDisplayRemoteDataSource;
        this.countryToCountryEntityMapper = countryToCountryEntityMapper;
    }

    /**
     * It is a method which allows to add country to our list of favorites countries
     * @param countryId
     * @return RrJs Type to know that country is added or not
     */
    @Override
    public Completable addCountryToFavorites(String countryId) {

        return countryDisplayRemoteDataSource.getCountry(countryId)
                .map(new Function<Country, CountryEntity>() {
                    @Override
                    public CountryEntity apply(Country country) {
                        return countryToCountryEntityMapper.map(country);
                    }
                })
                .flatMapCompletable(new Function<CountryEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(CountryEntity countryEntity) {
                        return countryDisplayLocalDataSource.addCountryToFavorites(countryEntity);
                    }
                });
    }

    /**
     * It is a method which allows to remove country from our list of favorites countries
     * @param countryId
     * @return RrJs Type to know that country is added or not
     */

    @Override
    public Completable removeCountryFromFavorites(String countryId) {
        return countryDisplayLocalDataSource.deleteCountryFromFavorites(countryId);
    }

    /**
     * It is a method which allows to add country to our list of favorites countries
     * @return RrJs Type that contain all favorites countries
     */

    /**
     * @return RrJs Type that return list of country from remote database
     */
    @Override
    public Single<List<Country>> getCountries() {
        return countryDisplayRemoteDataSource.getCountries()
                .zipWith(countryDisplayLocalDataSource.getFavoriteIdList(), new BiFunction<List<Country>, List<String>, List<Country>>() {
                    @Override
                    public List<Country> apply(List<Country> countries, List<String> longs) throws Exception {
                        for (Country country : countries) {
                            if (longs.contains(country.getId())) {
                                country.setFavorite();
                            }
                        }
                        return countries;
                    }
                });
    }

}
