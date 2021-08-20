package fr.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spring.datajpa.model.VehiculeService;

public interface VehiculeRepository extends JpaRepository<VehiculeService, String> {

}
