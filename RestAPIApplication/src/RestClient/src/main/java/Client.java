import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {

    public static void main(String[] args) {
        final String nameSensor="main-sensor";

        createSensor(nameSensor);

        Random random=new Random();
        Double maxTemperate=45.0;
        for (int i = 0; i < 5000; i++) {
            System.out.println(i);
            createMeasurement(random.nextDouble()*maxTemperate,random.nextBoolean(),nameSensor);
        }




    }

    private static void createSensor(String sensorName){
        Map<String,Object> map=new HashMap<>();
        String url="http://localhost:8080/sensors/registration";
        map.put("name", sensorName);

        postRequest(map,url);
    }

    private static void createMeasurement(Double value,Boolean raining,String sensorName){
        Map<String,Object> map= new HashMap<>();
        String url="http://localhost:8080/measurements/add";
        map.put("value",value);
        map.put("raining",raining);
        map.put("sensor", Map.of("name",sensorName));

        postRequest(map,url);

    }

    private static void postRequest(Map<String, Object> map, String url) {
        RestTemplate restTemplate=new RestTemplate();

        HttpHeaders httpHeaders=new HttpHeaders();
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
