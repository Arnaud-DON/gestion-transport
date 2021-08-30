package fr.spring.datajpa.repository;
import fr.spring.datajpa.model.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long>{
}
