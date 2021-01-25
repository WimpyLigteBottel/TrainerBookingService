package runnable;

import com.google.gson.Gson;
import nel.marco.gymtrainerservice.rest.v1.model.GymModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

/* This is very crud way of setting up data
I know you can create scripts that can be ran at start up
 to insert data but i chose this approach just for each of testing

*/
public class SettingUpGymData {

    public static void main(String[] args) {
        new SettingUpGymData();
    }

    public SettingUpGymData() {

        for (int i = 0; i < 10; i++) {
            insertRecords();
        }

        findAll().forEach(gym -> findSpecificGym(gym.getId()));
    }

    private void insertRecords() {
        RestTemplate restTemplate = new RestTemplate();

        GymModel request = new GymModel();
        request.setName(UUID.randomUUID().toString());

        ResponseEntity<Object> forEntity =
                restTemplate.postForEntity("http://localhost:8080/gym", request, Object.class);

        if (forEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("ADDED");
        }
    }

    private void findSpecificGym(long id) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Object> forEntity =
                restTemplate.getForEntity(String.format("http://localhost:8080/gym/%d", id), Object.class);

        if (forEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println(new Gson().toJson(forEntity.getBody()));
        }
    }

    private List<GymModel> findAll() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<GymModel>> gyms =
                restTemplate.exchange(
                        "http://localhost:8080/gym",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<GymModel>>() {
                        });

        if (gyms.getStatusCode().is2xxSuccessful()) {
            System.out.println(new Gson().toJson(gyms.getBody()));
        }

        return gyms.getBody();
    }
}
