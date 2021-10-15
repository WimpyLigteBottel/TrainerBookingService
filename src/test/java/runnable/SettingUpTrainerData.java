package runnable;

import com.google.gson.Gson;
import nel.marco.gymtrainerservice.rest.v1.model.GymModel;
import nel.marco.gymtrainerservice.rest.v1.model.TrainerModel;
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
public class SettingUpTrainerData {

    public static void main(String[] args) {
        new SettingUpTrainerData();
    }

    public SettingUpTrainerData() {

        for (int i = 0; i < 10; i++) {
            createTrainers();
        }

//        findAll().forEach(gym -> findSpecificTrainer(gym.getId()));
    }

    public static TrainerModel createTrainers() {
        RestTemplate restTemplate = new RestTemplate();


        GymModel gymModel = new GymModel();
        gymModel.setName(UUID.randomUUID().toString());

        restTemplate.postForEntity("http://localhost:8080/gym", gymModel, Object.class);

        ResponseEntity<List<GymModel>> gyms =
                restTemplate.exchange(
                        String.format("http://localhost:8080/gym?filterGymName=%s", gymModel.getName()),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<GymModel>>() {
                        });


        TrainerModel request = new TrainerModel();
        request.setName(UUID.randomUUID().toString());
        //Very bad to do it like this but i am assuming it created successfully
        request.setGymId(gyms.getBody().get(0).getId());

        ResponseEntity<Object> forEntity = restTemplate.postForEntity("http://localhost:8080/trainer", request, Object.class);

        if (forEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("ADDED");
        }

        return request;
    }


    private void findSpecificTrainer(long id) {
        RestTemplate restTemplate = new RestTemplate();


        ResponseEntity<Object> forEntity =
                restTemplate.getForEntity(String.format("http://localhost:8080/trainer/%d", id), Object.class);

        if (forEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println(new Gson().toJson(forEntity.getBody()));
        }
    }

    private List<TrainerModel> findAll() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<TrainerModel>> trainers =
                restTemplate.exchange(
                        "http://localhost:8080/trainer",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<TrainerModel>>() {
                        });

        if (trainers.getStatusCode().is2xxSuccessful()) {
            System.out.println(new Gson().toJson(trainers.getBody()));
        }

        return trainers.getBody();
    }
}
