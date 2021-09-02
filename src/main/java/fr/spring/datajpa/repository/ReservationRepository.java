package fr.spring.datajpa.repository;

import fr.spring.datajpa.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT resa FROM Reservation resa JOIN FETCH resa.organisateur orga"
            + " WHERE orga.mail=:mail"
            + " AND resa.date > CURRENT_DATE", nativeQuery = false)
    public List<Reservation> findCurrentReservationCollaborateur(@Param("mail") String mail);

}
