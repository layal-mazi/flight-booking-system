# flight-booking-system
A Java-based flight reservation system implementing OOP principles to manage flights, passengers, and ticketing. It features automated seat mapping, dynamic pricing logic (including regional discounts and VAT), and full file-based command processing.

Flight Booking System 
A robust Java-based flight reservation system developed as part of my Programming II course. This project demonstrates the application of Object-Oriented Programming (OOP) principles, file handling, and complex logic implementation.

Key Features
Dynamic Management: Add flights and register passengers via input files.

Seat Booking: Intelligent seat allocation with availability checks and seat map visualization.

Pricing Engine: Automated ticket price calculation including:

Hajj Discount: Special 20% discount for flights arriving in Jeddah (JED).

Class Upgrades: Price multipliers for Business and First Class.

Taxation: Automatic 15% VAT application.

Invoice Generation: Detailed invoices for individual tickets or entire flights.

File-Based Processing: Processes commands from inputCommands.txt and generates a comprehensive output.txt.

Technical Stack
Language: Java

Concepts: Object-Oriented Programming (Encapsulation, Arrays of Objects, Constructor Overloading), File I/O (Scanner, PrintWriter).

Project Structure
BookingSystem.java: The main driver class that handles command processing.

Flight.java: Manages flight details and seat map logic.

Passenger.java: Stores passenger identity information.

Ticket.java: Handles reservation details and the pricing algorithm.

How to Run
Clone the repository.

Ensure you have flight_passenger.txt and inputCommands.txt in the root directory.

Compile and run BookingSystem.java.

Check output.txt for the generated results and flight seat plans.

Example Output (Seat Plan)
The system generates a visual representation of the aircraft seating:
Row           a            b            c            d            e            f            
 0            O            O            O            O            O            O            
...
 9            X12345       O            O            O            O            O
 (O = Available, Passenger ID = Reserved)
