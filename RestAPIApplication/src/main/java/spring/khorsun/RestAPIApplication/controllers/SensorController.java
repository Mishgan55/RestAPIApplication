package spring.khorsun.RestAPIApplication.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.khorsun.RestAPIApplication.DTO.SensorDTO;
import spring.khorsun.RestAPIApplication.models.Sensor;
import spring.khorsun.RestAPIApplication.services.SensorService;
import spring.khorsun.RestAPIApplication.utils.ErrorException;
import spring.khorsun.RestAPIApplication.utils.ErrorResponse;
import spring.khorsun.RestAPIApplication.utils.SensorNameValidation;

import javax.validation.Valid;

import java.util.Date;

import static spring.khorsun.RestAPIApplication.utils.ErrorUtils.returnErrors;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorNameValidation sensorNameValidation;
    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper,
                            SensorNameValidation sensorNameValidation) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorNameValidation = sensorNameValidation;
    }
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> saveSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        Sensor sensor = convertToSensor(sensorDTO);
        sensorNameValidation.validate(sensor,bindingResult);
        if (bindingResult.hasErrors()){
            returnErrors(bindingResult);
        }
        sensorService.save(sensor);

        return ResponseEntity.ok(HttpStatus.OK);

    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ErrorException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),new Date());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO,Sensor.class);
    }

}
