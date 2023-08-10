package spring.khorsun.RestAPIApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.khorsun.RestAPIApplication.models.Measurement;
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
