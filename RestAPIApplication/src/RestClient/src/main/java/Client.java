import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String[] args) {
        String nameSensor="sensor3";

        createSensor(nameSensor);



    }

    private static void createSensor(String sensorName){
        Map<String,Object> map=new HashMap<>();
        String url="http://localhost:8080/sensors/registration";
        map.put("name", sensorName);

        postRequest(map,url);
    }

    private static void postRequest(Map<String, Object> map, String url) {
        RestTemplate restTemplate=new RestTemplate();

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request= new HttpEntity<>(map,httpHeaders);
        try{
        restTemplate.postForEntity(url,request,String.class);
            System.out.println("Measurement is sucseed");
        }catch (Exception e){
            System.out.println("ERROR");
            e.getMessage();


        }
    }
}
