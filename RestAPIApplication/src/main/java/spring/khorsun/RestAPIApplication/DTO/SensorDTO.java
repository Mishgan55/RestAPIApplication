package spring.khorsun.RestAPIApplication.DTO;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class SensorDTO {
    @Column(name = "name")
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
