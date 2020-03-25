package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;
import javafx.beans.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdvertRepository extends JpaRepository<PropertyAdvert, Long> {

    List<PropertyAdvert> findByOrderByTimeOfActivationDesc();

    List<PropertyAdvert> findPropertyAdvertsByCity(String city);

    List<PropertyAdvert> findPropertyAdvertsByPriceIsBetween(Double minPrice, Double maxPrice);

    List<PropertyAdvert> findPropertyAdvertsByAreaIsBetween(Integer minArea, Integer maxArea);

    List<PropertyAdvert> findPropertyAdvertsByNumberOfRoomsIsBetween(Integer minRooms, Integer maxRooms);

    List<PropertyAdvert> findPropertyAdvertsByPropertyType(PropertyType propertyType);

    List<PropertyAdvert> findPropertyAdvertsByPropertyConditionType(PropertyConditionType propertyConditionType);

    //List<PropertyAdvert> findPropertyAdvertsByListOfImagesNotNull();

}
