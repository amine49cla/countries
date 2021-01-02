package android.eservices.countries.presentation.countrydisplay.list.adapter;

/**
 * It is an interface that define a method to handle countries action interface
 */
public interface CountryActionInterface {
    void onFavoriteToggle(String countryId, boolean isFavorite);
}
