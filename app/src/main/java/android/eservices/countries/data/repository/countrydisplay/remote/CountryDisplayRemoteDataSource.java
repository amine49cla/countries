package android.eservices.countries.data.repository.countrydisplay.remote;

import android.eservices.countries.data.api.CountryDisplayService;
import android.eservices.countries.data.api.model.Country;
import java.util.List;
import io.reactivex.Single;

/**
 * It is a class to handle a remote Data
 * @author amine
 *
 */

public class CountryDisplayRemoteDataSource {

    private CountryDisplayService countryDisplayService;

    public CountryDisplayRemoteDataSource(CountryDisplayService countryDisplayService) {
        this.countryDisplayService = countryDisplayService;
    }

    /**
     * @return a country from remote database through web service
     */
    public Single<List<Country>> getCountries() {
        return countryDisplayService.getCountries();
    }

    /**
     * @param idCountry
     * @return a detail country from remote database through web service
     */
    public Single<Country> getCountry(String idCountry) {
        return countryDisplayService.getCountry(idCountry);
    }

}
