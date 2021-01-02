package android.eservices.countries.data.repository.countrydisplay;

import android.eservices.countries.data.api.model.Country;
import android.eservices.countries.data.entity.CountryEntity;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * It is interface to define our interface contract
 */
public interface CountryDisplayRepository {

    Single<List<Country>> getCountries();

    Completable addCountryToFavorites(String CountryId);

    Completable removeCountryFromFavorites(String countryId);

}
