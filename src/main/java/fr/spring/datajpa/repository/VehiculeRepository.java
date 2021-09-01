package fr.spring.datajpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spring.datajpa.model.VehiculeService;

public interface VehiculeRepository extends JpaRepository<VehiculeService, String> {

	Optional<VehiculeService> findByImmatriculation(String immatriculation);

	Boolean existsByImmatriculation(String immatriculation);
}
