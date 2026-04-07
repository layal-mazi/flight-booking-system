
package bookingsystem;


public class Passenger {
    private String passportNumber;
    private String name;
    

    public Passenger(String passportNumber, String name){
        this.passportNumber=passportNumber;
        this.name=name;
    }
    
    public String getPassportNumber() {return passportNumber;};
    public String getName() {return name;}
    
    public void setPassportNumber(String passportNumber){ this.passportNumber=passportNumber;}
    public void setName(String name) {this.name=name;}
    
    public void updateDetails(String newName) {name=newName;}
    
    @Override
    public String toString() {return "Passenger{"+"passportNumber="+passportNumber+", name="+name+'}';}
    
}
