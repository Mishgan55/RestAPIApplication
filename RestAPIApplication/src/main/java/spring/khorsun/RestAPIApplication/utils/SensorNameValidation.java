package spring.khorsun.RestAPIApplication.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring.khorsun.RestAPIApplication.models.Sensor;
import spring.khorsun.RestAPIApplication.services.SensorService;

@Component
public class SensorNameValidation implements Validator {

    private final SensorService sensorService;
    @Autowired
    public SensorNameValidation(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Sensor sensor= (Sensor) target;

        if(sensorService.findSensorByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "","this sensor is already exist");
        }



    }
}
