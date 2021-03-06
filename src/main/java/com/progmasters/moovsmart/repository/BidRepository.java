package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.Bid;
import com.progmasters.moovsmart.dto.list.MyBidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Stream;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {


    @Query("SELECT b FROM Bid b WHERE b.propertyAdvertId.id = :advertId ORDER BY b.dateTimeOfBid DESC")
    Stream<Bid> findBidsByPropertyAdvertId(Long advertId);

    @Query("SELECT COUNT (DISTINCT b.userId.userName) FROM Bid b WHERE b.propertyAdvertId.id = :advertId")
    Long findNumberOfUniqueBidders(@Param("advertId") Long advertId);

    List<Bid> findAll();

   @Query("SELECT b FROM Bid b WHERE b.userId.userName = :userName GROUP BY b.propertyAdvertId.address ORDER BY b.propertyAdvertId.endOfAuction DESC ")
    Stream<Bid> findMyBidProperties(String userName);

    @Query("SELECT b FROM Bid b WHERE b.propertyAdvertId.id = :advertId AND b.userId.userName= :userName ORDER BY b.dateTimeOfBid DESC")
    Stream<Bid> findMyBidsByPropertyAdvertId(Long advertId, String userName);


}
