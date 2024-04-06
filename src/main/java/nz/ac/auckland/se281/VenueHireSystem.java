package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  // Instance Fields
  private ArrayList<Venue> hireVenue = new ArrayList<Venue>(); // Arraylist of valid Venues
  private Integer numberOfVenues;

  public VenueHireSystem() {}

  public void printVenues() {
    // Pre-setting variables
    numberOfVenues = hireVenue.size();
    String[] numberInWords = {
      "two", "three", "four", "five", "six", "seven", "eight", "nine"
    }; // called when total venues being displayed is between 2-9 inclusive

    // Outputs appropriate message for Number of Venues already booked according to numebrOfVenues
    if (numberOfVenues == 0) {
      MessageCli.NO_VENUES.printMessage();
    }
    if (numberOfVenues == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
    }
    if (numberOfVenues > 1 && numberOfVenues < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", numberInWords[numberOfVenues - 2], "s");
    }
    if (numberOfVenues >= 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", "" + numberOfVenues, "s");
    }

    // Print list of already booked Venues (and their details)
    for (int i = 0; i < numberOfVenues; i++) {
      MessageCli.VENUE_ENTRY.printMessage(
          hireVenue.get(i).getVenueName(), // gets the first value of the current row, venueName
          hireVenue.get(i).getVenueCode(), // second value venueCode
          hireVenue.get(i).getCapacityInput(), // third value capacityInput
          hireVenue.get(i).getHireFeeInput()); // fourth value hireFeeInput
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    Venue booking = new Venue(); // Verifies Venue details and creates a new Venue object

    // If the venue is valid, add it to the list of venues
    if (booking.validVenue(venueName, venueCode, capacityInput, hireFeeInput, hireVenue) == true) {
      hireVenue.add(booking); // Adds venue to hireVenue
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
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
