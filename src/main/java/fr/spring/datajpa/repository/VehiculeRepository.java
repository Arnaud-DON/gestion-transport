package fr.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spring.datajpa.model.AbstractVehicule;

public interface VehiculeRepository extends JpaRepository<AbstractVehicule, String> {

}
