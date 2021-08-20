package fr.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spring.datajpa.model.AbstractUser;

public interface UserRepository extends JpaRepository<AbstractUser, Long> {

}
