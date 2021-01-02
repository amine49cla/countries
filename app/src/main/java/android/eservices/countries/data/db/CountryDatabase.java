package android.eservices.countries.data.db;

import android.eservices.countries.data.db.CountryDao;
import android.eservices.countries.data.entity.CountryEntity;
import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * It is a abstarct class which allows us  to create our local database to manage favorites country
 */

@Database(entities = {CountryEntity.class}, version = 1, exportSchema = false)
public abstract class CountryDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();
}