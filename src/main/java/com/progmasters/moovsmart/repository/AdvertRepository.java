package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.dto.PropertyCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface AdvertRepository extends JpaRepository<PropertyAdvert, Long> {

    List<PropertyAdvert> findByOrderByTimeOfActivationDesc();

    List<PropertyAdvert> findByOrderByNumberOfRoomsDesc();

    List<PropertyAdvert> findByOrderByPriceDesc();


    @Query("SELECT DISTINCT p.city FROM PropertyAdvert p ORDER BY p.city")
    List<String> findAllCities();


}
