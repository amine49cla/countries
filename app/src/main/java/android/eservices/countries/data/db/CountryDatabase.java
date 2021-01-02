package android.eservices.countries.data.db;

import android.eservices.countries.data.entity.CountryEntity;
import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {CountryEntity.class}, version = 1, exportSchema = false)
public abstract class CountryDatabase extends RoomDatabase {
}