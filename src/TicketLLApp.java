import java.util.*;
import java.io.*;

public class TicketLLApp
{
    public static void main(String args[])throws IOException
    {
        Scanner inText=new Scanner(System.in);
        Scanner inNum=new Scanner(System.in);

        LinkedList ticketList=new LinkedList();

        Ticket tick;
        
        try
        {
            FileReader fr=new FileReader("input file csc248.txt");
            BufferedReader br=new BufferedReader(fr);
            
            tick=new Ticket();
            String s=null;
            
            /*String ticketID;
            String ticketType;
            double ticketPrice;
            int seatNum;
            String custName;
            String custPhoneNum;
            String custIC;
            int custAge;
            int ticketQuantity; */
            
            int count=0;
            s=br.readLine();
            while (s!=null)
            {
                StringTokenizer st=new StringTokenizer(s,"*");
                String ticketID=st.nextToken();
                String ticketType=st.nextToken();
                double ticketPrice=Double.parseDouble(st.nextToken());
                int seatNum=Integer.parseInt(st.nextToken());
                String custName=st.nextToken();
                String custPhoneNum=st.nextToken();
                String custIC=st.nextToken();
                int custAge=Integer.parseInt(st.nextToken());
                int ticketQuantity=Integer.parseInt(st.nextToken());
                
                tick=new Ticket(ticketID,ticketType,ticketPrice,seatNum,custName,custPhoneNum,custIC,custAge,ticketQuantity);
                ticketList.addLast(tick);
                
                s=br.readLine();
            }
        }
        catch(EOFException eof)
        {
            System.out.println("Problem: "+eof.getMessage());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem: "+e.getMessage());
        }
        catch(IOException ioe)
        {
            System.out.println("Problem: "+ioe.getMessage());
        }
        
        
        boolean status=true;
        while(status==true)
        {
            System.out.println("Welcome to Ticketing System :D");
            System.out.println("Please enter the desired choice:");
            System.out.println("[1] - INSERT NEW DATA");
            System.out.println("[2] - REMOVE EXISTING DATA");
            System.out.println("[3] - DISPLAY DATA");
            System.out.println("[4] - UPDATE DATA");
            System.out.println("[5] - SEARCH DATA");
            System.out.println("[6] - EXIT");
            
            
            int choice=inNum.nextInt();
            if(choice==1)   // Inserting new data     
            {
                String ans="y";
                tick=(Ticket)ticketList.getFirst();
                while (ans.equalsIgnoreCase("y"))
                {
                    System.out.println("Enter the data for the new customer:");
                
                    System.out.println("Enter the Ticket ID:");
                    String ticketID=inText.nextLine();
                    System.out.println("Enter the Ticket Type:");
                    String ticketType=inText.nextLine();
                    double fixedticketPrice=0.0;
                    if (ticketType.equalsIgnoreCase("VIP Ticket"))
                                    {
                                        fixedticketPrice=300.00;
                                    }
                    else if (ticketType.equalsIgnoreCase("Premium Ticket"))
                                    {
                                         fixedticketPrice=200.00;
                                    }
                    else if (ticketType.equalsIgnoreCase("Standard Ticket"))
                                    {
                                        fixedticketPrice=150.00;
                                    }
                    double ticketPrice=fixedticketPrice;
                    System.out.println("Enter the seat number:");
                    int seatNum=inNum.nextInt();
                    System.out.println("Enter the customer name:");
                    String custName=inText.nextLine();
                    System.out.println("Enter the customer phone number:");
                    String custPhoneNum=inText.nextLine();
                    System.out.println("Enter the customer IC number:");
                    String custIC=inText.nextLine();
                    System.out.println("Enter the customer age:");
                    int custAge=inNum.nextInt();
                    System.out.println("Enter the ticket quantity:");
                    int ticketQuantity=inNum.nextInt();
                    
                    tick=new Ticket(ticketID, ticketType, ticketPrice, seatNum, custName, custPhoneNum, custIC, custAge, ticketQuantity);
                    ticketList.addLast(tick);
                    
                    System.out.println("Do you want to add another data? (Y/N):");
                    ans=inText.nextLine();
                    tick=(Ticket)ticketList.getNext();
                }
            }
            
            else if(choice==2)//removing existing data
            {
                String ans="y";

                while(ans.equalsIgnoreCase("y"))
                {
                    System.out.println("---[REMOVAL]---");
                    
                    System.out.println("Please enter the Ticket ID to be removed:"); //remove the Ticket details based on Ticket ID
                    LinkedList ticketTemp=new LinkedList();
                    String ticketID=inText.nextLine();
                    int foundID = 1;
                    tick=(Ticket)ticketList.getFirst();
                    
                    while(tick!=null)
                    {
                        String compareID = tick.getTicketID();
                        if (compareID.equalsIgnoreCase(ticketID))
                        {
                            ticketList.removeFirst();
                            foundID=2;
                        }
                        else{
                            ticketTemp.addLast(tick);
                            ticketList.removeFirst();
                        }
                        tick=(Ticket)ticketList.getFirst();
                    }
                    
                    while (!ticketTemp.isEmpty()) {
                        tick = (Ticket) ticketTemp.removeFirst();
                        ticketList.addLast(tick);
                        
                    }
                    
                    if(foundID == 1){
                        System.out.println("Ticket ID not found");
                    }
                    System.out.println("Do you want to remove another data?\n(Y/N)");
                    ans=inText.nextLine();
                    
                }
            }
            else if (choice == 3) // Displaying data
            {
                tick=(Ticket)ticketList.getFirst();
                System.out.println("Showing all the data:");
                while (tick!=null)
                {
                    System.out.println(tick.toString());
                    tick = (Ticket) ticketList.getNext();
                }
                
            }
            else if (choice==4) //update
            {
                boolean foundIC=false;
                do 
                {
                    System.out.println("Enter IC Numbers to update your data: ");
                    String yourIC=inText.nextLine();
    
                    tick=(Ticket)ticketList.getFirst();
                    
                    while (tick!=null) 
                    {
                        
                        if (tick.getCustIC().equalsIgnoreCase(yourIC))
                        {
                            foundIC=true;
                            boolean update=true;
                            
                            do 
                            {
                                System.out.println("Choose which do you want to update:");
                                System.out.println("[1] - Ticket Type");
                                System.out.println("[2] - Seat Number");
                                System.out.println("[3] - Name");
                                System.out.println("[4] - Phone Number");
                                System.out.println("[5] - IC Number");
                                System.out.println("[6] - Age");
                                System.out.println("[7] - Ticket Quantity");
                                int updChoice = inNum.nextInt();
                                
                                if ((updChoice == 1))
                                {
                                    System.out.println("Choose New Ticket Type: ");
                                    System.out.println("[1] VIP Ticket");
                                    System.out.println("[2] Premium Ticket");
                                    System.out.println("[3] Standard Ticket");
                                    int newTicketType = inNum.nextInt();
                                    if (newTicketType == 1)
                                    {
                                        tick.setTicketType("VIP Ticket");
                                        tick.setTicketPrice(300.00);
                                    }
                                    else if (newTicketType == 2)
                                    {
                                        tick.setTicketType("Premium Ticket");
                                        tick.setTicketPrice(200.00);
                                    }
                                    else if (newTicketType == 3)
                                    {
                                        tick.setTicketType("Standard Ticket");
                                        tick.setTicketPrice(150.00);
                                    }
                                }
                                else if (updChoice == 2)
                                {
                                    System.out.println("Enter New Seat Number: ");
                                    int newSeatNum = inNum.nextInt();
                                    tick.setSeatNum(newSeatNum);
                                }
                                else if (updChoice == 3)
                                {
                                    System.out.println("Enter New Name: ");
                                    String newName = inText.nextLine();
                                    tick.setCustName(newName);
                                }
                                else if (updChoice == 4) 
                                {
                                    System.out.println("Enter New Phone Number: ");
                                    String newPhoneNum = inText.nextLine();
                                    tick.setCustPhoneNum(newPhoneNum);
                                }
                                else if (updChoice == 5)
                                {
                                    System.out.println("Enter New IC Numbers: ");
                                    String newICNum = inText.nextLine();
                                    tick.setCustIC(newICNum);
                                }
                                else if (updChoice == 6) 
                                {
                                    System.out.println("Enter New Age: ");
                                    int newAge = inNum.nextInt();
                                    tick.setCustAge(newAge);
                                }
                                else if (updChoice == 7) 
                                {
                                    System.out.println("Enter New Ticket Quantity: ");
                                    int newQuantity = inNum.nextInt();
                                    tick.setTicketQuantity(newQuantity);
                                }
                                else
                                {
                                    System.out.println("Wrong Input, Please enter again: ");
                                    updChoice = inNum.nextInt();
                                }
                                
                                System.out.println("Do you want to update another data? \n[1]Yes\n[2]No");
                                int UpdateAgain = inNum.nextInt();
                                if (UpdateAgain == 1) 
                                {
                                    update=true;
                                }
                                else 
                                {
                                    update=false;
                                }
                            } while(update==true);
                        }
                        
                        tick=(Ticket)ticketList.getNext();
                    }
                }while (foundIC==false);
            }
            else if (choice == 5) //search
            {
                System.out.println("Enter Customer IC Numbers to search their information: ");
                String SearchIC = inText.nextLine();
                boolean ICfound = false;
                tick=(Ticket)ticketList.getFirst();
                while(tick!=null){
                    if(tick.getCustIC().equalsIgnoreCase(SearchIC)){
                        ICfound = true;
                        System.out.println("Your Ticket Information:");
                        System.out.println(tick.toString());
                    }
                    tick=(Ticket)ticketList.getNext();
                }
                
                if(ICfound == false){
                    System.out.println("The IC numbers is not in the data");
                }
            }
            else if(choice == 6)//exit
            {
                System.out.print("\u000C");
                System.out.println("You've exited the system.");
                break;
            }
            
            else{
                System.out.println("You enter the wrong code,enter again");
            }
            
            System.out.println("\nProceed ?");
            System.out.println("\n[1] YES\n[2] NO");
            int checker = inNum.nextInt();
            if (checker == 1)
            {
                status = true;
            }
            else 
            {
                status = false;
                System.out.print("enter any key to clear the terminal: ");
                inNum.next();
                
                System.out.print("\u000C");
                System.out.println("The system have been cleared");
            }
        }
    }
}