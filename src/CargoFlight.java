// Student Name: Berkay Senler 
// Student No: s8087440

/**
 * The CargoFlight class extends the Flight class and adds attributes
 * specific to cargo flights, such as cargo type and cargo weight.
 * It is used to represent flights that primarily transport goods.
 */

public class CargoFlight extends Flight {

    // Instance variables specific to CargoFlight
    private double cargoWeight; // Weight for the cargo in kilograms
    private String cargoType; // Type of cargo being transported

    // Constructor to initialize CargoFlight-specific and inherited variables
    public CargoFlight(String airlineName, String flightNumber, String flightOrigin, String flightDestination,
            String departureTime, String arrivalTime, double flightDuration,
            double distance, String flightDate, double cargoWeight, String cargoType, String gateNumber) {

        // Call the parent class (Flight) constructor to initialize common flight
        // attributes
        super(airlineName, flightNumber, flightOrigin, flightDestination, 0.0, departureTime, arrivalTime,
                flightDuration, 0, distance, gateNumber, null, flightDate);

        // Initialize CargoFlight-specific attributes
        this.cargoWeight = cargoWeight;
        this.cargoType = cargoType;
    }

    /*
     * GETTER AND SETTER METHODS FOR THE SPECIFIC INSTANCE VARIABLES FOR CARGOFLIGHT
     */
    public double getCargoWeight() {
        return cargoWeight;
    }

    public String getCargoType() {
        return cargoType;
    }

    // Override toString method to provide a formatted string representation of the
    // CargoFlight object
    @Override
    public String toString() {
        return "Cargo Flight [Airline Name: " + getAirlineName() + ", Flight Number: " + getFlightNumber() +
                ", Origin: " + getFlightOrigin() + ", Destination: " + getFlightDestination() +
                ", Gate Number: " + getGateNumber() + ", Cargo Weight: " + cargoWeight + "KG" +
                ", Cargo Type: " + cargoType + ", Flight Date: " + getFlightDate() +
                ", Departure Time: " + getDepartureTime() + ", Arrival Time: " + getArrivalTime() +
                ", Duration: " + getFlightDuration() + " HOURS" + ", Distance: " + getDistance() + " KM]";
    }
}
