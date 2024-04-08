package nz.ac.auckland.se281;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Booking {
  private String venueCode;
  private String venueDate;
  private String bookingReference;
  private String systemDate;
  private ArrayList<String> bookingDatesList = new ArrayList<String>();
  private ArrayList<Booking> bookings = new ArrayList<Booking>();
  private int numberOfAttendees;
  private int day;
  private int month;
  private int year;
  private int systemDay;
  private int systemMonth;
  private int systemYear;

  public Booking() {}

  public String[] checkValidBooking(
      String[] options, String date, ArrayList<Venue> hireVenue, ArrayList<Booking> bookings) {
    // Pre-setting and initialising variables
    venueCode = options[0];
    venueDate = options[1];
    numberOfAttendees = Integer.parseInt(options[3]);
    systemDate = date;
    int numberOfVenues = hireVenue.size();
    int numberOfBookings = bookings.size();
    boolean venueCodeExists = false;
    String venueCapacity = null;
    String venueName = null;
    this.bookings = bookings;
    String[] outputArray = new String[2];

    // splitting the venue date into day, month, and year
    String[] dateSplit = venueDate.split("/");
    day = Integer.parseInt(dateSplit[0]);
    month = Integer.parseInt(dateSplit[1]);
    year = Integer.parseInt(dateSplit[2]);

    // Check if the venue code exists and then get the venue name and capacity
    for (int i = 0; i < numberOfVenues; i++) {
      if (hireVenue.get(i).getVenueCode().equals(venueCode)) {
        venueName = hireVenue.get(i).getVenueName();
        venueCapacity = hireVenue.get(i).getCapacityInput();
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
      // Adjustments for numberOfAtendess
      if (Integer.parseInt(venueCapacity) < numberOfAttendees) {
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            Integer.toString(numberOfAttendees), venueCapacity, venueCapacity);
        numberOfAttendees = Integer.parseInt(venueCapacity);
      }
      if (numberOfAttendees < (0.25 * Integer.parseInt(venueCapacity))) {
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            Integer.toString(numberOfAttendees),
            Integer.toString((int) (0.25 * Integer.parseInt(venueCapacity))),
            venueCapacity);
        numberOfAttendees = (int) (0.25 * Integer.parseInt(venueCapacity));
      }
      bookingReference = BookingReferenceGenerator.generateBookingReference();
      outputArray[0] = "1"; // 1 code for a successful booking
      outputArray[1] = bookingReference;
      return outputArray;
    } else {
      outputArray[0] = "0"; // 0 code for an unsuccessful booking
      outputArray[1] = null;
      return outputArray;
    }
  }

  public String getBookingsReference() {
    return bookingReference;
  }

  public String getNumberOfAttendees() {
    return Integer.toString(numberOfAttendees);
  }

  public String getBookingsVenueCode() {
    return venueCode;
  }

  public String getBookingsVenueDate() {
    return venueDate;
  }

  public String getNextAvailableDate(String inputDate, String inputCode) {
    // Initialising variables
    String availableDate = inputDate;
    String inputVenueCode = inputCode;
    bookingDatesList.clear();
    String[] availableDateSplit = availableDate.split("/");
    int availableDay = Integer.parseInt(availableDateSplit[0]);
    int numberOfBookings = bookings.size();

    // Creating a list of all booking dates
    for (int i = 0; i < numberOfBookings; i++) {
      if (this.bookings
          .get(i)
          .getBookingsVenueCode()
          .equals(inputVenueCode)) { // Adds booking to list if it is for the same venue
        bookingDatesList.add(this.bookings.get(i).getBookingsVenueDate());
      }
    }

    // Orders booking Dates list from earliest to latest
    // Note following code is taken from
    // https://www.baeldung.com/java-sort-date-strings#using-a-treemap
    // (5. Using a TreeMap) and modified to fit the context of the Booking class
    DateTimeFormatter dfm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Map<LocalDate, String> dateFormatMap = new TreeMap<>();
    bookingDatesList.forEach(string -> dateFormatMap.put(LocalDate.parse(string, dfm), string));
    List<String> bookingDatesList =
        new ArrayList<>(dateFormatMap.values()); // reassign ordered list to bookingDatesList

    int datesListSize = bookingDatesList.size();
    // Checks if any other bookings are on the same day and increments the available date
    // accordingly
    for (int i = 0; i < datesListSize; i++) {
      if (bookingDatesList.get(i).equals(availableDate)) {
        if (availableDay >= 9) {
          availableDate = (availableDay + 1) + "/0" + systemMonth + "/" + systemYear;
        } else {
          availableDate = "0" + (availableDay + 1) + "/0" + systemMonth + "/" + systemYear;
        }
      }
    }
    return availableDate;
  }
}
