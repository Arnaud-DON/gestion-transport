package fr.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spring.datajpa.model.AbstractTravel;

public interface TravelRepository extends JpaRepository<AbstractTravel, Long> {

}
