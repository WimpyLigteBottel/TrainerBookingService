package nel.marco.gymtrainerservice.rest.v1.endpoint;

import nel.marco.gymtrainerservice.business.dto.TrainerDto;
import nel.marco.gymtrainerservice.business.manager.TrainerDetailManager;
import nel.marco.gymtrainerservice.mapper.TrainerMapper;
import nel.marco.gymtrainerservice.rest.v1.model.TrainerDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TrainerDetails {

  @Autowired private TrainerDetailManager trainerDetailManager;

  @GetMapping("/trainer/{id}/details")
  public ResponseEntity<TrainerDetailModel> findTrainer(@PathVariable long id) {

    Optional<nel.marco.gymtrainerservice.db.entity.TrainerDetail> trainerDto =
        trainerDetailManager.find(id);

    if (trainerDto.isPresent()) {
      return ResponseEntity.ok(TrainerMapper.INSTANCE.mapToV1(trainerDto.get()));
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping("/trainer/{id}/details")
  public ResponseEntity<?> createDetailedProfile(
      @PathVariable long id, @RequestBody TrainerDetailModel detailTrainerModel) {
    TrainerDto trainerDto = TrainerMapper.INSTANCE.mapToV1(detailTrainerModel);

    boolean isCreated = trainerDetailManager.create(trainerDto);

    if (isCreated) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // This is very bad way of responding ideally i need to give more accurate error message if
    // possible
    // ideally in the 400 range to indicate  it is client error
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
