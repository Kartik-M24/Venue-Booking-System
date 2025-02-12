package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  // Instance Fields
  private ArrayList<Venue> hireVenue = new ArrayList<Venue>(); // Arraylist of valid Venues
  private ArrayList<Booking> bookings = new ArrayList<Booking>(); // Arraylist of valid Bookings
  private Integer numberOfVenues;
  private Integer numberOfBookings;
  private String systemDate;
  private String availableDate;
  private String floralName = null;
  private String cateringName = null;
  private int floralCost = 0;
  private int cateringCost = 0;
  private int musicCost = 0;

  public VenueHireSystem() {}

  public void printVenues() {
    // Pre-setting variables
    numberOfVenues = hireVenue.size();
    numberOfBookings = bookings.size();
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
      availableDate = this.systemDate;
      if (numberOfBookings > 0) {
        for (int j = 0; j < numberOfBookings; j++) {
          if (hireVenue.get(i).getVenueCode().equals(bookings.get(j).getBookingsVenueCode())) {
            availableDate =
                bookings
                    .get(j)
                    .getNextAvailableDate(availableDate, hireVenue.get(i).getVenueCode());
          }
        }
      }
      if (availableDate == null) { // if systemDate isn't set
        MessageCli.VENUE_ENTRY.printMessage(
            hireVenue.get(i).getVenueName(),
            hireVenue.get(i).getVenueCode(),
            hireVenue.get(i).getCapacityInput(),
            hireVenue.get(i).getHireFeeInput());
      } else {
        MessageCli.VENUE_ENTRY.printMessage(
            hireVenue.get(i).getVenueName(), // gets the corresponding venue i, and its name
            hireVenue.get(i).getVenueCode(),
            hireVenue.get(i).getCapacityInput(),
            hireVenue.get(i).getHireFeeInput(),
            availableDate);
      }
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    Venue booking = new Venue(); // Verifies Venue details and creates a new Venue object

    // If the venue is valid, add it to the list of venues
    if (booking.checkValidVenue(venueName, venueCode, capacityInput, hireFeeInput, hireVenue)
        == true) {
      hireVenue.add(booking); // Adds venue to hireVenue
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }
  }

  public void setSystemDate(String dateInput) {
    this.systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(systemDate);
  }

  public void printSystemDate() {
    if (this.systemDate == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(this.systemDate);
    }
  }

  public void makeBooking(String[] options) {
    Booking reservation = new Booking(); // Verifies Venue details and creates a new Venue object
    String[] bookingDetails = new String[2];
    bookingDetails = reservation.checkValidBooking(options, this.systemDate, hireVenue, bookings);

    // If the venue is valid, add it to the list of venues
    if (bookingDetails[0].equals("1")) {
      bookings.add(reservation); // Adds reservation to bookings

      numberOfVenues = hireVenue.size();
      numberOfBookings = bookings.size();
      String venueName = null;
      String venueAttendees = null;

      // Find the venueName via the venueCode
      for (int i = 0; i < numberOfVenues; i++) {
        if (hireVenue.get(i).getVenueCode().equals(options[0])) {
          venueName = hireVenue.get(i).getVenueName();
        }
      }
      for (int i = 0; i < numberOfBookings; i++) {
        if (bookings.get(i).getBookingsVenueCode().equals(options[0])) {
          venueAttendees = bookings.get(i).getNumberOfAttendees();
        }
      }

      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
          bookingDetails[1], venueName, options[1], venueAttendees);
    }
  }

  public void printBookings(String venueCode) {
    // Pre-setting variables
    numberOfBookings = bookings.size();
    numberOfVenues = hireVenue.size();
    boolean venueExists = false;
    String venueName = null;

    for (int i = 0; i < numberOfVenues; i++) {
      if (hireVenue.get(i).getVenueCode().equals(venueCode)) {
        venueExists = true;
        venueName = hireVenue.get(i).getVenueName();
        break;
      }
    }

    // Outputs appropriate message based on whether the venue exists or not and number of bookings
    if (venueExists == false) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
    } else {
      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName);
      int bookingsEntry = 0;
      for (int i = 0; i < numberOfBookings; i++) {
        if (bookings.get(i).getBookingsVenueCode().equals(venueCode)) {
          bookingsEntry++;
          MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
              bookings.get(i).getBookingsReference(), bookings.get(i).getBookingsVenueDate());
        }
      }
      if (bookingsEntry == 0) {
        MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
      }
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    Catering addCatering =
        new Catering(
            bookingReference,
            cateringType,
            bookings); // Verifies Catering details and creates a new Catering object
    // Gets catering details
    cateringName = addCatering.getServiceName();
    cateringCost = addCatering.getServiceCost();

    if (addCatering.checkBookingReference() == false) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
    } else {
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
          "Catering (" + cateringType.getName() + ")", bookingReference);
    }
  }

  public void addServiceMusic(String bookingReference) {
    Music addMusic = new Music(bookingReference, bookings);
    musicCost = addMusic.getServiceCost();

    if (addMusic.checkBookingReference() == false) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
    } else {
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
    }
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    FloralService addFloral = new FloralService(bookingReference, floralType, bookings);
    // Gets floral details
    floralName = addFloral.getServiceName();
    floralCost = addFloral.getServiceCost();

    // Checks if the booking reference is valid and accordingly prints the appropriate message
    if (addFloral.checkBookingReference() == false) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
    } else {
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
          "Floral (" + floralType.getName() + ")", bookingReference);
    }
  }

  public void viewInvoice(String bookingReference) {
    Invoice invoice =
        new Invoice(
            bookingReference,
            bookings,
            systemDate,
            hireVenue,
            cateringName,
            cateringCost,
            floralName,
            floralCost,
            musicCost);

    if (invoice.checkBookingReference() == false) {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
    } else {
      invoice.printInvoice(); // This method gets values from the bookings and hireVenues arraylists
      // required for the invoice
    }
    // Resetting the values of the invoice
    cateringCost = 0;
    floralCost = 0;
    musicCost = 0;
    cateringName = null;
    floralName = null;
  }
}
