import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/* The client class implements POST requests:
   1. Adding a new sensor
   2. Adding a new dimension   */
public class Client {

    public static void main(String[] args) {
        final String nameSensor="main-sensor";
        createSensor(nameSensor);

        //to calculate random temperature and rain
        Random random=new Random();
        Double maxTemperate=45.0;
        //creation of 1000 random measurements
        for (int i = 0; i < 5000; i++) {
            System.out.println(i);
            createMeasurement(random.nextDouble()*maxTemperate,random.nextBoolean(),nameSensor);
        }
    }
    //static method for passing JSON to create a new sensor
    private static void createSensor(String sensorName){
       Map<String,Object> map=new HashMap<>();
       final String url="http://localhost:8080/sensors/registration";
       map.put("name", sensorName);

        postRequest(map,url);
    }
    //static method for passing JSON to create a new measurement
    private static void createMeasurement(Double value,Boolean raining,String sensorName){
        Map<String,Object> map= new HashMap<>();
        final String url="http://localhost:8080/measurements/add";
        map.put("value",value);
        map.put("raining",raining);
        map.put("sensor", Map.of("name",sensorName));

        postRequest(map,url);

    }
    //method for creating a POST request
    private static void postRequest(Map<String, Object> map, String url) {
        final RestTemplate restTemplate=new RestTemplate();

        final HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request= new HttpEntity<>(map,httpHeaders);
        try{
        restTemplate.postForEntity(url,request,String.class);
            System.out.println("Operation is sucseed");
        }catch (Exception e){
            System.out.println("ERROR");
            e.getMessage();
        }
    }
}
