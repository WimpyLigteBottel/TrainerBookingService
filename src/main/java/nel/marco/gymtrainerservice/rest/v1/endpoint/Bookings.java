package nel.marco.gymtrainerservice.rest.v1.endpoint;

import nel.marco.gymtrainerservice.rest.v1.model.BookingRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class Bookings {

  @PostMapping("/book")
  public void bookAClass(@RequestBody BookingRequest bookingRequest) {

    // TODO: implement validator

    // TODO: add code to book the class
  }

  @GetMapping("/book/{id}/accept")
  public void acceptBooking(@RequestParam long gymClassId, @RequestParam long longId) {

    //

  }
}
