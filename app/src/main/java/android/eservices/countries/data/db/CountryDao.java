package android.eservices.countries.data.db;

import android.eservices.countries.data.entity.CountryEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * It is our Dao to request the database
 */
@Dao
public interface CountryDao {

    @Query("SELECT * from countryentity")
    Flowable<List<CountryEntity>> loadFavorites();

    @Insert
    public Completable addCountryToFavorites(CountryEntity countryEntity);

    @Query("DELETE FROM countryentity WHERE id = :id")
    public Completable deleteCountryFromFavorites(String id);

    @Query("SELECT id from countryentity")
    Single<List<String>> getFavoriteIdList();
}
