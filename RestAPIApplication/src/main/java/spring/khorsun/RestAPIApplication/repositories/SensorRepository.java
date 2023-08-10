package spring.khorsun.RestAPIApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.khorsun.RestAPIApplication.models.Sensor;
@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
}
