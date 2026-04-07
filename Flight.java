package bookingsystem;
import java.io.PrintWriter;

public class Flight {
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private int gateNumber;
    private String[][] seatMap;
    private int row;
    private int column;
    
    public Flight(String flightNumber, String departureCity, String arrivalCity, int gateNumber, int row, int column){
        this.flightNumber=flightNumber;
        this.departureCity=departureCity;
        this.arrivalCity=arrivalCity;
        this.gateNumber=gateNumber;
        this.row=row;
        this.column=column;
        seatMap = new String[row][column];
    }
    
    public String getFlightNumber() {return flightNumber;}
    public String getDepartureCity() {return departureCity;}
    public String getArrivalCity() {return arrivalCity;}
    public int getGateNumber() {return gateNumber;}
    public String[][] getSeatMap() {return seatMap;}
    
    public void setFlightNumber(String flightNumber){this.flightNumber=flightNumber;}
    public void setDepartureCity(String departureCity) {this.departureCity=departureCity;}
    public void setArrivalCity(String arrivalCity) {this.arrivalCity=arrivalCity;}
    public void setGateNumber(int gateNumber) {this.gateNumber=gateNumber;}
    
    public boolean bookSeat(int row, char column, String passengerID) {
        if(isSeatAvailable(row,column)){ //check if the seat exist
            seatMap[row-1][column-'A']=passengerID; //store passengerID in the seatMap array
            return true;
        }
        else
        return false; 
    }
    
    public boolean isSeatAvailable(int row, char column) {

        if(column-'A'<this.column&&seatMap[row][column-'A']==null){//seat is not reserved
            return true;
        }
        else
        return false;
    }
    
    @Override
    public String toString() {return "Flight{"+"flightNumber="+flightNumber+", departureCity="+departureCity+", arrivalCity="+arrivalCity+", gateNumber="+gateNumber+", seatMap="+seatMap+", row="+row+", column="+column+'}';}
    
    public void printSeatPlan(PrintWriter out) {
        
        out.println("Seat Plane for flight "+flightNumber+" is: \n"+"************************************");
        
        out.printf(" %-14s", "Row");               
        for(int i=0; i<seatMap[0].length; i++) {
            out.printf("%-13s", (char)(i+'a')+"");
        }
                        
        out.println();
        for(int i=0; i<seatMap.length; i++) {
            out.printf(" %-13d", i);
            for(int j = 0; j<seatMap[i].length; j++) {
                if (seatMap[i][j]==null)
                    out.printf("%-13s", "O");
                else
                    out.printf("%-13s", seatMap[i][j]);
                }
                out.println();
            }
        out.println();
        
    }
}
