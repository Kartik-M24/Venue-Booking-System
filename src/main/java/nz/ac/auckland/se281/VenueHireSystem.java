package nz.ac.auckland.se281;

import java.lang.reflect.Array;
import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  // Instance Fields
  private ArrayList<Venue> hireVenue = new ArrayList<Venue>(); // Arraylist of valid Venues
  private ArrayList<Booking> bookings = new ArrayList<Booking>(); // Arraylist of valid Bookings
  private Integer numberOfVenues;
  private String date;

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
          hireVenue.get(i).getVenueName(), // gets the corresponding venue i, and its name
          hireVenue.get(i).getVenueCode(),
          hireVenue.get(i).getCapacityInput(),
          hireVenue.get(i).getHireFeeInput());
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
    this.date = dateInput;
    MessageCli.DATE_SET.printMessage(date);
  }

  public void printSystemDate() {
    if (this.date == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(this.date);
    }
  }

  public void makeBooking(String[] options) {
    Booking reservation = new Booking(); // Verifies Venue details and creates a new Venue object

    // If the venue is valid, add it to the list of venues
    if (reservation.validBooking(options, this.date, hireVenue) == true) {
      bookings.add(reservation); // Adds venue to hireVenue

      String venueName = null;
      // Find the venueName via the venueCode
      for (int i = 0; i < numberOfVenues; i++) {
        if (hireVenue.get(i).getVenueCode().equals(options[1])) {
          venueName = hireVenue.get(i).getVenueName();
        }
      }
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        BookingReferenceGenerator.generateBookingReference(), venueName, options[1], options[3]);
    }
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
