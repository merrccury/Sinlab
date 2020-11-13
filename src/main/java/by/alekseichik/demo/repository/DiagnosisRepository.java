package by.alekseichik.demo.repository;

import by.alekseichik.demo.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Optional<Diagnosis> findById(Long id);
}
