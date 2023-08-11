package spring.khorsun.RestAPIApplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.khorsun.RestAPIApplication.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    Optional<Sensor> findByName(String name);

}
