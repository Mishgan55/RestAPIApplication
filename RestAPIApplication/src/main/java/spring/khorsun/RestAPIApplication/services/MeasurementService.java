package spring.khorsun.RestAPIApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.khorsun.RestAPIApplication.models.Measurement;
import spring.khorsun.RestAPIApplication.models.Sensor;
import spring.khorsun.RestAPIApplication.repositories.MeasurementRepository;
import spring.khorsun.RestAPIApplication.utils.ErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> showAll(){
        return measurementRepository.findAll();
    }
    @Transactional
    public void create(Measurement measurement){
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement){
        Optional<Sensor> sensorByName = sensorService.findSensorByName(measurement.getSensor().getName());
        if (sensorByName.isEmpty())
            throw new ErrorException("Don't find sensor");
        measurement.setSensor(sensorByName.get());
        measurement.setMeasurementDateTime(LocalDateTime.now());
    }
}
