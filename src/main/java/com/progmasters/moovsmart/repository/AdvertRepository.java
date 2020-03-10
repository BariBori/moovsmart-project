package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<PropertyAdvert, Long> {
}
