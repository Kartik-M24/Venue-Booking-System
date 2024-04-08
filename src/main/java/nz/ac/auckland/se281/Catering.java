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

  public int getCost() {
    int cateringTypeCost = cateringType.getCostPerPerson();
    return cateringTypeCost;
  }
}
