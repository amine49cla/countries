package android.eservices.countries.data.di;

import android.content.Context;
import android.eservices.countries.data.api.CountryDisplayService;
import android.eservices.countries.data.db.CountryDatabase;
import android.eservices.countries.data.repository.countrydisplay.CountryDisplayDataRepository;
import android.eservices.countries.data.repository.countrydisplay.CountryDisplayRepository;
import android.eservices.countries.data.repository.countrydisplay.mapper.CountryToCountryEntityMapper;
import android.eservices.countries.data.repository.countrydisplay.remote.CountryDisplayRemoteDataSource;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * It is a class to handle DependencyInjection in our project
 */

public class FakeDependencyInjection {

    private static CountryDisplayService countryDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static CountryDisplayRepository countryDisplayRepository;
    private static CountryDatabase countryDatabase;
    private static Context applicationContext;

    public static CountryDisplayRepository getCountryDisplayRepository() {
        if (countryDisplayRepository == null) {
            countryDisplayRepository = new CountryDisplayDataRepository(
                    new CountryDisplayRemoteDataSource(getCountryDisplayService()),
                    new CountryToCountryEntityMapper()
            );
        }
        return countryDisplayRepository;
    }

    public static CountryDisplayService getCountryDisplayService() {
        if (countryDisplayService == null) {
            countryDisplayService = getRetrofit().create(CountryDisplayService.class);
        }
        return countryDisplayService;
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://restcountries.eu/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static CountryDatabase getCountryDatabase() {
        if (countryDatabase == null) {
            countryDatabase = Room.databaseBuilder(applicationContext,
                    CountryDatabase.class, "country-database").build();
        }
        return countryDatabase;
    }
}
