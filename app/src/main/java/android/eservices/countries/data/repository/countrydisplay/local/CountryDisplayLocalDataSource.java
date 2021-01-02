package android.eservices.countries.data.repository.countrydisplay.local;

import android.eservices.countries.data.db.CountryDatabase;
import android.eservices.countries.data.entity.CountryEntity;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Is class to handle a local database
 * @author amine
 *
 */

public class CountryDisplayLocalDataSource {

    private CountryDatabase countryDatabase;

    public CountryDisplayLocalDataSource(CountryDatabase countryDatabase) {
        this.countryDatabase = countryDatabase;
    }

    /**
     *  Return List of favorites countries from local Database
     */
    public Flowable<List<CountryEntity>> loadFavorites() {
        return countryDatabase.countryDao().loadFavorites();
    }

    /**
     *  Add country to local Database
     */
    public Completable addCountryToFavorites(CountryEntity countryEntity) {
        return countryDatabase.countryDao().addCountryToFavorites(countryEntity);
    }

    /**
     *  Delete country from local Database
     */
    public Completable deleteCountryFromFavorites(String id) {
        return countryDatabase.countryDao().deleteCountryFromFavorites(id);
    }

    /**
     *  Return list of countries ID from local database
     */
    public Single<List<String>> getFavoriteIdList() {
        return countryDatabase.countryDao().getFavoriteIdList();
    }
}
