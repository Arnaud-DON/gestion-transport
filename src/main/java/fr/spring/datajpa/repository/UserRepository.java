package fr.spring.datajpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spring.datajpa.model.AbstractUser;

public interface UserRepository extends JpaRepository<AbstractUser, Long> {

	Optional<AbstractUser> findByMail(String mail);

	Boolean existsByMail(String mail);
}
