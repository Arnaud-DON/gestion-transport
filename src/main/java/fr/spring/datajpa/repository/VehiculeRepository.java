package fr.spring.datajpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.spring.datajpa.model.VehiculeService;

public interface VehiculeRepository extends JpaRepository<VehiculeService, String> {

	Optional<VehiculeService> findByImmatriculation(String immatriculation);

	Boolean existsByImmatriculation(String immatriculation);

	@Query("SELECT v FROM VehiculeService v WHERE v.status IN('IN_SERVICE', 'RESERVED', 'BUSY')")
	List<VehiculeService> findLesVoituresQuiPeuventRouler();
}
