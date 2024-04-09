package nz.ac.auckland.se281;

import java.util.ArrayList;

class Music extends Services {

  public Music(String bookingReference, ArrayList<Booking> inputBookings) {
    super(bookingReference, inputBookings);
  }

  public boolean checkBookingReference() {
    return super.checkBookingReference();
  }

  @Override
  public String getServiceName() {
    return null;
  }

  @Override
  public int getServiceCost() {
    return 500; // Fixed cost for Music
  }
}
