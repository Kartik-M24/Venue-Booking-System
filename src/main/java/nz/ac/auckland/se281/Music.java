package nz.ac.auckland.se281;

import java.util.ArrayList;

class Music extends Services {

  public Music(String bookingReference, ArrayList<Booking> inputBookings) {
    super(bookingReference, inputBookings);
  }

  public boolean checkBookingReference() {
    return super.checkBookingReference();
  }

  public int getMusicCost() {
    return 500; // set cost of music
  }
}
