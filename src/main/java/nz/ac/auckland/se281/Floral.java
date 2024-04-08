package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.FloralType;

class Floral extends Services {
  // Instance Fields
  private FloralType floralType;

  public Floral(String bookingReference, FloralType floralType, ArrayList<Booking> inputBookings) {
    super(bookingReference, inputBookings);
    this.floralType = floralType;
  }
}
