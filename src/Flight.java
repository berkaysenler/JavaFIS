// Student Name: Berkay Senler 
// Student No: s8087440

/**
 * The Flight class represents a general flight with common attributes
 * such as airline name, flight number, origin, destination, and timings.
 * It serves as a base class for specific flight types like PassengerFlight,
 * CargoFlight, and PrivateFlight.
 */

public class Flight {

    // Instance variables to store the flight details
    private String airlineName;
    private String flightNumber;
    private String flightOrigin;
    private String flightDestination;
    private double airfare;
    private String departureTime;
    private String arrivalTime;

    // Additional instance variables
    private double flightDuration;
    private int availableSeats;
    private double distance;
    private String gateNumber;
    private String seatClass;
    private String flightDate;

    // Constructor to initialize all the instance variables
    public Flight(String airlineName, String flightNumber, String flightOrigin, String flightDestination,
            double airfare, String departureTime, String arrivalTime, double flightDuration, int availableSeats,
            double distance, String gateNumber, String seatClass, String flightDate) {

        // Initialize the instance variables using the provided arguments
        this.airlineName = airlineName;
        this.flightNumber = flightNumber;
        this.flightOrigin = flightOrigin;
        this.flightDestination = flightDestination;
        this.airfare = airfare;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightDuration = flightDuration;
        this.availableSeats = availableSeats;
        this.distance = distance;
        this.gateNumber = gateNumber;
        this.seatClass = seatClass;
        this.flightDate = flightDate;

    }

    /*
     * GETTER AND SETTER METHODS FOR THE INSTANCE VARIABLES
     */

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightOrigin() {
        return flightOrigin;
    }

    public void setFlightOrigin(String flightOrigin) {
        this.flightOrigin = flightOrigin;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }

    public double getAirfare() {
        return airfare;
    }

    public void setAirfare(double airfare) {
        this.airfare = airfare;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(double flightDuration) {
        this.flightDuration = flightDuration;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;

    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    // toString method to provide a formatted string representation of the Flight
    // object
    public String toString() {
        return "Flight [Airline Name: " + airlineName + ", Flight Number: " + flightNumber +
                ", Origin: " + flightOrigin + ", Destination: " + flightDestination +
                ", Gate Number: " + gateNumber + ", Seat Class: " + seatClass +
                ", Available Seats: " + availableSeats + ", Airfare: $" + airfare +
                ", Flight Date: " + flightDate + ", Departure Time: " + departureTime +
                ", Arrival Time: " + arrivalTime + ", Duration: " + flightDuration + " HOURS" +
                ", Distance: " + distance + " KM]";
    }

}
