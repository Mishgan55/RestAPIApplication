package spring.khorsun.RestAPIApplication.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2,max = 100, message = "Name's characters should be between 2 and 100")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
