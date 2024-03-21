package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  // Instance Fields
  private String venueName;
  private String venueCode;
  private Integer capacityInput;
  private Integer hireFeeInput;
  private ArrayList<ArrayList<String>> hireVenue =
      new ArrayList<ArrayList<String>>(); // Multidimensional ArrayList

  public VenueHireSystem() {} // Check purpose of this should I be putting instance fields here?

  public void printVenues() {
    Integer numberOfVenues = hireVenue.size();

    if (numberOfVenues == 0) {
      MessageCli.NO_VENUES.printMessage();
    }
    if (numberOfVenues == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
    }
    if (numberOfVenues > 1 && numberOfVenues < 10) {
      MessageCli.NUMBER_VENUES.printMessage(
          "are", "", "s"); // haven't configured converting into to string value
    }
    if (numberOfVenues >= 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", "" + numberOfVenues, "s");
    }
    for (int i = 0; i < numberOfVenues; i++) {
      MessageCli.VENUE_ENTRY.printMessage(
          hireVenue.get(i).get(0), // gets the first value of the current row, venueName
          hireVenue.get(i).get(1), // second value venueCode
          hireVenue.get(i).get(2), // third value capacityInput
          hireVenue.get(i).get(3)); // fourth value hireFeeInput
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // Assign inputs to variables
    this.venueName = venueName;
    this.venueCode = venueCode;

    // Outputs Error message if venueName isn't valid
    if ((this.venueName.strip()).isEmpty() == true) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      this.venueName = null;
    }

    // Checks to see if hireFeeInput and capacityInput inputs can be converted into Integers and if
    // these integer values are appropriate
    try {
      this.capacityInput = Integer.parseInt(capacityInput);
      if (this.capacityInput <= 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        this.capacityInput = null;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      this.capacityInput = null;
    }
    try {
      this.hireFeeInput = Integer.parseInt(hireFeeInput);
      if (this.hireFeeInput <= 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        this.hireFeeInput = null;
      }
    } catch (NumberFormatException e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      this.hireFeeInput = null;
    }

    // Check to make sure venueCode is unique
    Integer numVenues = hireVenue.size();
    for (int x = 0; x < numVenues; x++) {
      if (hireVenue.get(x).get(1).equals(this.venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(
            hireVenue.get(x).get(1), hireVenue.get(x).get(0));
        this.venueCode = null;
        break;
      }
    }

    // Checks if all user inputs are valid - create venue
    if (this.hireFeeInput != null
        && this.capacityInput != null
        && this.venueName != null
        && this.venueCode != null) {
      this.hireVenue.add(
          new ArrayList<String>(Arrays.asList(venueName, venueCode, capacityInput, hireFeeInput)));
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(
          venueName, venueCode); // outputs success message
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
