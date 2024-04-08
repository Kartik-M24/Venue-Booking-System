package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Invoice extends Services {
  // Instance Fields
  private String bookingReference;
  private int totalCost;
  private ArrayList<Booking> bookings = new ArrayList<Booking>();

  public Invoice(String bookingReference, ArrayList<Booking> bookings) {
    super(bookingReference);
    this.bookings = bookings;
  }

  public void printInvoice() {
    int numberOfBookings = bookings.size();
    String customerEmail = null;
    String partyDate = null;
    String numberOfGuests = null;
    String venueName = null;

    for (int i = 0; i < numberOfBookings; i++) {
      if (bookings.get(i).getBookingsReference().equals(bookingReference)) {
        customerEmail = bookings.get(i).getClientEmail();
        partyDate = bookings.get(i).getBookingsVenueDate();
        numberOfGuests = bookings.get(i).getNumberOfAttendees();
        venueName = bookings.get(i).getVenueName();
      }
    }
    MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
        bookingReference, customerEmail, partyDate, numberOfGuests, venueName);
  }

  @Override
  public int getCost() {
    return totalCost;
  }
}
