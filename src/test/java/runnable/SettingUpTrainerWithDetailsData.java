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
        findAll().forEach(this::createDetailTrainers);

    }

    private void createDetailTrainers(TrainerModel trainerModel) {

        TrainerDetailModel trainerDetailModel = new TrainerDetailModel();
        trainerDetailModel.setContactNumber("0123456789");
        trainerDetailModel.setDescription("randomDescription");
        trainerDetailModel.setEmail("email@email.com");


        ResponseEntity<Object> forEntity = restTemplate.postForEntity("http://localhost:8080/trainer/" + trainerModel.getId() + "/details", trainerDetailModel, Object.class);


        if (forEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("ADDED");
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
