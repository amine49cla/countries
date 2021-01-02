package android.eservices.countries;

import android.app.Application;
import android.eservices.countries.data.di.FakeDependencyInjection;
import com.facebook.stetho.Stetho;

public class CountryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
