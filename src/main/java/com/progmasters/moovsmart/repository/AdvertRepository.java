package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface AdvertRepository extends JpaRepository<PropertyAdvert, Long> {

    @Query(value = "SELECT p FROM PropertyAdvert p WHERE p.advertStatus='FORAPPROVAL'")
    List<PropertyAdvert> findPropertyAdvertsByAdvertStatus_FORAPPROVAL();

    List<PropertyAdvert> findPropertyAdvertsByCity(String city);

    List<PropertyAdvert> findPropertyAdvertsByPriceIsBetween(Double minPrice, Double maxPrice);

    List<PropertyAdvert> findPropertyAdvertsByAreaIsBetween(Integer minArea, Integer maxArea);

    List<PropertyAdvert> findPropertyAdvertsByNumberOfRoomsIsBetween(Integer minRooms, Integer maxRooms);

    List<PropertyAdvert> findPropertyAdvertsByPropertyType(PropertyType propertyType);

    List<PropertyAdvert> findPropertyAdvertsByPropertyConditionType(PropertyConditionType propertyConditionType);

    //List<PropertyAdvert> findPropertyAdvertsByListOfImagesNotNull();

    Optional<PropertyAdvert> findOneById(Long id);


}
