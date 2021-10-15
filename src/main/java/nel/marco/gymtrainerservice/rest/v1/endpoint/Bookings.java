package nel.marco.gymtrainerservice.rest.v1.endpoint;

import nel.marco.gymtrainerservice.business.manager.BookingManager;
import nel.marco.gymtrainerservice.rest.v1.model.BookingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Bookings {

  private final BookingManager bookingManager;

  public Bookings(BookingManager bookingManager) {
    this.bookingManager = bookingManager;
  }

  @PostMapping("/book")
  public ResponseEntity<?> bookAClass(@RequestBody BookingRequest bookingRequest) {

    boolean isBooked = bookingManager.bookAClass(bookingRequest);

    return ResponseEntity.accepted().build();
  }

  @GetMapping("/book/{id}/accept")
  public ResponseEntity<?> acceptBooking(
      @RequestParam long gymClassId, @RequestParam long trainerId) {

    bookingManager.acceptBooking(gymClassId, trainerId);
    return ResponseEntity.accepted().build();
  }
}
