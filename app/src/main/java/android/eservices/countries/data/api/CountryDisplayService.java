package android.eservices.countries.data.api;

import android.eservices.countries.data.api.model.Country;

import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * It is interface to describe end points
 * @author amine
 */

public interface CountryDisplayService {

    /**
     * @return
     */
    @GET("rest/v2/all")
    Single<List<Country>> getCountries();

    /**
     * @param id  for a specific country
     * @return a detailed country
     */
    @GET("rest/v2/alpha/ALA/{id}")
    Single<Country> getCountry(@Path("id") String id);

}
