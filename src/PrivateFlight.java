// Student Name: Berkay Senler 
// Student No: s8087440

/**
 * The PrivateFlight class extends the Flight class and includes attributes
 * specific to private flights, such as ownership status, plane type, and
 * the name of the primary passenger. It represents flights operated for
 * private individuals or organizations.
 */

public class PrivateFlight extends Flight {

    // Instance variables specific to PrivateFlight
    private String ownership; // Describes the ownership of the private plane (e.g., "Owned", "Leased")
    private String passengerName; // Name of the primary passenger on the private flight
    private String planeType; // Type of the private plane

    // Constructor to initialize PrivateFlight-specific and inherited variables
    public PrivateFlight(String airlineName, String planeType, String passengerName, String ownership,
            String flightNumber,
            String flightOrigin, String flightDestination, String departureTime, String arrivalTime,
            double flightDuration, double distance, String flightDate, String gateNumber,
            Boolean mealService, Boolean inFlightEntertainment) {

        // Call the parent class (Flight) constructor to initialize common flight
        // attributes
        super(airlineName, flightNumber, flightOrigin, flightDestination, 0.0, departureTime, arrivalTime,
                flightDuration, 0, distance, gateNumber, null, flightDate);

        // Initialize PrivateFlight-specific attributes
        this.ownership = ownership;
        this.passengerName = passengerName;
        this.planeType = planeType;

    }

    /*
     * GETTER AND SETTER METHODS FOR THE SPECIFIC INSTANCE VARIABLES FOR
     * PRIVATEFLIGHT
     */
    public String getOwnership() {
        return ownership;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPlaneType() {
        return planeType;
    }

    // Override toString method to provide a formatted string representation of the
    // PrivateFlight object

    @Override
    public String toString() {
        return "Private Flight [Airline Name: " + getAirlineName() + ", Plane Type: " + planeType + ", Passenger Name: "
                + passengerName + ", Ownership: "
                + ownership + ", Flight Number: " + getFlightNumber() +
                ", Origin: " + getFlightOrigin() + ", Destination: " + getFlightDestination() +
                ", Gate Number: " + getGateNumber() + ", Flight Date: " + getFlightDate() +
                ", Departure Time: " + getDepartureTime() + ", Arrival Time: " + getArrivalTime() +
                ", Duration: " + getFlightDuration() + " HOURS" + ", Distance: " + getDistance() + " KM]";
    }

}
