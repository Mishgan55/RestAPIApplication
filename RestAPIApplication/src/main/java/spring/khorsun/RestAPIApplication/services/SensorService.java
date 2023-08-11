package spring.khorsun.RestAPIApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.khorsun.RestAPIApplication.models.Sensor;
import spring.khorsun.RestAPIApplication.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findSensorByName(String name){
         return sensorRepository.findByName(name);
    }
    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
