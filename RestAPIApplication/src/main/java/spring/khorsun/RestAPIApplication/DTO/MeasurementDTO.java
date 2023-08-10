package spring.khorsun.RestAPIApplication.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {
    @Column(name = "value")
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;
    @Column(name = "raining")
    @NotNull
    private Boolean raining;
    @NotNull
    private SensorDTO sensorDTO;

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }
}
