package spring.khorsun.RestAPIApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.khorsun.RestAPIApplication.models.Sensor;
import spring.khorsun.RestAPIApplication.repositories.SensorRepository;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
