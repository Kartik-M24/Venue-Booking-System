package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Invoice extends Services {
  // Instance Fields
  private int totalCost;
  private ArrayList<Venue> hireVenues = new ArrayList<Venue>();
  private String systemDate;
  private String cateringName;
  private String floralName;
  private int cateringCostPerPerson;
  private int floralCost;
  private int musicCost;

  public Invoice(
      String bookingReference,
      ArrayList<Booking> bookings,
      String systemDate,
      ArrayList<Venue> hireVenuesArray,
      String cateringName,
      int cateringCostPerPerson,
      String floralName,
      int floralCost,
      int musicCost) {
    super(bookingReference, bookings);
    this.systemDate = systemDate;
    this.hireVenues = hireVenuesArray;
    this.cateringName = cateringName;
    this.floralName = floralName;
    this.cateringCostPerPerson = cateringCostPerPerson;
    this.floralCost = floralCost;
    this.musicCost = musicCost;
  }

  public void printInvoice() {
    int numberOfBookings = bookings.size();
    String customerEmail = null;
    String partyDate = null;
    String numberOfGuests = null;
    String venueName = null;

    // Find values to input for top half of invoice
    for (int i = 0; i < numberOfBookings; i++) {
      if (bookings.get(i).getBookingsReference().equals(bookingReference)) {
        customerEmail = bookings.get(i).getClientEmail();
        partyDate = bookings.get(i).getBookingsVenueDate();
        numberOfGuests = bookings.get(i).getNumberOfAttendees();
        venueName = bookings.get(i).getVenueName();
      }
    }
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference, customerEmail, this.systemDate, partyDate, numberOfGuests, venueName);

    // Find values to input for cost section of invoice
    int numberOfVenues = hireVenues.size();
    String venueHireFee = null;
    int cateringCost = cateringCostPerPerson * Integer.parseInt(numberOfGuests);
    for (int i = 0; i < numberOfVenues; i++) {
      if (hireVenues.get(i).getVenueName().equals(venueName)) {
        venueHireFee = hireVenues.get(i).getHireFeeInput();
      }
    }
    // Outputs cost section if user has selected catering, music or floral services
    MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(venueHireFee);
    if (cateringName != null) {
      MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
          cateringName, Integer.toString(cateringCost));
    }
    if (musicCost != 0) {
      MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(Integer.toString(musicCost));
    }
    if (floralName != null) {
      MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
          floralName, Integer.toString(floralCost));
    }

    // Bottom half of invoice
    totalCost = Integer.parseInt(venueHireFee) + cateringCost + musicCost + floralCost;
    MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(Integer.toString(totalCost));
  }
}
