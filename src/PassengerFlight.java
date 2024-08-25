// Student Name: Berkay Senler 
// Student No: s8087440

/**
 * The PassengerFlight class extends the Flight class and adds attributes
 * specific to passenger flights, such as seat class, meal service, and
 * in-flight entertainment options.
 */

public class PassengerFlight extends Flight {

    // Instance variables specific to PassengerFlight
    private boolean mealService; // Indicates whether meal service is available on the flight
    private boolean inFlightEntertainment; // Indicates whether in-flight entertainment is available

    // Constructor to initialize PassengerFlight-specific and inherited variables
    public PassengerFlight(String airlineName, String flightNumber, String flightOrigin, String flightDestination,
            double airfare, String departureTime, String arrivalTime, double flightDuration,
            double distance, String flightDate, String gateNumber, int availableSeats, String seatClass,
            boolean mealService, boolean inFlightEntertainment) {

        // Call the parent class (Flight) constructor to initialize common flight
        // attributes
        super(airlineName, flightNumber, flightOrigin, flightDestination, airfare, departureTime, arrivalTime,
                flightDuration, availableSeats, distance, gateNumber, seatClass, flightDate);

        // Initialize PassengerFlight-specific attributes
        this.mealService = mealService;
        this.inFlightEntertainment = inFlightEntertainment;
    }

    // Override toString method to provide a formatted string representation of the
    // PassengerFlight object

    @Override
    public String toString() {
        return "Passenger Flight [Airline Name: " + getAirlineName() + ", Flight Number: " + getFlightNumber() +
                ", Origin: " + getFlightOrigin() + ", Destination: " + getFlightDestination() +
                ", Seat Class: " + getSeatClass() + ", Gate Number: " + getGateNumber() + ", Available Seats: "
                + getAvailableSeats() + ", Airfare: $" + getAirfare() +
                ", Flight Date: " + getFlightDate() + ", Departure Time: " + getDepartureTime() +
                ", Arrival Time: " + getArrivalTime() + ", Duration: " + getFlightDuration() + " HOURS" +
                ", Distance: " + getDistance() + " KM" + ", Meal Service: " + mealService +
                ", In-Flight Entertainment: " + inFlightEntertainment + "]";
    }
}
