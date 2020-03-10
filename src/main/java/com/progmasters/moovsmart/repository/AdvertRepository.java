package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<Advert, Long> {
}
