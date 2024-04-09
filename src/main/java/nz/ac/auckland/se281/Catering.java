package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;

class Catering extends Services {
  // Instance Fields
  private CateringType cateringType;

  public Catering(String bookingReference, CateringType cateringType, ArrayList<Booking> bookings) {
    super(bookingReference, bookings);
    this.cateringType = cateringType;
  }

  public boolean checkBookingReference() {
    return super.checkBookingReference();
  }
}
