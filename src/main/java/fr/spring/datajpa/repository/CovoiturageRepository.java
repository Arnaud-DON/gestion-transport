package fr.spring.datajpa.repository;
import fr.spring.datajpa.model.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long> {
    @Query(value = "SELECT cv FROM Covoiturage cv JOIN FETCH cv.passagers psgr WHERE psgr.mail=:mail", nativeQuery = false)
    public List<Covoiturage> findCovoiturageCollaborateur(@Param("mail") String mail);
}
