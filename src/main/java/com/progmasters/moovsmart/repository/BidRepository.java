package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {


    @Query("SELECT b FROM Bid b WHERE b.propertyAdvertId.id = :advertId ORDER BY b.dateTimeOfBid DESC")
    Stream<Bid> findBidsByPropertyAdvertId(Long advertId);


}
