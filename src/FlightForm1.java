// Student Name: Berkay Senler 
// Student No: s8087440

/**
 * The FlightForm1 class is a graphical user interface (GUI) for displaying,
 * searching, filtering, and adding flights. It allows users to interact with
 * a list of Flight objects and view details of different types of flights 
 * (Passenger, Cargo, Private) in a table format.
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Date;
// import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.text.NumberFormat;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;

public class FlightForm1 extends JFrame {

    // GUI components
    private JTable flightTable; // Table to display flight information
    private DefaultTableModel tableModel; // Model for the table data
    private JButton displayButton; // Button to display all flights
    private JTextField searchField; // Field for entering search queries
    private JButton searchButton; // Button to initiate the search
    private List<Flight> flights; // List of flights to be displayed and managed
    private JComboBox<String> searchCriteriaComboBox, flightTypeComboBox; // Dropdowns for search criteria and flight
                                                                          // type

    // Components for adding new flights
    private JPanel addFlightPanel; // Panel for adding new flight details
    private JTextField flightNumberField, airlineNameField, flightOriginField, flightDestinationField,
            departureTimeField, arrivalTimeField, airfareField, seatClassField, cargoTypeField, ownershipField;
    private JLabel airfareLabel, seatClassLabel, cargoTypeLabel, ownershipLabel;

    private JButton addFlightButton; // Button to add a new flight

    // Constructor to initialize the form with a list of flights
    public FlightForm1(List<Flight> flights) {
        this.flights = flights; // Store the list of flights
        initializeComponents(); // Initialize the GUI components
    }

    // Method to initialize all GUI components and set up the layout
    private void initializeComponents() {
        // Set up colors
        Color bgColor = new Color(36, 38, 39); // Background color
        Color btnColor = new Color(229, 175, 81); // Button color
        Color btnHoverColor = new Color(243, 228, 174); // Button hover color

        getContentPane().setBackground(bgColor); // Set background color
        setTitle("Flight Information"); // Set window title
        setSize(1420, 880); // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setLayout(null); // Set layout to null for absolute positioning

        // Initialize components
        initializeSearchCriteriaDropdown(btnColor, btnHoverColor); // Initialize search criteria dropdown
        initializeFlightTable(btnColor, btnHoverColor, bgColor); // Initialize flight table
        initializeDisplayButton(btnColor, btnHoverColor); // Initialize display button
        initializeSearchField(btnColor, btnHoverColor); // Initialize search field
        initializeAddFlightPanel(btnColor, btnHoverColor, bgColor); // Initialize add flight panel

        setVisible(true); // Make the window visible
    }

    // Method to initialize the dropdown for search criteria
    private void initializeSearchCriteriaDropdown(Color btnColor, Color btnHoverColor) {
        String[] searchOptions = { "Airline Name", "Flight Number", "Origin", "Destination" }; // Search options
        searchCriteriaComboBox = new JComboBox<>(searchOptions); // Create dropdown with search options

        searchCriteriaComboBox.setBounds(20, 20, 150, 30); // Set position and size
        searchCriteriaComboBox.setBackground(btnColor); // Set background color
        searchCriteriaComboBox.setForeground(Color.WHITE); // Set text color
        searchCriteriaComboBox.setBorder(BorderFactory.createEmptyBorder()); // Remove border

        add(searchCriteriaComboBox); // Add dropdown to the form
    }

    // Method to initialize the table for displaying flight information
    private void initializeFlightTable(Color btnColor, Color btnHoverColor, Color bgColor) {

        // Table column headers
        String[] columnNames = { "Flight Type", "Flight Number", "Airline Name", "Origin", "Destination",
                "Departure Time", "Arrival Time", "Airfare", "Seat class", "Cargo Type", "Ownership" };
        tableModel = new DefaultTableModel(columnNames, 0); // Initialize table model with column names
        flightTable = new JTable(tableModel); // Create table with the model

        flightTable.setBackground(bgColor); // Set background color
        flightTable.setForeground(btnHoverColor); // Set text color
        flightTable.setAutoCreateRowSorter(true); // Enable sorting by clicking on column headers
        flightTable.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Set font for the table

        JTableHeader header = flightTable.getTableHeader(); // Get the table header

        // Create and set a custom renderer for the header
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);

                // Set custom colors for the header
                label.setBackground(bgColor); // Set the background color for the header cells
                label.setForeground(Color.WHITE); // Set the text color for the header cells
                label.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Set the font style and size
                label.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Set border color

                // Align text in the center (optional)
                label.setHorizontalAlignment(SwingConstants.CENTER);

                return label;
            }
        });

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(flightTable);
        scrollPane.setBounds(20, 60, 1240, 400); // Set position and size of the scroll pane
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove border

        add(scrollPane); // Add the scroll pane (with the table) to the form

        /// Enhanced label with a bigger font, border, and background for sorting info
        JLabel sortingInfoLabel = new JLabel("Tip: Click on column headers to sort!"); // Label with sorting tip
        sortingInfoLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Set font
        sortingInfoLabel.setForeground(Color.WHITE); // Set text color
        sortingInfoLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        sortingInfoLabel.setOpaque(true); // Make the background color visible
        sortingInfoLabel.setBackground(btnColor); // Set background color

        // Set the size and position of the label
        sortingInfoLabel.setBounds(scrollPane.getX() + scrollPane.getWidth() - 300, scrollPane.getY() - 50, 300, 30);
        add(sortingInfoLabel); // Add sorting tip label to the form

        // Label for developer name (Berkay Senler)
        JLabel nameLabel = new JLabel("Developed by Berkay Senler");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        nameLabel.setOpaque(true); // Make the background color visible
        nameLabel.setBackground(btnColor); // Set background color

        // Set the size and position of the label
        nameLabel.setBounds(scrollPane.getX() + scrollPane.getWidth() - 300,
                scrollPane.getY() + 300 + scrollPane.getHeight() + 20, 300, 30);
        add(nameLabel);

    }

    // Method to initialize the display button
    private void initializeDisplayButton(Color btnColor, Color btnHoverColor) {
        displayButton = new JButton("Display Flights");
        displayButton.setBounds(550, 480, 200, 30);
        displayButton.setBackground(btnColor);
        displayButton.setForeground(Color.WHITE);
        displayButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        displayButton.setFocusPainted(false);
        displayButton.setBorder(BorderFactory.createEmptyBorder());
        displayButton.addMouseListener(new java.awt.event.MouseAdapter() { // Add hover effects
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                displayButton.setBackground(btnHoverColor);
                displayButton.repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                displayButton.setBackground(btnColor);
                displayButton.repaint();
            }

        });
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable(flights); // Populate the table with flight data when button is clicked
            }

        });
        add(displayButton);
    }

    // Method to initialize the search field and search button
    private void initializeSearchField(Color btnColor, Color btnHoverColor) {

        searchField = new JTextField();
        searchField.setBounds(180, 20, 200, 30);
        searchField.setBackground(new Color(233, 236, 239));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(400, 20, 100, 30);
        searchButton.setBackground(btnColor);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(btnColor);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterFlights(); // Filter the flights based on the search query when button is clicked
            }
        });

        add(searchButton);
    }

    // Method to filter flights based on the search criteria and query
    private void filterFlights() {
        String searchText = searchField.getText().toLowerCase(); // Get the search query and convert to lowercase
        String selectedCriteria = (String) searchCriteriaComboBox.getSelectedItem(); // Get the selected search criteria

        // Filter the flights based on the selected criteria
        List<Flight> filteredFlights = flights.stream().filter(flight -> {
            switch (selectedCriteria) {
                case "Airline Name":
                    return flight.getAirlineName().toLowerCase().contains(searchText);
                case "Flight Number":
                    return flight.getFlightNumber().toLowerCase().contains(searchText);
                case "Origin":
                    return flight.getFlightOrigin().toLowerCase().contains(searchText);
                case "Destination":
                    return flight.getFlightDestination().toLowerCase().contains(searchText);
                default:
                    return false; // If no valid criteria, return false (no filtering)
            }
        }).collect(Collectors.toList()); // Collect the filtered flights into a list

        populateTable(filteredFlights); // Populate the table with the filtered flights
    }

    // Method to populate the table with flight data
    private void populateTable(List<Flight> flightsToDisplay) {
        tableModel.setRowCount(0); // Clear existing rows
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        // Loop through the flights and add them to the table
        for (Flight flight : flightsToDisplay) {
            String flightType; // Store the type of flight
            String ownership = "";
            String cargoType = "";
            String formattedAirfare = currencyFormatter.format(flight.getAirfare());

            if (flight instanceof CargoFlight) {
                flightType = "CargoFlight";
                cargoType = ((CargoFlight) flight).getCargoType();
            } else if (flight instanceof PassengerFlight) {
                flightType = "PassengerFlight";
            } else if (flight instanceof PrivateFlight) {
                flightType = "PrivateFlight";
                ownership = ((PrivateFlight) flight).getOwnership();
            } else {
                flightType = "Unknown";
            }

            // Create a row with flight data
            Object[] rowData = {
                    flightType,
                    flight.getFlightNumber(),
                    flight.getAirlineName(),
                    flight.getFlightOrigin(),
                    flight.getFlightDestination(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    formattedAirfare,
                    flight.getSeatClass(),
                    cargoType,
                    ownership
            };
            tableModel.addRow(rowData); // Add the row to the table model
        }
    }

    // Method to initialize the panel for adding new flights
    private void initializeAddFlightPanel(Color btnColor, Color btnHoverColor, Color bgColor) {
        addFlightPanel = new JPanel();
        addFlightPanel.setLayout(null); // Set layout to null for absolute positioning

        addFlightPanel.setBounds(20, 520, 1240, 250);
        addFlightPanel.setBackground(new Color(238, 238, 238));

        // Label and dropdown for selecting flight type
        JLabel flightTypeLabel = new JLabel("Flight Type:");
        flightTypeLabel.setBounds(20, 170, 120, 25);
        addFlightPanel.add(flightTypeLabel);
        flightTypeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        String[] flightTypes = { "Passenger", "Cargo", "Private" }; // Flight type options
        flightTypeComboBox = new JComboBox<>(flightTypes); // Create dropdown with flight type options
        flightTypeComboBox.setBounds(122, 170, 150, 25);
        flightTypeComboBox.setForeground(Color.WHITE);
        flightTypeComboBox.setBackground(btnColor);
        addFlightPanel.add(flightTypeComboBox); // Add dropdown to the panel
        flightTypeComboBox.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Adjust positions to ensure better alignment for the input fields
        flightNumberField = createLabelAndTextField(addFlightPanel, "Flight Number:", 20, 60, 100);
        airlineNameField = createLabelAndTextField(addFlightPanel, "Airline Name:", 320, 60, 100);
        flightOriginField = createLabelAndTextField(addFlightPanel, "Origin:", 620, 60, 100);
        flightDestinationField = createLabelAndTextField(addFlightPanel, "Destination:", 920, 60, 100);
        departureTimeField = createLabelAndTextField(addFlightPanel, "Departure Time:", 20, 100, 100);
        arrivalTimeField = createLabelAndTextField(addFlightPanel, "Arrival Time:", 320, 100, 100);

        // Passenger flight fields
        airfareLabel = new JLabel("Airfare:");
        airfareLabel.setBounds(620, 100, 100, 25);
        addFlightPanel.add(airfareLabel);

        airfareField = new JTextField();
        airfareField.setBounds(720, 100, 150, 25);
        addFlightPanel.add(airfareField);

        seatClassLabel = new JLabel("Seat Class:");
        seatClassLabel.setBounds(920, 100, 100, 25);
        addFlightPanel.add(seatClassLabel);

        seatClassField = new JTextField();
        seatClassField.setBounds(1020, 100, 150, 25);
        addFlightPanel.add(seatClassField);

        // Cargo flight field and label
        cargoTypeLabel = new JLabel("Cargo Type:");
        cargoTypeLabel.setBounds(620, 100, 100, 25); // Same as airfare, will be shown/hidden dynamically
        addFlightPanel.add(cargoTypeLabel);

        cargoTypeField = new JTextField();
        cargoTypeField.setBounds(720, 100, 150, 25);
        addFlightPanel.add(cargoTypeField);

        // Private flight field and label
        ownershipLabel = new JLabel("Ownership:");
        ownershipLabel.setBounds(620, 100, 100, 25); // Same as airfare, will be shown/hidden dynamically
        addFlightPanel.add(ownershipLabel);

        ownershipField = new JTextField();
        ownershipField.setBounds(720, 100, 150, 25);
        addFlightPanel.add(ownershipField);

        // Button to add a new flight
        addFlightButton = new JButton("Add Flight");
        addFlightButton.setBounds(550, 200, 150, 30);
        addFlightButton.setBackground(btnColor);
        addFlightButton.setForeground(Color.WHITE);
        addFlightButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addFlightButton.setFocusPainted(false);
        addFlightButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addFlightButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addFlightButton.setBackground(btnHoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addFlightButton.setBackground(btnColor);
            }
        });
        addFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewFlight(); // Add a new flight to the list and table when button is clicked
            }
        });

        addFlightPanel.add(addFlightButton); // Add the add flight button to the panel
        add(addFlightPanel); // Add the add flight panel to the form

        // Add ActionListener to the flightTypeComboBox to change input fields
        // visibility
        flightTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInputFieldsVisibility();
            }
        });

        // Set initial visibility based on default selection
        updateInputFieldsVisibility();
    }

    // Helper method to create labels and text fields in the add flight panel
    private JTextField createLabelAndTextField(JPanel panel, String labelText, int x, int y, int labelWidth) {
        JLabel label = new JLabel(labelText); // Create label with specified text
        label.setBounds(x, y, labelWidth, 25); // Set position and size
        panel.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(x + labelWidth + 1, y, 150, 25);
        panel.add(textField);

        return textField;
    }

    // Method to update the visibility of input fields based on selected flight type
    private void updateInputFieldsVisibility() {
        String selectedFlightType = (String) flightTypeComboBox.getSelectedItem(); // Get selected flight type

        // Hide all fields initially
        airfareLabel.setVisible(false);
        airfareField.setVisible(false);
        seatClassLabel.setVisible(false);
        seatClassField.setVisible(false);
        cargoTypeLabel.setVisible(false);
        cargoTypeField.setVisible(false);
        ownershipLabel.setVisible(false);
        ownershipField.setVisible(false);

        // Show relevant fields based on flight type
        if ("Passenger".equals(selectedFlightType)) {
            airfareLabel.setVisible(true);
            airfareField.setVisible(true);
            seatClassLabel.setVisible(true);
            seatClassField.setVisible(true);
        } else if ("Cargo".equals(selectedFlightType)) {
            cargoTypeLabel.setVisible(true);
            cargoTypeField.setVisible(true);
        } else if ("Private".equals(selectedFlightType)) {
            ownershipLabel.setVisible(true);
            ownershipField.setVisible(true);
        }

        // Ensure updated visibility is reflected on the panel
        addFlightPanel.repaint();
    }

    // Method to add a new flight based on user input
    private void addNewFlight() {
        try {
            String flightNumber = readIntStr(flightNumberField, "Flight Number");
            String airlineName = readString(airlineNameField, "Airline Name");
            String flightOrigin = readString(flightOriginField, "Origin");
            String flightDestination = readString(flightDestinationField, "Destination");

            // Ensure origin and destination are not the same
            if (flightOrigin.equalsIgnoreCase(flightDestination)) {
                JOptionPane.showMessageDialog(this, "Origin and Destination cannot be the same.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                flightDestinationField.requestFocus(); // Focus on the destination field if error
                return;
            }

            String departureTime = readTime(departureTimeField, "Departure Time", null);
            String arrivalTime = readTime(arrivalTimeField, "Arrival Time", departureTime);

            String selectedFlightType = (String) flightTypeComboBox.getSelectedItem();

            if ("Passenger".equals(selectedFlightType)) {
                double airfare = readDouble(airfareField, "Airfare");
                String seatClass = readString(seatClassField, "Seat Class");

                PassengerFlight newFlight = new PassengerFlight(airlineName, flightNumber, flightOrigin,
                        flightDestination,
                        airfare, departureTime, arrivalTime, 0, 0, "", "", 0, seatClass, true, true);
                flights.add(newFlight);

            } else if ("Cargo".equals(selectedFlightType)) {
                String cargoType = readString(cargoTypeField, "Cargo Type");

                CargoFlight newFlight = new CargoFlight(airlineName, flightNumber, flightOrigin, flightDestination,
                        departureTime, arrivalTime, 0, 0, "", 0, cargoType, "");
                flights.add(newFlight);

            } else if ("Private".equals(selectedFlightType)) {
                String ownership = readString(ownershipField, "Ownership");

                PrivateFlight newFlight = new PrivateFlight(airlineName, "", "", ownership, flightNumber,
                        flightOrigin, flightDestination, departureTime, arrivalTime, 0, 0, "", "", true, true);
                flights.add(newFlight);
            }

            populateTable(flights);
            clearInputFields();

        } catch (IllegalArgumentException e) {
            // The error is already handled above, no need it again

        }
    }

    // Method to clear all input fields after adding a flight
    private void clearInputFields() {
        flightNumberField.setText("");
        airlineNameField.setText("");
        flightOriginField.setText("");
        flightDestinationField.setText("");
        departureTimeField.setText("");
        arrivalTimeField.setText("");
        airfareField.setText("");
        seatClassField.setText("");
        cargoTypeField.setText("");
        ownershipField.setText("");
    }

    // Main method to run the form
    public static void main(String[] args) {
        // Create a list of flights for testing purposes
        List<Flight> flights = new ArrayList<>(Arrays.asList(
                new PassengerFlight("Turkish Airlines", "AA100", "Istanbul", "Melbourne", 4500.0, "06:00",
                        "09:15", 5, 2475, "01 Nov 2024",
                        "B32", 120, "First Class", true, true),
                new CargoFlight("FedEx", "FX705", "Florida", "Chicago", "02:00", "04:15", 2, 489, "02 Nov 2024",
                        5000,
                        "General Goods", "D15"),
                new PrivateFlight("NetJets", "Gulfstream G650", "Elon Musk", "Owned", "PJ789", "Sydney",
                        "Auckland", "07:00",
                        "07:55", 1, 184, "03 Nov 2024", "G45", true, true),
                new PassengerFlight("Emirates", "BA283", "London", "Los Angeles", 750.0,
                        "11:30", "15:00", 11, 5456, "04 Nov 2024",
                        "C25", 150, "Business", true, false),
                new CargoFlight("DHL", "DHL913", "Tokyo", "Hong Kong", "20:30", "12:45", 16, 8056,
                        "05 Nov 2024", 10000,
                        "Electronics", "F22")));

        new FlightForm1(flights);
    }

    /*
     * HELPER METHODS TO READ AND VALIDATE USER INPUTS
     */

    // Method to read a string input (alphabetic characters only)
    private String readString(JTextField textField, String fieldName) {
        String input = textField.getText().trim();
        if (!input.isEmpty() && input.matches("[a-zA-Z ]+")) {
            return input.toUpperCase();
        } else {
            JOptionPane.showMessageDialog(this,
                    fieldName + " cannot be empty or contain numbers. Please enter a valid value.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            textField.requestFocus();
            throw new IllegalArgumentException(fieldName + " is invalid.");
        }
    }

    // Method to read a string input (alphanumeric characters only)
    private String readIntStr(JTextField textField, String fieldName) {
        String input = textField.getText().trim();
        if (!input.isEmpty() && input.matches("[a-zA-Z0-9 ]+")) {
            return input.toUpperCase();
        } else {
            JOptionPane.showMessageDialog(this,
                    fieldName + " cannot be empty or contain any special characters. Please enter a valid value.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            textField.requestFocus();
            throw new IllegalArgumentException(fieldName + " is invalid.");
        }
    }

    // Method to read a double input (numeric only)
    private double readDouble(JTextField textField, String fieldName) {
        String input = textField.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, fieldName + " cannot be empty. Please enter a valid number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            textField.requestFocus();
            throw new IllegalArgumentException(fieldName + " is empty.");
        }
        try {
            double number = Double.parseDouble(input);
            if (number >= 0) {
                return number;
            } else {
                JOptionPane.showMessageDialog(this, fieldName + " cannot be negative.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                textField.requestFocus();
                throw new IllegalArgumentException(fieldName + " is negative.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input for " + fieldName + ". Please enter a valid number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            textField.requestFocus();
            throw new IllegalArgumentException("Invalid " + fieldName + ".");
        }
    }

    // Method to read a time input in HH:mm format
    private String readTime(JTextField textField, String fieldName, String previousTime) {
        String input = textField.getText().trim();
        if (isValidTimeFormat(input)) {
            if (previousTime == null || isValidTimeOrder(previousTime, input)) {
                return input.toUpperCase();
            } else {
                JOptionPane.showMessageDialog(this, "Departure time must be before arrival time.", "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                textField.requestFocus();
                throw new IllegalArgumentException("Invalid time order.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid time format for " + fieldName + ". Please use HH:mm format.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            textField.requestFocus();
            throw new IllegalArgumentException("Invalid time format.");
        }
    }

    // Helper method to check if a time is in the HH:mm format
    private boolean isValidTimeFormat(String time) {
        return time.matches("^([01][0-9]|2[0-3]):([0-5][0-9])$");
    }

    // Helper method to check if the departure time is before the arrival time
    private boolean isValidTimeOrder(String departure, String arrival) {
        int depMinutes = convertToMinutes(departure);
        int arrMinutes = convertToMinutes(arrival);
        return depMinutes < arrMinutes;
    }

    // Helper method to convert time in HH:mm format to minutes since midnight
    private int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

}
