package fr.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spring.datajpa.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
