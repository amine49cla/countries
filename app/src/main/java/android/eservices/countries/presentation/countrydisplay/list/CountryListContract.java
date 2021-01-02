package android.eservices.countries.presentation.countrydisplay.list;

import android.eservices.countries.data.api.model.Country;
import java.util.List;

/**
 * It is an interface that represent country contract
 */

public class CountryListContract {

    public interface View {
        void displayCountries(List<Country> countryItemViewModelList);

        void onCountryAddedToFavorites();

        void onCountryRemovedFromFavorites();

    }

    public interface Presenter {
        void searchCountries();

        void attachView(View view);

        void addCountryToFavorite(String countryId);

        void removeCountryFromFavorites(String countryId);

        void detachView();
    }
}
