package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FilterPropertyAdvert extends CrudRepository<PropertyAdvert, Long>, JpaSpecificationExecutor<PropertyAdvert> {





}
