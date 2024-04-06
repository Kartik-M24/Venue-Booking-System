package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {
  // Instance Fields
  private String venueName;
  private String venueCode;
  private Integer capacityInput;
  private Integer hireFeeInput;

  public Venue() {}

  public boolean validVenue(
      String venueName,
      String venueCode,
      String capacityInput,
      String hireFeeInput,
      ArrayList<Venue> hireVenue) {

    // Assign inputs to variables
    this.venueName = venueName.trim();
    this.venueCode = venueCode.trim();

    // Checks to see if venueName isn't valid
    if ((this.venueName.strip()).isEmpty() == true) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      this.venueName =
          null; // if user input isn't valid variables are assigne as null to prevent the venue from
      // being added
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
    int numberOfVenues = hireVenue.size();
    for (int i = 0; i < numberOfVenues; i++) {
      if (hireVenue.get(i).getVenueCode().equals(this.venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(
            hireVenue.get(i).getVenueCode(), hireVenue.get(i).getVenueName());
        this.venueCode = null;
        break;
      }
    }

    // Validates the venue details
    if (this.hireFeeInput != null
        && this.capacityInput != null
        && this.venueName != null
        && this.venueCode != null) {
      return true; // venue can be created and added to the list of venues
    } else {
      return false;
    }
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getCapacityInput() {
    return Integer.toString(capacityInput);
  }

  public String getHireFeeInput() {
    return Integer.toString(hireFeeInput);
  }
}
