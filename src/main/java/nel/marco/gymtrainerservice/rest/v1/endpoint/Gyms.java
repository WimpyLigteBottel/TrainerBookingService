package nel.marco.gymtrainerservice.rest.v1.endpoint;

import nel.marco.gymtrainerservice.business.dto.GymDto;
import nel.marco.gymtrainerservice.business.manager.GymManager;
import nel.marco.gymtrainerservice.mapper.GymMapper;
import nel.marco.gymtrainerservice.rest.v1.model.GymModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Gyms {


    @Autowired
    private GymManager gymManager;


    @GetMapping("/gym")
    public ResponseEntity<List<GymModel>> findAllGyms(@RequestParam(required = false, defaultValue = "10") int maxResults, @RequestParam(required = false, defaultValue = "0") int index, @RequestParam(required = false, defaultValue = "") String filterGymName) {
        List<GymDto> all = gymManager.findAll(maxResults, index, filterGymName);


        return ResponseEntity.ok(GymMapper.INSTANCE.mapToV1(all));
    }

    @PostMapping("/gym")
    public ResponseEntity<?> createNewGym(@RequestBody GymModel gym) {

        GymDto mapToV1 = GymMapper.INSTANCE.mapToV1(gym);

        gymManager.create(mapToV1);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/gym/{id}")
    public ResponseEntity<GymModel> findGym(@PathVariable long id) {

        Optional<GymDto> gymDto = gymManager.find(id);

        if (gymDto.isPresent()) {
            return ResponseEntity.ok(GymMapper.INSTANCE.mapToV1(gymDto.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
