package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdvertRepository extends JpaRepository<PropertyAdvert, Long> {

    List<PropertyAdvert> findByOrderByTimeOfActivationDesc();

    List<PropertyAdvert> findByOrderByNumberOfRoomsDesc();

    List<PropertyAdvert> findByOrderByPriceDesc();

    Optional<PropertyAdvert> findOneById(Long id);

}
