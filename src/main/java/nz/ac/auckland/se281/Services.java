package nz.ac.auckland.se281;

import java.util.ArrayList;

public abstract class Services {
  // Instance Fields
  protected String bookingReference;
  protected ArrayList<Booking> bookings = new ArrayList<Booking>();

  // Constructor
  public Services(String bookingReference, ArrayList<Booking> inputBookings) {
    this.bookingReference = bookingReference;
    this.bookings = inputBookings;
  }

  public boolean checkBookingReference() {
    int numberOfBookings = bookings.size();
    boolean validReference = false;
    for (int i = 0; i < numberOfBookings; i++) {
      if (bookings.get(i).getBookingsReference().equals(bookingReference)) {
        return validReference = true;
      }
    }
    return validReference;
  }
}
