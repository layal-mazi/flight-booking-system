package bookingsystem;
import java.util.*;
import java.io.*;

public class BookingSystem {
    static PrintWriter out=null;

    public static void main(String[] args) throws FileNotFoundException{
        
        File input = new File("inputCommands.txt");
        File inputPF = new File("flight_passenger.txt");
        File output = new File("output.txt");
        
        if(!input.exists()||!inputPF.exists()){
            System.out.println("The input file does not exist!!");
            System.exit(0);
        }
        
        Scanner in = new Scanner(input);
        Scanner inPF = new Scanner(inputPF);
        
        out = new PrintWriter(output);
        
        final int MAX_ticket=100; //assuming the maximum number of tickets is 100
        
        int numOfFlights=inPF.nextInt();
        int numOfPass=inPF.nextInt();
        
        Passenger[] passengers=new Passenger[numOfPass];
        Flight[] flights=new Flight[numOfFlights];
        Ticket[] tickets=new Ticket[MAX_ticket];
        
        int fCounter=0,pCounter=0,tCounter=0;
        
        //read from flight_passenger file
        while(inPF.hasNext()){
            switch(inPF.next()){ 
                case "AddFlight":{ 
                    String flightNum = inPF.next();
                    String flightDepCity = inPF.next();
                    String flightArrCity = inPF.next();
                    int gateNum = inPF.nextInt();
                    int r =inPF.nextInt();
                    int c =inPF.nextInt();
                    
                    Flight flight=new Flight(flightNum,flightDepCity,flightArrCity,gateNum,r,c); //create flight object
                    flights[fCounter]=flight; //store the flight object in the array 
                    fCounter++; 
                    
                    out.println("Flight "+flightNum+" added successfully");
                    break;
                }
                
                case "AddPassenger":{
                    String passNum = inPF.next();
                    String passName = inPF.next();
                    
                    Passenger passenger=new Passenger(passNum,passName); //create passenger object
                    passengers[pCounter]=passenger; //store the passenger object in the array 
                    pCounter++;
                    
                    out.println("Passenger "+passName+" added successfully");
                    break;
                }      
            }
        }
        
        //read from inputCommands file
        while(in.hasNext()){
            switch(in.next()){
                case "BookTicket":{ 
                    out.println("\n********************BookTicket*************************\n");
                    
                    String passengerPassportNum=in.next();
                    String flightNum=in.next();
                    int seatRow=in.nextInt();
                    String seatColumn=in.next();
                    String seatClass=in.next();
                    
                    
                    bookTicket(tickets, tCounter++, passengers, passengerPassportNum, flights, flightNum, seatRow, seatColumn, seatClass);
                    break;   
                } 
                
                case "GenerateInvoice":{
                    out.println("\n*********************Generate Ticket Invoice************************** \n");
                    int reservationNumber=in.nextInt();
                    int ticketIndex=SearchTicket(tickets, reservationNumber);
                    
                    GenerateInvoice(tickets, ticketIndex, reservationNumber);
                    break;
                    
                }
                
                case "GenerateIFlightnvoice":{
                    
                    out.println("\n*********************Generate Flight Invoice************************** \n");
                    
                    String flightNum=in.next();

                    int flightIndex=searchFlight(flights,flightNum);
                    
                    GenerateIFlightnvoice(tickets, flightIndex, flights, flightNum);
                    break;
                }
                
            }
        }
        
        inPF.close();
        in.close();
        out.flush();
        out.close();
        
    }
        
        
    
    
    public static void GenerateIFlightnvoice(Ticket[] tickets,int fIndex,Flight[] flights, String flightnumber){
        
        if(fIndex==-1){
            out.println("Flight Not Found or No Ticket booked for this flight");
        }
        else{
            flights[fIndex].printSeatPlan(out); //print seatPlan
            String[][] seatMap=flights[fIndex].getSeatMap(); 
            
            double totalInvoiveNumber=0;
                        
            for (int i=0;i<seatMap.length;i++) { //row
                for (int j=0;j<seatMap[i].length;j++) { //column
                    if (seatMap[i][j] != null){ 
                        int ticketIndex=-1;
                        for (int x = 0; x<tickets.length; x++){
                            if (tickets[x]!=null&&tickets[x].getPassenger().getPassportNumber().equals(seatMap[i][j])&&tickets[x].getFlight().getFlightNumber().equals(flightnumber)){
                                ticketIndex=x;
                                break;
                            }
                        }
                        out.println("Ticket Information: ");
                        out.println(tickets[ticketIndex].toString());
                                
                        totalInvoiveNumber+=tickets[ticketIndex].ticketPrice();
                    }
                }
            }
            out.println("\nTotal Invoice price ="+String.format("%.1f",totalInvoiveNumber)); //print the total with one digit after the decimal point
            
        }
    }
    
    public static void GenerateInvoice(Ticket[] tickets,int tIndex, int Res){
        if(tIndex==-1){ //ticket not found
            out.println("Reservation Number is not available");
        }
        else{ //found the ticket
            out.println("Ticket Information: ");
            out.println(tickets[tIndex].toString());
            out.printf("Total ticket price = %.1f\n", tickets[tIndex].ticketPrice());
        }
    }
    
    public static int SearchTicket(Ticket[] tickets, int resNum){
        
        for(int i=0; i<tickets.length; i++){
            if (tickets[i]!=null&&tickets[i].getReservationConfirmationNumber()==resNum){//check if the ticket exists
                return i; //index
            }
        }
        return -1; //not found
    }
    
    public static void bookTicket(Ticket[] tickets,int tIndex, Passenger[] passengers, String passPort,Flight[] flights,String flightNumber,int seatRow,String seatcol, String classType){
        
                    int pIndex=searchPassenger(passengers, passPort); 
                    if(pIndex==-1){ //the passenger does not exist
                            out.println("Passenger with Passport number "+passPort+" is not Registered");
                    }
                    else{ //the passenger exists
                        int fIndex=searchFlight(flights,flightNumber);  
                        
                        if(fIndex==-1){ //if the flight does not exist
                            out.println("Flight "+flightNumber+" Not Found");
                        }
                        else{ // the flight exist
                            
                            if(flights[fIndex].isSeatAvailable(seatRow,seatcol.charAt(0))==false){ //the seat is not available
                                out.println("Seat "+seatRow+seatcol+" is already Reserved Or Not found");
                            }
                            else{ //the seat is availabe
                                        Ticket ticket=new Ticket(flights[fIndex], passengers[pIndex],seatcol, seatRow, classType); //create ticket object
                                        tickets[tIndex]=ticket; ////store the ticket object in the array of tickets 
                                        flights[fIndex].bookSeat(seatRow, seatcol.charAt(0), passPort); //store the passport number in the seatMap array
                                        out.println("Seat booked successfully.");
                                        out.println("Ticket Information: ");
                                        out.println(ticket.toString());
                            }

                        }
                    }
        
    }
    
    public static int searchPassenger(Passenger[] passengers, String passPortNum){
        
        for (int i=0; i<passengers.length; i++) {
            if (passengers[i].getPassportNumber().equals(passPortNum)&&passengers[i]!=null){ //check if the passenger exists
                return i;
            }
        }
        return -1; //not found
    }
    
    public static int searchFlight(Flight[] flights,String flightNum){

        
        for (int i = 0; i<flights.length; i++) {
            if (flights[i].getFlightNumber().equals(flightNum)&&flights[i]!=null){ //check if the flight exists
                return i;
            }
        }
        
        return -1; //not found
    }
}
