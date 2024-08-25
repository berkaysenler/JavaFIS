// Student Name: Berkay Senler 
// Student No: s8087440

/**
 * The FlightTest class is a console-based application that allows users
 * to input, manage, and sort a list of flights. It supports different 
 * types of flights, such as PassengerFlight, CargoFlight, and PrivateFlight, 
 * and provides functionality for sorting flights based on various attributes.
 * The class also includes methods for validating user input and handling
 * different flight-related operations.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import java.util.Date;
import java.util.Scanner;

public class FlightTest {
    public static void main(String[] args) throws Exception {

        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the number of flights and read the integer input
        int numberOfFlights = readInt(scanner, "Enter the number of flights: ");

        // Create an array of Flight objects
        Flight[] flights = new Flight[numberOfFlights];

        // Loop to get input for each flight
        for (int i = 0; i < numberOfFlights; i++) {
            System.out.println("Enter details for Flight " + (i + 1) + ":");

            // Get flight type and based on type, collect relevant information
            String flightType = readString(scanner, "Flight Type (Cargo/Passenger/Private): ");

            // Collect details based on the selected flight type,
            // handle specific attributes and creating objects
            if (flightType.equalsIgnoreCase("Cargo")) {
                String airlineName = readString(scanner, "Airline Name: ");
                String flightNumber = readIntStr(scanner, "Flight Number: ");
                String flightOrigin = readEquality(scanner, "Flight Origin: ", null);
                String flightDestination = readEquality(scanner, "Flight Destination: ", flightOrigin);
                String gateNumber = readIntStr(scanner, "Gate Number: ");
                String flightDate = readDate(scanner, "Flight Date (dd MMM yyyy): ");
                String departureTime = readTime(scanner, "Departure Time (HH:mm): ", null);
                String arrivalTime = readTime(scanner, "Arrival Time (HH:mm): ", departureTime);
                double flightDuration = readDouble(scanner, "Flight Duration (hours): ");
                double distance = readDouble(scanner, "Distance (km): ");
                String cargoType = readString(scanner, "Cargo Type: ");
                double cargoWeight = readDouble(scanner, "Cargo Weight (kg): ");
                flights[i] = new CargoFlight(airlineName, flightNumber, flightOrigin, flightDestination,
                        departureTime,
                        arrivalTime, flightDuration, distance, flightDate, cargoWeight, cargoType, gateNumber);
            } else if (flightType.equalsIgnoreCase("Passenger")) {
                String airlineName = readString(scanner, "Airline Name: ");
                String flightNumber = readIntStr(scanner, "Flight Number: ");
                String flightOrigin = readEquality(scanner, "Flight Origin: ", null);
                String flightDestination = readEquality(scanner, "Flight Destination: ", flightOrigin);
                String seatClass = readString(scanner, "Seat Class: ");
                String gateNumber = readIntStr(scanner, "Gate Number: ");
                double airfare = readDouble(scanner, "Airfare: ");
                String flightDate = readDate(scanner, "Flight Date (dd MMM yyyy): ");
                String departureTime = readTime(scanner, "Departure Time (HH:mm): ", null);
                String arrivalTime = readTime(scanner, "Arrival Time (HH:mm): ", departureTime);
                double flightDuration = readDouble(scanner, "Flight Duration (hours): ");
                int availableSeats = readInt(scanner, "Available Seats: ");
                double distance = readDouble(scanner, "Distance (km): ");
                boolean mealService = readBoolean(scanner, "Meal Service (yes/no): ");
                boolean inFlightEntertainment = readBoolean(scanner, "In-Flight Entertainment (yes/no): ");
                flights[i] = new PassengerFlight(airlineName, flightNumber, flightOrigin, flightDestination, airfare,
                        departureTime,
                        arrivalTime, flightDuration, distance, flightDate, gateNumber, availableSeats, seatClass,
                        mealService, inFlightEntertainment);
            } else if (flightType.equalsIgnoreCase("Private")) {
                String airlineName = readString(scanner, "Airline Name: ");
                String planeType = readString(scanner, "Plane Type : ");
                String ownership = readString(scanner, "Ownership: ");
                String passengerName = readString(scanner, "Passenger Name: ");
                String flightNumber = readIntStr(scanner, "Flight Number: ");
                String flightOrigin = readEquality(scanner, "Flight Origin: ", null);
                String flightDestination = readEquality(scanner, "Flight Destination: ", flightOrigin);
                String gateNumber = readIntStr(scanner, "Gate Number: ");
                String flightDate = readDate(scanner, "Flight Date (dd MMM yyyy): ");
                String departureTime = readTime(scanner, "Departure Time (HH:mm): ", null);
                String arrivalTime = readTime(scanner, "Arrival Time (HH:mm): ", departureTime);
                double flightDuration = readDouble(scanner, "Flight Duration (hours): ");
                double distance = readDouble(scanner, "Distance (km): ");
                boolean mealService = readBoolean(scanner, "Meal Service (yes/no): ");
                boolean inFlightEntertainment = readBoolean(scanner, "In-Flight Entertainment (yes/no): ");
                flights[i] = new PrivateFlight(airlineName, planeType, passengerName, ownership, flightNumber,
                        flightOrigin,
                        flightDestination, departureTime, arrivalTime, flightDuration, distance, flightDate, gateNumber,
                        mealService, inFlightEntertainment);
            }

            else {
                System.out
                        .println("Invalid flight type. Please enter one of these: 'Cargo' - 'Passenger' - 'Private'.");
                i--; // Decrement index to retry this entry
            }
        }

        // Display the details of all flights
        System.out.println("\nFlight Information:");
        System.out.println();
        System.out.println("================================================================================");
        System.out.println();
        for (Flight flight : flights) {
            System.out.println(flight);
            System.out.println();
        }
        System.out.println("================================================================================");

        // Loop for valid sorting option input
        int sortOption;
        while (true) {
            System.out.println("\nHow would you like to sort the flights?");
            System.out.println("Options: ");
            System.out.println("         1. Airline Name 2. Flight Number 3. Cargo Weight 4. Seat Class");
            System.out.println("         5. Plane Type 6. Airfare 7. Flight Date 8. Distance");
            System.out.println("         9. Departure Time");
            sortOption = readInt(scanner, "Choose an option (1-9): ");

            if (sortOption >= 1 && sortOption <= 9) {
                break;
            } else {
                System.out.println("Invalid option. Please choose a valid option between 1 and 9.");
            }
        }

        // Separate sortable and non-sortable flights based on the chosen option
        Flight[] sortableFlights;
        Flight[] nonSortableFlights;

        // Sorting cases for speacial conditions
        switch (sortOption) {
            case 3: // Sorting by Cargo Weight (CargoFlight Only)
                sortableFlights = Arrays.stream(flights)
                        .filter(f -> f instanceof CargoFlight)
                        .toArray(Flight[]::new);
                nonSortableFlights = Arrays.stream(flights)
                        .filter(f -> !(f instanceof CargoFlight))
                        .toArray(Flight[]::new);
                break;

            case 4: // Sorting by Seat Class (PassengerFlight Only)
                sortableFlights = Arrays.stream(flights)
                        .filter(f -> f instanceof PassengerFlight)
                        .toArray(Flight[]::new);
                nonSortableFlights = Arrays.stream(flights)
                        .filter(f -> !(f instanceof PassengerFlight))
                        .toArray(Flight[]::new);
                break;

            case 5: // Sorting by Plane Type (PrivateFlight Only)
                sortableFlights = Arrays.stream(flights)
                        .filter(f -> f instanceof PrivateFlight)
                        .toArray(Flight[]::new);
                nonSortableFlights = Arrays.stream(flights)
                        .filter(f -> !(f instanceof PrivateFlight))
                        .toArray(Flight[]::new);
                break;

            case 6: // Sorting by Airfare (PassengerFlight Only)
                sortableFlights = Arrays.stream(flights)
                        .filter(f -> f instanceof PassengerFlight)
                        .toArray(Flight[]::new);
                nonSortableFlights = Arrays.stream(flights)
                        .filter(f -> !(f instanceof PassengerFlight))
                        .toArray(Flight[]::new);
                break;

            default:
                sortableFlights = flights; // If sorting by common attribute, all flights are sortable
                nonSortableFlights = new Flight[0];
                break;

        }

        // Try sorting and catch any errors that occur
        try {
            if (sortableFlights.length > 1) {
                Arrays.sort(sortableFlights, new FlightComparator(sortOption));
            }

            System.out.println("\n" + getSortHeader(sortOption));
            System.out.println("================================================================================");

            // Display sorted flights
            for (Flight flight : sortableFlights) {
                System.out.println(flight);
                System.out.println();
            }

            // Display non-sortable flights
            if (nonSortableFlights.length > 0) {
                System.out.println();
                System.out.println(
                        "The following flights could not be sorted by the selected attribute and are displayed in default order:");
                System.out.println();
                System.out.println("================================================================================");
                for (Flight flight : nonSortableFlights) {
                    System.out.println();
                    System.out.println(flight);
                    System.out.println();
                }
            }
            // If an error occurs during sorting, catch the exception
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Displaying flights in default order.");
            System.out.println("================================================================================");

            // Print all the flights in their default order (without sorting)
            for (Flight flight : flights) {
                System.out.println(flight);
                System.out.println();
            }
        }

        System.out.println("================================================================================");
        scanner.close();

    }

    // Helper method to read an integer input with validation
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid integer.");
                continue;
            }
            try {
                int number = Integer.parseInt(input);
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Input number must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    // Helper method to read a string input with validation
    private static String readString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                if (!input.isEmpty() && input.matches("[a-zA-Z ]+")) {
                    return input.toUpperCase();
                } else {
                    throw new IllegalArgumentException(
                            "Input cannot be empty or contain numbers. Please enter a valid value.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Helper method to read a string with alphanumeric characters only
    private static String readIntStr(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                if (!input.isEmpty() && input.matches("[a-zA-Z0-9 ]+")) {
                    return input.toUpperCase();
                } else {
                    throw new IllegalArgumentException(
                            "Input cannot be empty or contain any special character. Please enter a valid value.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Helper method to read a double value with validation
    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty. Please enter a valid number.");
            }
            try {
                double number = Double.parseDouble(input);
                if (number >= 0) {
                    return number;
                } else {
                    throw new IllegalArgumentException("Value cannot be negative.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid input. Please enter a valid number.", e);
            }
        }
    }

    // Helper method to read a time value and ensure correct time format
    private static String readTime(Scanner scanner, String prompt, String previousTime) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                if (isValidTimeFormat(input)) {
                    if (previousTime == null || isValidTimeOrder(previousTime, input)) {
                        return input.toUpperCase();
                    } else {
                        throw new IllegalArgumentException("Departure time must be before arrival time.");
                    }
                } else {
                    throw new IllegalArgumentException(
                            "Invalid time format. Please enter a valid time in HH:mm format.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Check if the time format is HH:mm for 24-hour format
    private static boolean isValidTimeFormat(String time) {
        return time.matches("^([01][0-9]|2[0-3]):([0-5][0-9])$");
    }

    // Check if departure time is before arrival time
    private static boolean isValidTimeOrder(String departure, String arrival) {
        int depMinutes = convertToMinutes(departure);
        int arrMinutes = convertToMinutes(arrival);
        return depMinutes < arrMinutes;
    }

    // Convert time in HH:mm format to minutes since midnight
    private static int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    // Helper method to ensure two strings are not equal
    private static String readEquality(Scanner scanner, String prompt, String previousValue) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid value.");
                continue;
            } else if (!input.matches("[a-zA-Z ]+")) {
                System.out.println("Input must contain only alphabetic characters and spaces. Please try again.");
                continue;
            } else if (previousValue == null || !input.equalsIgnoreCase(previousValue)) {
                return input.toUpperCase();
            }

            else {
                System.out.println("Input cannot be the same as the previous value. Please enter a different value.");
            }
        }
    }

    // Helper method to read a date value with validation
    private static String readDate(Scanner scanner, String prompt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setLenient(false); // Ensures strict parsing

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                Date date = dateFormat.parse(input); // Validate the date format
                return dateFormat.format(date).toUpperCase(); // Return the formatted date
            } catch (ParseException e) {
                throw new IllegalArgumentException(
                        "Invalid date format. Please enter the date in 'dd MMMM yyyy' format.");
            }
        }
    }

    // Helper method to read a boolean value (yes/no)
    private static boolean readBoolean(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("no")) {
                return Boolean.parseBoolean(input.toUpperCase());
            } else {
                throw new IllegalArgumentException("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    // Helper method to generate a header based on the sorting option
    public static String getSortHeader(int sortOption) {
        switch (sortOption) {
            case 1:
                return "Sorted flights by Airline Name: ";
            case 2:
                return "Sorted flights by Flight Number: ";
            case 3:
                return "Sorted flights by Cargo Weight: ";
            case 4:
                return "Sorted flights by Seat Class: ";
            case 5:
                return "Sorted flights by Plane Type: ";
            case 6:
                return "Sorted flights by Airfare: ";
            case 7:
                return "Sorted flights by Flight Date: ";
            case 8:
                return "Sorted flights by Distance: ";
            case 9:
                return "Sorted flights by Departure Time: ";
            default:
                return "Unsorted default Flight Information: "; // Default header
        }
    }
}
