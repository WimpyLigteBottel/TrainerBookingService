package nel.marco.gymtrainerservice.rest.v1.endpoint;

import nel.marco.gymtrainerservice.business.dto.TrainerDto;
import nel.marco.gymtrainerservice.business.manager.TrainerManager;
import nel.marco.gymtrainerservice.mapper.TrainerMapper;
import nel.marco.gymtrainerservice.rest.v1.model.TrainerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
Do note i am ignore security on my endpoint, ideally i would allow only certain users to access this endpoint/service
 */
@RestController
public class Trainers {

  @Autowired private TrainerManager trainerManager;

  @GetMapping("/trainer")
  public ResponseEntity<List<TrainerModel>> findAllTrainers(
      @RequestParam(required = false, defaultValue = "10") int maxResults,
      @RequestParam(required = false, defaultValue = "0") int index,
      @RequestParam(required = false, defaultValue = "") String filterTrainerName) {
    List<TrainerDto> all = trainerManager.findAll(maxResults, index, filterTrainerName);

    return ResponseEntity.ok(TrainerMapper.INSTANCE.mapToV1(all));
  }

  @PostMapping("/trainer")
  public ResponseEntity<?> createTrainer(@RequestBody TrainerModel trainer) {

    TrainerDto trainerDto = TrainerMapper.INSTANCE.mapToV1(trainer);

    if (trainerManager.create(trainerDto)) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // This is very bad way of responding ideally i need to give more accurate error message if
    // possible
    // ideally in the 400 range to indicate  it is client error
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  @GetMapping("/trainer/{id}")
  public ResponseEntity<TrainerModel> findTrainer(@PathVariable long id) {

    Optional<TrainerDto> trainerDto = trainerManager.find(id);

    if (trainerDto.isPresent()) {
      return ResponseEntity.ok(TrainerMapper.INSTANCE.mapToV1(trainerDto.get()));
    }

    return ResponseEntity.notFound().build();
  }
}
