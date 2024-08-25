// Student Name: Berkay Senler 
// Student No: s8087440

/**
 * The FlightComparator class implements the Comparator interface to provide
 * custom sorting logic for Flight objects. It allows sorting based on various
 * criteria such as airline name, flight number, cargo weight, seat class, and
 * other flight-specific attributes.
 */

import java.util.Comparator;

public class FlightComparator implements Comparator<Flight> {

    // Instance variable to store the user's sorting option
    private int sortOption;

    // Constructor to initialize the sortOption variable
    public FlightComparator(int sortOption) {
        this.sortOption = sortOption;
    }

    // Overridden compare method to define custom sorting logic based on sortOption
    @Override
    public int compare(Flight f1, Flight f2) {
        switch (sortOption) {
            case 1: // Airline Name (PassengerFlight Only)
                if (f1 instanceof PassengerFlight && f2 instanceof PassengerFlight) {
                    return ((PassengerFlight) f1).getAirlineName()
                            .compareToIgnoreCase(((PassengerFlight) f2).getAirlineName());
                }
                return 0; // If not both PassengerFlights, leave them in original order
            case 2:
                return f1.getFlightNumber().compareToIgnoreCase(f2.getFlightNumber());
            case 3: // Cargo Weight (CargoFlight Only)
                if (f1 instanceof CargoFlight && f2 instanceof CargoFlight) {
                    return Double.compare(((CargoFlight) f1).getCargoWeight(),
                            ((CargoFlight) f2).getCargoWeight());
                }
                return 0; // If not both CargoFlights, leave them in original order
            case 4: // Seat Class (PassengerFlight Only)
                if (f1 instanceof PassengerFlight && f2 instanceof PassengerFlight) {
                    return ((PassengerFlight) f1).getSeatClass()
                            .compareToIgnoreCase(((PassengerFlight) f2).getSeatClass());
                }
                return 0; // If not both PassengerFlights, leave them in original order
            case 5: // Plane Type (PrivateFlight Only)
                if (f1 instanceof PrivateFlight && f2 instanceof PrivateFlight) {
                    return ((PrivateFlight) f1).getPlaneType().compareToIgnoreCase(((PrivateFlight) f2).getPlaneType());
                }
                return 0; // If not both PrivateFlights, leave them in original order
            case 6: // Airfare (PassengerFlight Only)
                if (f1 instanceof PassengerFlight && f2 instanceof PassengerFlight) {
                    return Double.compare(f1.getAirfare(), f2.getAirfare());
                }
                return 0; // If not both PassengerFlights, leave them in original order
            case 7: // Flight Date (Applies to all flights)
                return f1.getFlightDate().compareTo(f2.getFlightDate());
            case 8: // Distance (Applies to all flights)
                return Double.compare(f1.getDistance(), f2.getDistance());
            case 9:
                // return f1.getDepartureTime().compareToIgnoreCase(f2.getDepartureTime()); // FROM EARLIEST TO LATEST
                return -f1.getDepartureTime().compareToIgnoreCase(f2.getDepartureTime()); // FROM LATEST TO EARLIEST

            default:
                throw new IllegalArgumentException("Invalid sorting option.");
        }
    }
}
