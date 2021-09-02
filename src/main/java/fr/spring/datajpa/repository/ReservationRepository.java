package fr.spring.datajpa.repository;

import fr.spring.datajpa.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
/** @Query(value = SELECT resa FROM Reservation resa JOIN FETCH .passagers psgr\"\n" +
            "            + \" WHERE psgr.mail=:mail\"\n" +
            "            + \" AND cv.date < CURRENT_DATE\", nativeQuery = false")
 **/
}
