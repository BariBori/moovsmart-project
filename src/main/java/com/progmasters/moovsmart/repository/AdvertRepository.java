package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdvertRepository extends JpaRepository<PropertyAdvert, Long> {

    List<PropertyAdvert> findByOrderByTimeOfActivationDesc();

    List<PropertyAdvert> findByOrderByNumberOfRoomsDesc();

    List<PropertyAdvert> findByOrderByPriceDesc();

}
