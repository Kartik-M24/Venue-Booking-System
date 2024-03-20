package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  private String venueName;
  private String venueCode;
  private String capacityInput;
  private String hireFeeInput;
  

  public VenueHireSystem() {}

  public void printVenues() {
    
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
        //Assign inputs to variables
        this.venueName = venueName;
        this.venueCode = venueCode;
        this.capacityInput = capacityInput;
        this.hireFeeInput = hireFeeInput;

        //Check if inputs are valid
        if ((venueName.strip()).isEmpty() == true){
          MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
        }




  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
