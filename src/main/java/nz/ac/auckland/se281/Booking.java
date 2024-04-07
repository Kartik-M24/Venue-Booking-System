package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {
  private String venueCode;
  private String venueDate;
  private String systemDate;

  public Booking() {}

  public boolean validBooking(
      String[] options, String date, ArrayList<Venue> hireVenue, ArrayList<Booking> bookings) {
    // Pre-setting and initialising variables
    venueCode = options[0];
    venueDate = options[1];
    systemDate = date;
    int numberOfVenues = hireVenue.size();
    int numberOfBookings = bookings.size();
    boolean venueCodeExists = false;
    int systemDay;
    int systemMonth;
    int systemYear;
    String venueName = null; // may cause issues

    // splitting the venue date into day, month, and year
    String[] dateSplit = venueDate.split("/");
    int day = Integer.parseInt(dateSplit[0]);
    int month = Integer.parseInt(dateSplit[1]);
    int year = Integer.parseInt(dateSplit[2]);

    for (int i = 0; i < numberOfVenues; i++) {
      if (hireVenue.get(i).getVenueCode().equals(venueCode)) {
        venueName = hireVenue.get(i).getVenueName();
        venueCodeExists = true;
      }
    }

    for (int i = 0; i < numberOfBookings; i++) {
      if (bookings.get(i).getBookingsVenueCode().equals(venueCode)
          && bookings.get(i).getBookingsVenueDate().equals(venueDate)) {
        MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venueName, venueDate);
        venueDate = null;
      }
    }

    // Outputting error message if booking conditions aren't met
    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    } else {
      // splitting the system date into day, month, and year (after confirming system date is set)
      String[] systemDateSplit = systemDate.split("/");
      systemDay = Integer.parseInt(systemDateSplit[0]);
      systemMonth = Integer.parseInt(systemDateSplit[1]);
      systemYear = Integer.parseInt(systemDateSplit[2]);

      // Check if the booking date is in the past
      if (year < systemYear
          || (year == systemYear && month < systemMonth)
          || (year == systemYear && month == systemMonth && day < systemDay)) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(venueDate, systemDate);
        venueDate = null;
      }
    }
    if (numberOfVenues == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
    }
    if (venueCodeExists == false) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(venueCode);
    }

    // If all conditions are met, create a new booking
    if (systemDate != null && numberOfVenues != 0 && venueCodeExists == true && venueDate != null) {
      return true;
    } else {
      return false;
    }
  }

  public String getBookingsVenueCode() {
    return venueCode;
  }

  public String getBookingsVenueDate() {
    return venueDate;
  }
}
