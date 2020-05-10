package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.AdvertStatusType;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface AdvertRepository extends JpaRepository<PropertyAdvert, Long> {

    Optional<PropertyAdvert> findOneById(Long id);

    @Query(value = "SELECT p FROM PropertyAdvert p WHERE p.advertStatus='FORAPPROVAL' ORDER BY p.createdAt DESC")
    List<PropertyAdvert> findPropertyAdvertsByAdvertStatus_FORAPPROVAL();

    @Query("SELECT DISTINCT p.city FROM PropertyAdvert p ORDER BY p.city")
    List<String> findAllCities();

    @Query("SELECT p FROM PropertyAdvert p " +
            "WHERE p.city LIKE CONCAT('%',:city,'%') " +
            "AND p.price >=:minPrice " +
            "AND p.price <=:maxPrice " +
            "AND p.area >=:minArea " +
            "AND p.area <=:maxArea " +
            "AND p.numberOfRooms >=:minRooms " +
            "AND p.numberOfRooms <=:maxRooms " +
            "AND p.propertyType LIKE CASE WHEN :propertyType IS NOT NULL THEN :propertyType ELSE '%' END " +
            "AND p.propertyConditionType LIKE CASE WHEN :propertyConditionType IS NOT NULL THEN :propertyConditionType ELSE '%' END " +
            "AND p.advertStatus = :advertStatusType ")
    List<PropertyAdvert> findFilteredPropertyAdverts(@Param("city") String city, @Param("minPrice") Double minPrice,
                                                     @Param("maxPrice") Double maxPrice,
                                                     @Param("minArea") Integer minArea, @Param("maxArea") Integer maxArea,
                                                     @Param("minRooms") Integer minRooms, @Param("maxRooms") Integer maxRooms,
                                                     @Param("propertyType") PropertyType propertyType,
                                                     @Param("propertyConditionType") PropertyConditionType propertyConditionType,
                                                     @Param("advertStatusType") AdvertStatusType advertStatusType);


    @Query("SELECT p FROM PropertyAdvert p WHERE p.userName = :userName ORDER BY p.createdAt DESC")
    Stream<PropertyAdvert> findMyProperties(String userName);


    @Query("SELECT p FROM PropertyAdvert p WHERE p.advertStatus='FORAPPROVAL' ORDER BY p.createdAt DESC")
    Page<PropertyAdvert> findAllByPaginator(Pageable pageable);


}
