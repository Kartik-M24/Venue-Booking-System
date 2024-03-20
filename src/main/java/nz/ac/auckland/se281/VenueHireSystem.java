package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jgit.ignore.internal.Strings;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {
  //Instance Fields
  private String venueName;
  private String venueCode;
  private Integer capacityInput;
  private Integer hireFeeInput;
  private ArrayList<ArrayList<String>> hireVenue = new ArrayList<ArrayList<String>>(); //Multidimensional ArrayList


  public VenueHireSystem() {}

  public void printVenues() {
    int numberOfVenues = hireVenue.size();
        if (numberOfVenues == 0){
          MessageCli.NO_VENUES.printMessage();
        }
        if (numberOfVenues == 1){
          MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
          MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode); 
        }
        // if (numberOfVenues > 1 && numberOfVenues < 10){
          
        // }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
        //Assign inputs to variables
        this.venueName = venueName;
        this.venueCode = venueCode;
        
        //Checks to see if hireFeeInput and capacityInput inputs can be converted into Integers
        try {
          this.capacityInput = Integer.parseInt(capacityInput);
        }
        catch (NumberFormatException e1) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
        }
        try {
          this.hireFeeInput = Integer.parseInt(hireFeeInput);
        }
        catch (NumberFormatException e2) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
        }

        //Check if user inputs are valid
        if ((this.venueName.strip()).isEmpty() == true){
          MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
        }
        if (this.capacityInput <= 0) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
        }
        if (this.hireFeeInput <= 0) {
          MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        }

        //Check to make sure venueCode is unique


        //All user inputs are valid - create venue
        this.hireVenue.add(new ArrayList<String>(Arrays.asList(venueName, venueCode, capacityInput, hireFeeInput)));
        MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode); 
       

    



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
