package spring.khorsun.RestAPIApplication.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.khorsun.RestAPIApplication.DTO.MeasurementDTO;
import spring.khorsun.RestAPIApplication.models.Measurement;
import spring.khorsun.RestAPIApplication.services.MeasurementService;
import spring.khorsun.RestAPIApplication.utils.ErrorException;
import spring.khorsun.RestAPIApplication.utils.ErrorResponse;
import spring.khorsun.RestAPIApplication.utils.MeasurementValidator;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static spring.khorsun.RestAPIApplication.utils.ErrorUtils.returnErrors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;
    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public List<MeasurementDTO> showAll(){

        return measurementService.showAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList());

    }

    @GetMapping("/rainyDaysCount")
    public Long countRainyDays(){
        return measurementService.showAll().stream().filter(Measurement::isRaining).count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO,
                                             BindingResult bindingResult){
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement,bindingResult);
        if (bindingResult.hasErrors()){
            returnErrors(bindingResult);
        }
        measurementService.create(measurement);
      return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    ResponseEntity<ErrorResponse> handleException(ErrorException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), new Date());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }



    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO,Measurement.class);
    }
    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement,MeasurementDTO.class);
    }
}
