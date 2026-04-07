package bookingsystem;


public class Ticket {
    private Flight flight;
    private Passenger passenger;
    private int seatRow;
    private String seatNumber;
    private String classType;
    private int reservationConfirmationNumber;
    private static int reservationNumber=100;
    
    public Ticket(Flight flight, Passenger passenger, String seatNumber,int seatRow, String classType){
        this.flight=flight;
        this.passenger=passenger;
        this.seatNumber=seatNumber;
        this.seatRow = seatRow;
        this.classType=classType;
        reservationConfirmationNumber=reservationNumber++;
    }
    
    public Passenger getPassenger() {return passenger;}
    public Flight getFlight() {return flight;}
    public String getSeatNumber() {return seatNumber;}
    public int getSeatRow() {return seatRow;}
    public String getClassType() {return classType;}
    public int getReservationConfirmationNumber(){return reservationConfirmationNumber;}
    
    public void setFlight(Flight flight) {this.flight=flight;}
    public void setPassenger(Passenger passenger) {this.passenger=passenger;}
    public void setSeatNumber(String seatNumber) {this.seatNumber=seatNumber;}
    public void setSeatRow(int seatRow) {this.seatRow=seatRow;}
    public void setClassType(String classType) {this.classType=classType;}

    
    public double ticketPrice() {
        
        double tPrice=2000; //each ticket price

        if (flight.getArrivalCity().equals("JED")) { //Hajj discount
            tPrice=tPrice-(tPrice*0.2);
        }
        
        if (classType.equals("FirstClass")) { //add 400%
            tPrice=tPrice+(tPrice*4);
        }
        else if (classType.equals("BusinessClass")) { //add 200%
            tPrice=tPrice+(tPrice*2);
        }
        //Economy Class will not change
        
        return tPrice*1.15; //VAT 15%
    } 
    
    @Override
    public String toString() {return "Reservation Confirmation Number= "+reservationConfirmationNumber+", Flight Number="+flight.getFlightNumber()+", Passenger Name= "+passenger.getName()+", Seat Number= "+seatRow+seatNumber+", classType= "+classType;}
}
