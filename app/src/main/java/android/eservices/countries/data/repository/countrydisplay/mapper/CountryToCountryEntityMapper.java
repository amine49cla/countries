package android.eservices.countries.data.repository.countrydisplay.mapper;

import android.eservices.countries.data.api.model.Country;
import android.eservices.countries.data.entity.CountryEntity;

/**
 * It is a class to map a film to an entity film
 * @author amine
 *
 */

public class CountryToCountryEntityMapper {

    public CountryEntity map(Country country) {
        CountryEntity countryEntity = new CountryEntity();

        countryEntity.setId(country.getId());
        countryEntity.setArea(country.getArea());
        countryEntity.setCapital(country.getCapital());
        countryEntity.setFlag(country.getFlag());
        countryEntity.setName(country.getName());
        countryEntity.setPopulation(country.getPopulation());
        countryEntity.setRegion(country.getRegion());

        return countryEntity;
    }

}
