package runnable;

import com.google.gson.Gson;
import nel.marco.gymtrainerservice.rest.v1.model.TrainerDetailModel;
import nel.marco.gymtrainerservice.rest.v1.model.TrainerModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/* This is very crud way of setting up data
I know you can create scripts that can be ran at start up
 to insert data but i chose this approach just for each of testing

*/
public class SettingUpTrainerWithDetailsData {

    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        new SettingUpTrainerWithDetailsData();
    }

    public SettingUpTrainerWithDetailsData() {

        for (int i = 0; i < 10; i++) {
            createDetailTrainers();
        }

        //findAll().forEach(gym -> findSpecificTrainer(gym.getId()));
    }

    private void createDetailTrainers() {
        TrainerModel trainer = SettingUpTrainerData.createTrainers();

        TrainerDetailModel trainerDetailModel = new TrainerDetailModel();
        trainerDetailModel.setContactNumber("0123456789");
        trainerDetailModel.setDescription("randomDescription");
        trainerDetailModel.setEmail("email@email.com");


        ResponseEntity<Object> forEntity = restTemplate.postForEntity("http://localhost:8080/trainer/" + trainer.getId() + "/details", trainerDetailModel, Object.class);


        if (forEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("ADDED");
        }


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
