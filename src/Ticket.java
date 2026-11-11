public class Ticket
{
    private String ticketID;
    private String ticketType;
    private double ticketPrice;
    private int seatNum;
    private String custName;
    private String custPhoneNum;
    private String custIC;
    private int custAge;
    private int ticketQuantity;
    
    public Ticket()
    {
        ticketID=" ";
        ticketType=" ";
        ticketPrice= 0.0;
        seatNum=0;
        custName=" ";
        custPhoneNum=" ";
        custIC=" ";
        custAge=0;
        ticketQuantity=0;
    }
    
    public Ticket(String ticketID, String tickType, double tickPrice, int seatNum, String custName, String custPhoneNum, String custIC, int custAge, int ticketQuantity)
    {
        this.ticketID=ticketID;
        this.ticketType=ticketType;
        this.ticketPrice=ticketPrice;
        this.seatNum=seatNum;
        this.custName=custName;
        this.custPhoneNum=custPhoneNum;
        this.custIC=custIC;
        this.custAge=custAge;
        this.ticketQuantity=ticketQuantity;
    }
    
    //setter
    public void setTicketID(String tID){ticketID=tID;}
    public void setTicketType(String tType){ticketType=tType;}
    public void setTicketPrice(double tPrice){ticketPrice=tPrice;}
    public void setSeatNum(int sNum){seatNum=sNum;}
    public void setCustName(String cName){custName=cName;}
    public void setCustPhoneNum(String cNum){custPhoneNum=cNum;}
    public void setCustIC(String cIC){custIC=cIC;}
    public void setCustAge(int cAge){custAge=cAge;}
    public void setTicketQuantity(int tQuantity){ticketQuantity=tQuantity;}
    
    //getter
    public String getTicketID(){return ticketID;}
    public String getTicketType(){return ticketType;}
    public double getTicketPrice(){return ticketPrice;}
    public int getSeatNum(){return seatNum;}
    public String getCustName(){return custName;}
    public String getCustPhoneNum(){return custPhoneNum;}
    public String getCustIC(){return custIC;}
    public int getCustAge(){return custAge;}
    public int getTicketQuantity(){return ticketQuantity;}
    
    
    //calculate total price method
    double totalPrice=0.00;
    public double calcTotalPrice()
    {
        if (ticketType.equalsIgnoreCase("Standard Ticket"))
        {
            totalPrice=150.00*ticketQuantity;                            
        }
        else if (ticketType.equalsIgnoreCase("Premium Ticket"))
        {
            totalPrice=200.00*ticketQuantity;
        }
        else if (ticketType.equalsIgnoreCase("VIP Ticket"))
        {
            totalPrice=300.00*ticketQuantity;
        }
        return totalPrice;
    }
    
    public String toString() {
        return "Ticket ID: " + ticketID +
                "\nTicket Type: " + ticketType +
                "\nTicket Price: " + ticketPrice +
                "\nSeat Number: " + seatNum +
                "\nCustomer Name: " + custName +
                "\nCustomer Phone Number: " + custPhoneNum +
                "\nCustomer IC: " + custIC +
                "\nCustomer Age: " + custAge +
                "\nTicket Quantity: " + ticketQuantity;
    }
}